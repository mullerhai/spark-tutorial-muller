package com.heremap.core

import org.apache.spark.sql.SparkSession

object Ex_1_RDD_Intro {
  def main(args: Array[String]): Unit = {

    //For windows only: don't forget to put winutils.exe to c:/bin folder
    System.setProperty("hadoop.home.dir", "c:\\")

    val spark = SparkSession.builder
      .master("local[2]")
      .appName("RDD_Intro")
      .getOrCreate()

    spark.sparkContext.setLogLevel("ERROR")

    /*
    SAMPLE-1: Make dataset based on range and extract RDD from it
     */

    val ds = spark.range(100000000)
    println(ds.count)
    println(ds.rdd.count)


    /*
    SAMPLE-2: Make RDD based on Array with reverse order
    */
    val sc = spark.sparkContext

    val r = 1 to 10 toArray
    // Creates RDD with 3 parts
    val ints = sc.parallelize(r.reverse, 3)

    ints.saveAsTextFile("/home/zaleslaw/data/ints") // works for windows well
    val cachedInts = sc.textFile("/home/zaleslaw/data/ints")
      .map(x => x.toInt).cache()

    // Step 1: Transform each number to its square
    val squares = cachedInts
      .map(x => x * x)

    println("--Squares--")
    squares.collect().foreach(println)

    // Step 2: Filter even numbers

    val even = squares.filter(x => x % 2 == 0)

    println("--Even numbers--")
    even.collect().foreach(println)

    // Step 3: print RDD metadata
    even.setName("Even numbers")
    println("Name is " + even.name + " id is " + even.id)
    println(even.toDebugString)

    println("Total multiplication is " + even.reduce((a, b) => a * b))

    // Step 4: Transform to PairRDD make keys 0 for even and 1 for odd numbers and
    val groups = cachedInts.map(x => if (x % 2 == 0) {
      (0, x)
    } else {
      (1, x)
    })

    println("--Groups--")
    println(groups.groupByKey.toDebugString)
    groups.groupByKey.collect.foreach(println)
    println(groups.countByKey)

    // Step 5: different actions
    println("--Different actions--")
    println("First elem is " + cachedInts.first)
    println("Total amount is " + cachedInts.count)
    println("Take 2")
    cachedInts.take(2).foreach(println)
    println("Take ordered 5")
    cachedInts.takeOrdered(5).foreach(println)


  }
}
