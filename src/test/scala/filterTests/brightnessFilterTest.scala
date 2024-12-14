package filterTests

import filter.brightnessFilter
import models.{basicImage, greyColor}
import org.scalatest.funsuite.AnyFunSuite

import scala.collection.mutable.ArrayBuffer

class brightnessFilterTest extends AnyFunSuite{
  test("brightnessFilter should change brightness") {
    val inputPixels = ArrayBuffer(
      ArrayBuffer(greyColor(50), greyColor(100)),
      ArrayBuffer(greyColor(150), greyColor(200))
    )
    val image = basicImage[greyColor](inputPixels)
    val filter = brightnessFilter(50)

    val outputImage = filter.applyFilter(image)

    val expectedPixels = ArrayBuffer(
      ArrayBuffer(greyColor(100), greyColor(150)),
      ArrayBuffer(greyColor(200), greyColor(250))
    )
    val expectedImage = basicImage[greyColor](expectedPixels)

    for (y <- 0 until outputImage.getHeight()) {
      for (x <- 0 until outputImage.getWidth()) {
        assert(outputImage.getPixel(x, y).getVal == expectedImage.getPixel(x, y).getVal)
      }
    }
  }

  test("brightnessFilter should cap values at 255") {
    val inputPixels = ArrayBuffer(
      ArrayBuffer(greyColor(240), greyColor(250)),
      ArrayBuffer(greyColor(200), greyColor(255))
    )
    val image = basicImage[greyColor](inputPixels)
    val filter = brightnessFilter(20)

    val outputImage = filter.applyFilter(image)

    val expectedPixels = ArrayBuffer(
      ArrayBuffer(greyColor(255), greyColor(255)),
      ArrayBuffer(greyColor(220), greyColor(255))
    )
    val expectedImage = basicImage[greyColor](expectedPixels)

    for (y <- 0 until outputImage.getHeight()) {
      for (x <- 0 until outputImage.getWidth()) {
        assert(outputImage.getPixel(x, y).getVal == expectedImage.getPixel(x, y).getVal)
      }
    }
  }

  test("brightnessFilter should handle negative deltas and cap at 0") {
    val inputPixels = ArrayBuffer(
      ArrayBuffer(greyColor(10), greyColor(20)),
      ArrayBuffer(greyColor(5), greyColor(0))
    )
    val image = basicImage[greyColor](inputPixels)
    val filter = brightnessFilter(-15)

    val outputImage = filter.applyFilter(image)

    val expectedPixels = ArrayBuffer(
      ArrayBuffer(greyColor(0), greyColor(5)),
      ArrayBuffer(greyColor(0), greyColor(0))
    )
    val expectedImage = basicImage[greyColor](expectedPixels)

    for (y <- 0 until outputImage.getHeight()) {
      for (x <- 0 until outputImage.getWidth()) {
        assert(outputImage.getPixel(x, y).getVal == expectedImage.getPixel(x, y).getVal)
      }
    }
  }
}
