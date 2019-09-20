package eu.wojciechzurek.spark

import org.apache.spark.ml.evaluation.RegressionEvaluator
import org.apache.spark.ml.recommendation.ALS


fun main(args: Array<String>) = alsExample()

fun alsExample() {
    val sparkSession = LocalSparkSession.get("ALS example")

    val rdd = sparkSession.read().format("csv").load(object {}.javaClass.getResource("/movie_ratings.csv").path).javaRDD()

    val moviesRatings = rdd.map {
        MovieRating(
            it[0].toString().toInt(),
            it[1].toString(),
            it[2].toString().toInt(),
            it[3].toString(),
            it[4].toString().toInt(),
            it[5].toString().toLong()
        )
    }

    val dataFrame = sparkSession.createDataFrame(moviesRatings, MovieRating::class.java)
    val splits = dataFrame.randomSplit(doubleArrayOf(0.8, 0.2))
    val training = splits[0]
    val test = splits[1]

    val als = ALS().setMaxIter(6).setRegParam(0.01).setUserCol("userId").setItemCol("movieId").setRatingCol("rating")
    val model = als.fit(training)
    model.coldStartStrategy = "drop"
    val predictions = model.transform(test)

    val rmse = RegressionEvaluator().setMetricName("rmse").setLabelCol("rating").setPredictionCol("prediction")
        .evaluate(predictions)

    println("Root mean square error: $rmse")

    val top5MoviesForUser = model.recommendForAllUsers(5)
    val top5UserRec = model.recommendForAllItems(5)

    val subUser = dataFrame.select(als.userCol).distinct().limit(3)
    val top5MoviesFoSubUser = model.recommendForUserSubset(subUser, 5)

    val subRec = dataFrame.select(als.itemCol).distinct().limit(3)
    val top5UserSubRec = model.recommendForItemSubset(subRec, 5)

    top5MoviesForUser.show()
    top5UserRec.show()
    top5MoviesFoSubUser.show()
    top5UserSubRec.show()
}

data class MovieRating(
    val userId: Int,
    val publicUserId: String,
    val movieId: Int,
    val publicMovieId: String,
    val rating: Int,
    val timestamp: Long
)