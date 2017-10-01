import sbt.util

name := "scala-web-crawler"

version := "0.1"

scalaVersion := "2.11.11"

libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-actor" % "2.5.6",
    "commons-validator" % "commons-validator" % "1.6",
    "net.ruippeixotog" %% "scala-scraper" % "2.0.0"
  )

logLevel := Level.Error