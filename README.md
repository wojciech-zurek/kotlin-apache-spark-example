# kotlin-apache-spark-example
kotlin apache spark example

### Download

```bash
    git clone https://github.com/wojciech-zurek/kotlin-apache-spark-example.git
```

### Build shadow/fat jar

```bash
    cd kotlin-apache-spark-example/
    ./gradlew shadowJar
```

### Run reduce example

```bash
    java -cp build/libs/kotlin-apache-spark-example-1.0-SNAPSHOT-all.jar eu.wojciechzurek.spark.ReduceExampleKt
```

### Run SQL Reflection example

```bash
    java -cp build/libs/kotlin-apache-spark-example-1.0-SNAPSHOT-all.jar eu.wojciechzurek.spark.SQLReflectionExampleKt
```

### Run SQL Schema example

```bash
    java -cp build/libs/kotlin-apache-spark-example-1.0-SNAPSHOT-all.jar eu.wojciechzurek.spark.SQLSchemaExampleKt
```

### Run ML Correlation example

```bash
    java -cp build/libs/kotlin-apache-spark-example-1.0-SNAPSHOT-all.jar eu.wojciechzurek.spark.CorrelationExampleKt
```

### Run ML Hypothesis(ChiSquareTest) example    

```bash
    java -cp build/libs/kotlin-apache-spark-example-1.0-SNAPSHOT-all.jar eu.wojciechzurek.spark.HypothesisExampleKt
```

### Run ML ALS example    

```bash
    java -cp build/libs/kotlin-apache-spark-example-1.0-SNAPSHOT-all.jar eu.wojciechzurek.spark.ALSExampleKt
```