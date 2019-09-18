package eu.wojciechzurek.spark

import org.apache.spark.sql.SparkSession

object LocalSparkSession {
    fun get(name: String): SparkSession = SparkSession
        .builder()
        .master("local")
        .appName(name)
        .orCreate
}