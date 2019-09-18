package eu.wojciechzurek.spark

import org.apache.spark.sql.RowFactory
import org.apache.spark.sql.types.*

fun main(args: Array<String>) = sqlSchemaExample()

fun sqlSchemaExample() {

    val sparkSession = LocalSparkSession.get("Spark SQL Schema Example")

    val fields = arrayOf(
        StructField("name", DataTypes.StringType, true, Metadata.empty()),
        StructField("age", DataTypes.IntegerType, true, Metadata.empty())
    )
    val schema = StructType(fields)

    val items = listOf("Wojtek, 37", "Ola, 34", "Krzysiek, 20", "Agnieszka, 32")

    val rows = items.map { it.split(",") }.map { RowFactory.create(it[0], it[1].trim().toInt()) }

    val dataFrame = sparkSession.createDataFrame(rows, schema)
    dataFrame.printSchema()

    dataFrame.createOrReplaceTempView("user")

    val result = sparkSession.sql("SELECT name FROM user WHERE age > 20 AND age < 37")

    result.collectAsList().map { it.get(0) }.forEach(::println)
}