package eu.wojciechzurek.spark

fun main(args: Array<String>) = sqlReflectionExample()

fun sqlReflectionExample() {

    val sparkSession = LocalSparkSession.get("Spark SQL Reflection Example")

    val items = listOf("Wojtek, 37", "Ola, 34", "Krzysiek, 20", "Agnieszka, 32")

    val users = items.map { it.split(",") }.map { User(it[0], it[1].trim().toInt()) }

    val dataFrame = sparkSession.createDataFrame(users, User::class.java)

    dataFrame.createOrReplaceTempView("user")
    dataFrame.printSchema()

    val result = sparkSession.sql("SELECT name FROM user WHERE age > 20 AND age < 37")

    result.collectAsList().map { it.get(0) }.forEach(::println)
}

data class User(val name: String, val age: Int)