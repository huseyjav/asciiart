package transformerTests

import models.{asciiColor, basicImage, greyColor}
import org.scalatest.funsuite.AnyFunSuite
import transformer.nonLinearGreyToAsciiTransformer

import scala.collection.mutable.ArrayBuffer

class nonLinearGreyToAsciiTransformerTest extends AnyFunSuite{
  test("nonLinearGreyToAsciiTransformer should map greyscale to ASCII based on ranges") {
    val inputPixels = ArrayBuffer(
      ArrayBuffer(greyColor(50), greyColor(210), greyColor(225), greyColor(240))
    )
    val image = basicImage[greyColor](inputPixels)
    val transformer = nonLinearGreyToAsciiTransformer()

    val outputImage = transformer.transform(image)

    val expectedPixels = ArrayBuffer(
      ArrayBuffer(asciiColor('@'),
        asciiColor('+'),
        asciiColor('-'),
        asciiColor('.'))
    )
    val expectedImage = basicImage[asciiColor](expectedPixels)

    for (y <- 0 until outputImage.getHeight()) {
      for (x <- 0 until outputImage.getWidth()) {
        assert(outputImage.getPixel(x, y).getVal == expectedImage.getPixel(x, y).getVal)
      }
    }
  }
  test("nonLinearGreyToAsciiTransformer should handle single pixel input") {
    val inputPixels = ArrayBuffer(
      ArrayBuffer(greyColor(215))
    )
    val image = new basicImage[greyColor](inputPixels)
    val transformer = new nonLinearGreyToAsciiTransformer()

    val outputImage = transformer.transform(image)

    assert(outputImage.getPixel(0, 0).getVal == '+')
  }
  test("nonLinearGreyToAsciiTransformer should handle empty input") {
    val inputPixels = ArrayBuffer[ArrayBuffer[greyColor]]()

    val image = new basicImage[greyColor](inputPixels)
    val transformer = new nonLinearGreyToAsciiTransformer()

    val outputImage = transformer.transform(image)

    assert(outputImage.getHeight() == 0)
    assert(outputImage.getWidth() == 0)
  }
}
