package Main
import Source.{imgFileImporter, imgGenerator}
import UserInterface.commandLineInterface
import exporter.{consoleExport, fileExport}
import filter.{brightnessFilter, flipFilter, invertFilter}
import models.{basicImage, rgbColor}
import transformer.{greyToAsciiTransformer, rgbToGreyTransformer}
import java.io.{File, PrintWriter}

class pipeline {
  def run(args: Array[String]) : Unit = {
    var ui = commandLineInterface(args)

    var imageRGB: basicImage[rgbColor] = ui.getSource.load()
    var filters = ui.getFilters

    var imageGrey = ui.getRgbToGreyTransformer.transform(imageRGB)

    for (x <- filters) {
      imageGrey = x.applyFilter(imageGrey)
    }

    var imageASCII = ui.getGreyToAsciiTransformer
      .transform(imageGrey)

    for (output <- ui.getExport) output.exportSource(imageASCII)
  }
}
