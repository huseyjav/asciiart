package transformerTests

import models.{basicImage, greyColor, rgbColor}
import org.scalatest.funsuite.AnyFunSuite
import transformer.rgbToGreyTransformer

import scala.collection.mutable.ArrayBuffer

class rgbToGreyTransformerTests extends AnyFunSuite{
  test("rgbToGreyTransformer should correctly transform RGB to greyscale") {
    val inputPixels = ArrayBuffer(
      ArrayBuffer(rgbColor(255, 0, 0), rgbColor(0, 255, 0)),
      ArrayBuffer(rgbColor(0, 0, 255), rgbColor(255, 255, 255))
    )
    val image = basicImage[rgbColor](inputPixels)
    val transformer = rgbToGreyTransformer()

    val outputImage = transformer.transform(image)

    val expectedPixels = ArrayBuffer(
      ArrayBuffer(greyColor(76), greyColor(150)),
      ArrayBuffer(greyColor(28), greyColor(255))
    )
    val expectedImage = new basicImage[greyColor](expectedPixels)

    for (y <- 0 until outputImage.getHeight()) {
      for (x <- 0 until outputImage.getWidth()) {
        assert(outputImage.getPixel(x, y).getVal == expectedImage.getPixel(x, y).getVal)
      }
    }
  }
}
