package filterTests

import filter.invertFilter
import models.{basicImage, greyColor}
import org.scalatest.funsuite.AnyFunSuite

import scala.collection.mutable.ArrayBuffer

class invertFilterTest extends AnyFunSuite{
  test("colors should be inverted") {
    val inputPixels = ArrayBuffer(
      ArrayBuffer(greyColor(255), greyColor(0), greyColor(0)),
      ArrayBuffer(greyColor(200), greyColor(10), greyColor(120))
    )
    val image = basicImage[greyColor](inputPixels)
    val filter = invertFilter()

    val outputImage = filter.applyFilter(image)

    val expectedPixels = ArrayBuffer(
      ArrayBuffer(greyColor(0), greyColor(255), greyColor(255)),
      ArrayBuffer(greyColor(55), greyColor(245), greyColor(135))
    )
    val expectedImage = basicImage[greyColor](expectedPixels)

    for (y <- 0 until outputImage.getHeight()) {
      for (x <- 0 until outputImage.getWidth()) {
        assert(outputImage.getPixel(x, y).getVal == expectedImage.getPixel(x, y).getVal)
      }
    }
  }
}
