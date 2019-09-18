package eu.wojciechzurek.spark

import org.apache.spark.api.java.JavaSparkContext

fun main(args: Array<String>) = reduceExample()

fun reduceExample() {
    val sc = JavaSparkContext(LocalSparkSession.get("Reduce simple example").sparkContext())

    val items = listOf("564943,43524,8744,XYZ,3533443,OODSN", "345443,5653,ZYZ,453343,SVS", "465766,85445,4653")

    val input = sc.parallelize(items)

    val sumOfNumbers = input.flatMap { it.split(",").iterator() }
        .filter { it.matches(Regex("[0-9]+")) }
        .map { it.toInt() }
        .reduce { total, next -> total + next }

    println("Sum of numbers: $sumOfNumbers")
}