name := "spakr"

version := "0.1"

scalaVersion := "2.12.7"
// https://mvnrepository.com/artifact/org.apache.spark/spark-core
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.4"

libraryDependencies += "org.apache.spark" %% "spark-mllib" % "2.4.4"
//libraryDependencies += "org.apache.spark" % "spark-sql" %  "2.4.4"
//libraryDependencies += "org.apache.spark" % "spark-streaming" %  "2.4.4"
//libraryDependencies += "org.apache.spark" % "spark-streaming-kafka-0-10" %  "2.4.4"
//libraryDependencies += "org.apache.spark" % "spark-sql-kafka-0-10" %  "2.4.4"

// https://mvnrepository.com/artifact/org.apache.bahir/spark-streaming-twitter
libraryDependencies += "org.apache.bahir" %% "spark-streaming-twitter" % "2.4.0"

libraryDependencies += "joda-time" % "joda-time" % "2.9.9"
libraryDependencies += "com.typesafe" % "config" % "1.3.1"

resolvers += "bintray-spark-packages" at "https://dl.bintray.com/spark-packages/maven/"
libraryDependencies += "saurfang" % "spark-knn" % "0.2.0"
