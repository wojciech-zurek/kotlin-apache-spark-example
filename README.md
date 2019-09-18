# kotlin-spark-example
kotlin-spark-example

### Download

```bash
    git clone https://github.com/wojciech-zurek/kotlin-spark-example.git
```

### Build shadow/fat jar

```bash
    cd kotlin-spark-example/
    ./gradlew shadowJar
```

### Run reduce example

```bash
    java -cp build/libs/kotlin-spark-example-1.0-SNAPSHOT-all.jar eu.wojciechzurek.spark.ReduceExampleKt
```

### Run SQL Reflection example

```bash
    java -cp build/libs/kotlin-spark-example-1.0-SNAPSHOT-all.jar eu.wojciechzurek.spark.SQLReflectionExampleKt
```

### Run SQL Schema example

```bash
    java -cp build/libs/kotlin-spark-example-1.0-SNAPSHOT-all.jar eu.wojciechzurek.spark.SQLSchemaExampleKt
```