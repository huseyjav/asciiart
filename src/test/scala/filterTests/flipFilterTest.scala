package filterTests

import filter.flipFilter
import models.{basicImage, greyColor}
import org.scalatest.funsuite.AnyFunSuite

import scala.collection.mutable.ArrayBuffer

class flipFilterTest extends AnyFunSuite{
  test("flipFilter should flip image on Y-axis") {
    val inputPixels = ArrayBuffer(
      ArrayBuffer(greyColor(1), greyColor(2), greyColor(3)),
      ArrayBuffer(greyColor(4), greyColor(5), greyColor(6))
    )
    val image = basicImage[greyColor](inputPixels)
    val filter = flipFilter(isFlipOnX = false)

    val outputImage = filter.applyFilter(image)

    val expectedPixels = ArrayBuffer(
      ArrayBuffer(greyColor(3), greyColor(2), greyColor(1)),
      ArrayBuffer(greyColor(6), greyColor(5), greyColor(4))
    )
    val expectedImage = basicImage[greyColor](expectedPixels)

    for (y <- 0 until outputImage.getHeight()) {
      for (x <- 0 until outputImage.getWidth()) {
        assert(outputImage.getPixel(x, y).getVal == expectedImage.getPixel(x, y).getVal)
      }
    }
  }

  test("flipFilter should flip image on X-axis") {
    val inputPixels = ArrayBuffer(
      ArrayBuffer(greyColor(1), greyColor(2), greyColor(3)),
      ArrayBuffer(greyColor(4), greyColor(5), greyColor(6))
    )
    val image = basicImage[greyColor](inputPixels)
    val filter = flipFilter(isFlipOnX = true)

    val outputImage = filter.applyFilter(image)

    val expectedPixels = ArrayBuffer(
      ArrayBuffer(greyColor(4), greyColor(5), greyColor(6)),
      ArrayBuffer(greyColor(1), greyColor(2), greyColor(3))
    )
    val expectedImage = basicImage[greyColor](expectedPixels)

    for (y <- 0 until outputImage.getHeight()) {
      for (x <- 0 until outputImage.getWidth()) {
        assert(outputImage.getPixel(x, y).getVal == expectedImage.getPixel(x, y).getVal)
      }
    }
  }
}
