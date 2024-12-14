package sourceTest

import Source.imgFileImporter
import models.rgbColor
import org.scalatest.funsuite.AnyFunSuite

class imgFileImporterTest extends AnyFunSuite{
  test("imgFileImporter should throw exception for unsupported file format") {
    val invalidFilePath = "test_invalid.txt"

    val importer = imgFileImporter(invalidFilePath)
    val exception = intercept[IllegalArgumentException] {
      importer.load()
    }

    assert(exception.getMessage == "wrong file format")
  }
  test("imgFileImporter should correct load"){
    var filePath = "1414red.png"

    val importer = imgFileImporter(filePath)
    val loadedImage = importer.load()

    assert(loadedImage.getHeight() == 14)
    assert(loadedImage.getWidth() == 14)
    assert(loadedImage.getPixel(0, 0).getR == rgbColor(255, 255, 255).getR)
    assert(loadedImage.getPixel(0, 0).getG == rgbColor(255, 255, 255).getG)
    assert(loadedImage.getPixel(0, 0).getB == rgbColor(255, 255, 255).getB)

    assert(loadedImage.getPixel(3, 0).getR == rgbColor(238, 0, 0).getR)
    assert(loadedImage.getPixel(3, 0).getG == rgbColor(238, 0, 0).getG)
    assert(loadedImage.getPixel(3, 0).getB == rgbColor(238, 0, 0).getB)

  }
}
