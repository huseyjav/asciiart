package userInterfaceTests

import Source.imgFileImporter
import UserInterface.commandLineInterface
import exporter.{consoleExport, fileExport}
import filter.{brightnessFilter, flipFilter, invertFilter}
import org.scalatest.funsuite.AnyFunSuite
import transformer.{linearGreyToAsciiTransformer, rgbToGreyTransformer}

class commandLineInterfaceTest extends AnyFunSuite{
  test("getExport should return appropriate exports based on arguments") {
    val args = Array("--output-console", "--output-file", "output.txt")
    val cli = commandLineInterface(args)
    val exports = cli.getExport

    assert(exports.length == 2)
    assert(exports.exists(_.isInstanceOf[consoleExport]))
    assert(exports.exists(_.isInstanceOf[fileExport]))
  }

  test("getGreyToAsciiTransformer should return correct transformer") {
    val args = Array("--table", "bourke-small")
    val cli = commandLineInterface(args)
    val transformer = cli.getGreyToAsciiTransformer

    assert(transformer.isInstanceOf[linearGreyToAsciiTransformer])
    assert(transformer.asInstanceOf[linearGreyToAsciiTransformer].getTransformationTable == " .:-=+*#%@")
  }

  test("getRgbToGreyTransformer should always return an instance of rgbToGreyTransformer") {
    val args = Array.empty[String]
    val cli = commandLineInterface(args)
    val transformer = cli.getRgbToGreyTransformer

    assert(transformer.isInstanceOf[rgbToGreyTransformer])
  }

  test("getFilters should return appropriate filters based on arguments") {
    val args = Array("--invert", "--flip", "x", "--brightness", "20")
    val cli = commandLineInterface(args)
    val filters = cli.getFilters

    assert(filters.length == 3)
    assert(filters.exists(_.isInstanceOf[invertFilter]))
    assert(filters.exists(_.isInstanceOf[flipFilter]))
    assert(filters.exists(_.isInstanceOf[brightnessFilter]))
    assert(filters.collect { case f: flipFilter => f }.head.getisFlipOnX)
    assert(filters.collect { case f: brightnessFilter => f }.head.getBrightness == 20)
  }

  test("getSource should throw exception for multiple or no sources") {
    val argsMultipleSources = Array("--image", "path1", "--image-random")
    val argsNoSources = Array.empty[String]

    intercept[IllegalArgumentException] {
      commandLineInterface(argsMultipleSources).getSource
    }

    intercept[IllegalArgumentException] {
      commandLineInterface(argsNoSources).getSource
    }
  }

  test("getSource should return imgFileImporter for valid --image argument") {
    val args = Array("--image", "path/to/image.png")
    val cli = commandLineInterface(args)
    val source = cli.getSource

    assert(source.isInstanceOf[imgFileImporter])
  }

  test("splitArguments should correctly parse arguments") {
    val args = Array("--image", "path/to/image.png", "--output-file", "output.txt", "--invert")
    val cli = commandLineInterface(args)
    val parsedArgs = cli.splitArguments

    assert(parsedArgs.contains(("--image", "path/to/image.png")))
    assert(parsedArgs.contains(("--output-file", "output.txt")))
    assert(parsedArgs.contains(("--invert", "")))
  }
}
