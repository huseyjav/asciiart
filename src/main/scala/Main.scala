package Main

import Source.{imgFileImporter, imgGenerator}
import UserInterface.commandLineInterface
import exporter.{consoleExport, fileExport}
import filter.{brightnessFilter, flipFilter, invertFilter}
import models.{basicImage, rgbColor}
import transformer.{greyToAsciiTransformer, rgbToGreyTransformer}

import java.io.{File, PrintWriter}




object Main{
  def main(args: Array[String]): Unit = {
    pipeline().run(args)
  }
}