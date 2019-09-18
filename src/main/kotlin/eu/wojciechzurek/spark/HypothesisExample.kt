package eu.wojciechzurek.spark

import org.apache.spark.ml.linalg.VectorUDT
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.ml.stat.ChiSquareTest
import org.apache.spark.sql.RowFactory
import org.apache.spark.sql.types.DataTypes
import org.apache.spark.sql.types.Metadata
import org.apache.spark.sql.types.StructField
import org.apache.spark.sql.types.StructType

fun main(args: Array<String>) = hypothesisExample()

fun hypothesisExample() {
    val sparkSession = LocalSparkSession.get("Hypothesis example")

    val rows = listOf(
        RowFactory.create(0.0, Vectors.dense(1.0, 30.0)),
        RowFactory.create(0.0, Vectors.dense(0.0, 20.0)),
        RowFactory.create(1.0, Vectors.dense(3.0, 20.0)),
        RowFactory.create(0.0, Vectors.dense(3.0, 10.0)),
        RowFactory.create(0.0, Vectors.dense(1.0, 20.0)),
        RowFactory.create(1.0, Vectors.dense(2.0, 30.0)),
        RowFactory.create(0.0, Vectors.dense(1.5, 40.0)),
        RowFactory.create(1.0, Vectors.dense(3.0, 40.0)),
        RowFactory.create(0.0, Vectors.dense(3.5, 20.0)),
        RowFactory.create(1.0, Vectors.dense(3.5, 30.0))
    )

    val fields = arrayOf(
        StructField("label", DataTypes.DoubleType, true, Metadata.empty()),
        StructField("data", VectorUDT(), true, Metadata.empty()))
    val schema = StructType(fields)

    val dataFrame = sparkSession.createDataFrame(rows, schema)
    val row = ChiSquareTest.test(dataFrame, "data", "label").head()

    row.let {
        println("Result pValues: ${it[0]}")
        println("Result degreesOfFreedom: ${it.getList<Any>(1)}")
        println("Result statistics: ${it[2]}")
    }

}