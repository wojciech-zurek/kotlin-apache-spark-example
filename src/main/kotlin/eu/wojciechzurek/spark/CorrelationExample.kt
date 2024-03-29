package eu.wojciechzurek.spark

import org.apache.spark.ml.linalg.VectorUDT
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.ml.stat.Correlation
import org.apache.spark.sql.RowFactory
import org.apache.spark.sql.types.Metadata
import org.apache.spark.sql.types.StructField
import org.apache.spark.sql.types.StructType

fun main(args: Array<String>) = correlationExample()

fun correlationExample() {
    val sparkSession = LocalSparkSession.get("Correlation example")

    val rows = listOf(
        Vectors.sparse(4, intArrayOf(0, 3), doubleArrayOf(2.0, -2.0)),
        Vectors.dense(3.0, 6.0, 1.0, 5.0),
        Vectors.dense(5.0, 5.0, 1.0, 7.0),
        Vectors.sparse(4, intArrayOf(0, 3), doubleArrayOf(8.0, 2.0))
    ).map { RowFactory.create(it) }

    val fields = arrayOf(StructField("features", VectorUDT(), true, Metadata.empty()))
    val schema = StructType(fields)

    val dataFrame = sparkSession.createDataFrame(rows, schema)
    val row1 = Correlation.corr(dataFrame, "features").head()
    val row2 = Correlation.corr(dataFrame, "features", "spearman").head()

    println("Correlation of matrix via Pearson method: ${row1[0]}")
    println("Correlation of matrix via Spearman method: ${row2[0]}")
}