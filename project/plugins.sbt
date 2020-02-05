resolvers += "bintray-spark-packages" at "https://dl.bintray.com/spark-packages/maven/"

logLevel := Level.Warn
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.6")