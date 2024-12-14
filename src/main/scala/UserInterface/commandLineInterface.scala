package UserInterface
import Source.{ImgSource, imgFileImporter, imgGenerator}
import exporter.{basicExport, consoleExport, fileExport}
import filter.{baseFilter, brightnessFilter, flipFilter, invertFilter}
import models.{asciiColor, greyColor}
import transformer.{greyToAsciiTransformer, linearGreyToAsciiTransformer, nonLinearGreyToAsciiTransformer, rgbToGreyTransformer}

import scala.collection.mutable.ArrayBuffer


class commandLineInterface(args: Array[String]) extends baseInterface {
  override def getExport: ArrayBuffer[basicExport[asciiColor]] = {
    var toReturn = ArrayBuffer[basicExport[asciiColor]]()
    for(arg <- splitArguments){
      if(arg._1 == "--output-console") toReturn += consoleExport()
      if(arg._1 == "--output-file") toReturn += fileExport(arg._2)
    }
    toReturn
  }

  override def getGreyToAsciiTransformer: greyToAsciiTransformer = {

    var result = splitArguments.find(_._1 == "--table")
    // default one
    if(result.isEmpty) return linearGreyToAsciiTransformer(" .'`^\",:;Il!i><~+_-?][}{1)(|\\/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao*#MW&8%B@$")
    if(result.get._2 == "bourke-small") return linearGreyToAsciiTransformer(" .:-=+*#%@")
    if(result.get._2 == "non-linear") return nonLinearGreyToAsciiTransformer()
    return linearGreyToAsciiTransformer(result.get._2)
  }

  override def getRgbToGreyTransformer: rgbToGreyTransformer = {
    return rgbToGreyTransformer()
  }

  override def getFilters: ArrayBuffer[baseFilter[greyColor]] = {
    var filterArray = ArrayBuffer[baseFilter[greyColor]]()

    for(arg <- splitArguments){
      if(arg._1 == "--invert"){
        filterArray += invertFilter()
      }
      else if(arg._1 == "--flip"){
        filterArray += flipFilter({
          if(arg._2=="x") true
          else if(arg._2=="y") false
          else throw IllegalArgumentException("wrong parameter for flip")
        })
      }
      else if(arg._1 == "--brightness"){
        filterArray += brightnessFilter(arg._2.toInt)
      }
    }
    filterArray
  }

  override def getSource: ImgSource = {
    var argsSplit = splitArguments
    if(argsSplit.count(_._1 == "--image") + argsSplit.count(_._1 == "--image-random") != 1)
      throw IllegalArgumentException("Multiple or no sources entered")
    val result = argsSplit.find(_._1 == "--image")
    if(result.isDefined){
      if(result.get._2.isEmpty) throw IllegalArgumentException("No path specified for image")
      return imgFileImporter(result.get._2)
    }
    val result2 = argsSplit.find(_._1 == "--image-random")
    if(result2.isDefined) return imgGenerator()
    throw IllegalArgumentException("No image input method specified")
  }

  def parseOptionWithArgument (i : Int): (String,String) = {
    if (args.length <= i + 1) throw IllegalArgumentException("No path specified for image")
    (args(i), args(i + 1))
  }
  def splitArguments : ArrayBuffer[(String,String)] = {
    var toReturn = ArrayBuffer[(String,String)]()
    var i : Int = 0
    while(i<args.length){
       args(i) match {
        case "--image" =>
          toReturn += parseOptionWithArgument(i)
          i = i + 1
        case "--image-random" =>
          toReturn += ((args(i),String()))
        case "--output-file" =>
          toReturn += parseOptionWithArgument(i)
          i = i + 1
        case "--output-console" =>
          toReturn += ((args(i),String()))
        case "--invert" =>
          toReturn += ((args(i),String()))
        case "--flip" =>
          toReturn += parseOptionWithArgument(i)
          i = i + 1
        case "--brightness" =>
          toReturn += parseOptionWithArgument(i)
          i = i + 1
        case "--table" =>
          toReturn += parseOptionWithArgument(i)
          i = i + 1
        case _ => throw IllegalArgumentException("Illegal argument found")
       }
      i = i + 1
    }
    return toReturn
  }
}
