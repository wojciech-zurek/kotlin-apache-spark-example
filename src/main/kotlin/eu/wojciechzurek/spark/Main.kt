package eu.wojciechzurek.spark

import org.apache.spark.SparkConf
import org.apache.spark.api.java.JavaSparkContext

fun main (args: Array<String>) = sparkJob()

fun sparkJob() {
    val conf = SparkConf().setMaster("local").setAppName("Kotlin Spark Test").setSparkHome("/home/gabi/Apps/spark-2.4.4-bin-hadoop2.7/bin")

    val sc = JavaSparkContext(conf)

    val items = listOf("123/643/7563/2134/ALPHA", "2343/6356/BETA/2342/12", "23423/656/343")

    val input = sc.parallelize(items)

    val sumOfNumbers = input.flatMap { it.split("/").iterator() }
        .filter { it.matches(Regex("[0-9]+")) }
        .map { it.toInt() }
        .reduce {total,next -> total + next }

    println(sumOfNumbers)
}