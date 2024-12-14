
name := "ASCIIArt"

version := "1.10.5"

scalaVersion := "3.4.2"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19"
libraryDependencies += "org.scalatestplus" %% "mockito-5-12" % "3.2.19.0"
javacOptions ++= Seq("-source", "22", "-target", "22")