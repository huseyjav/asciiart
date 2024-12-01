package Main

import Source.{imgFileImporter, imgGenerator}
import exporter.{consoleExport, fileExport}
import filter.{brightnessFilter, flipFilter, invertFilter}
import models.{basicImage, rgbColor}
import transformer.{greyToAsciiTransformer, rgbToGreyTransformer}

import java.io.{File, PrintWriter}

@main def main(): Unit = {
  var imageRGB : basicImage[rgbColor] = imgFileImporter("shrek.png").load()
  //var imageRGB : basicImage[rgbColor] = imgGenerator().load()
  var imageGrey = flipFilter(true).applyFilter(
    brightnessFilter(-100).applyFilter(
      rgbToGreyTransformer().transform(imageRGB))
  )
  var imageASCII = greyToAsciiTransformer(
    " .'`^\",:;Il!i><~+_-?][}{1)(|\\/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao*#MW&8%B@$")
    .transform(imageGrey)

  println("Height: "+ imageASCII.getHeight())
  println("Width: "+ imageASCII.getWidth())

  fileExport("output2.txt").exportSource(imageASCII)
  consoleExport().exportSource(imageASCII)
  println("sikbasi")
}
