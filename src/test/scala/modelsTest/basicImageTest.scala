package modelsTest

import models.basicImage
import org.scalatest.funsuite.AnyFunSuite

import scala.collection.mutable.ArrayBuffer

class basicImageTest extends AnyFunSuite{
  test("basicImage should correctly return pixel values") {
    val pixels = ArrayBuffer(
      ArrayBuffer(1, 2, 3),
      ArrayBuffer(4, 5, 6),
      ArrayBuffer(7, 8, 9)
    )
    val image = basicImage[Int](pixels)

    assert(image.getPixel(0, 0) == 1)
    assert(image.getPixel(1, 1) == 5)
    assert(image.getPixel(2, 2) == 9)
  }

  test("basicImage should correctly return dimensions") {
    val pixels = ArrayBuffer(
      ArrayBuffer(1, 2, 3),
      ArrayBuffer(4, 5, 6),
      ArrayBuffer(7, 8, 9)
    )
    val image = basicImage[Int](pixels)

    assert(image.getHeight() == 3)
    assert(image.getWidth() == 3)

    val emptyImage = basicImage[Int](ArrayBuffer())
    assert(emptyImage.getHeight() == 0)
    assert(emptyImage.getWidth() == 0)
  }

  test("basicImage should correctly return rows") {
    val pixels = ArrayBuffer(
      ArrayBuffer(1, 2, 3),
      ArrayBuffer(4, 5, 6),
      ArrayBuffer(7, 8, 9)
    )
    val image = basicImage[Int](pixels)

    assert(image.getRow(0) == ArrayBuffer(1, 2, 3))
    assert(image.getRow(1) == ArrayBuffer(4, 5, 6))
    assert(image.getRow(2) == ArrayBuffer(7, 8, 9))
  }

  test("basicImage should correctly return columns") {
    val pixels = ArrayBuffer(
      ArrayBuffer(1, 2, 3),
      ArrayBuffer(4, 5, 6),
      ArrayBuffer(7, 8, 9)
    )
    val image = basicImage[Int](pixels)

    assert(image.getColumn(0) == ArrayBuffer(1, 4, 7))
    assert(image.getColumn(1) == ArrayBuffer(2, 5, 8))
    assert(image.getColumn(2) == ArrayBuffer(3, 6, 9))
  }

  test("basicImage should correctly map values") {
    val pixels = ArrayBuffer(
      ArrayBuffer(1, 2, 3),
      ArrayBuffer(4, 5, 6),
      ArrayBuffer(7, 8, 9)
    )
    val image = basicImage[Int](pixels)
    val mappedImage = image.map(value => value * 2)

    val expectedPixels = ArrayBuffer(
      ArrayBuffer(2, 4, 6),
      ArrayBuffer(8, 10, 12),
      ArrayBuffer(14, 16, 18)
    )
    val expectedImage = basicImage[Int](expectedPixels)

    for (y <- 0 until mappedImage.getHeight()) {
      for (x <- 0 until mappedImage.getWidth()) {
        assert(mappedImage.getPixel(x, y) == expectedImage.getPixel(x, y))
      }
    }
  }

  test("basicImage should handle an empty image") {
    val emptyImage = basicImage[Int](ArrayBuffer())

    assert(emptyImage.getHeight() == 0)
    assert(emptyImage.getWidth() == 0)

    val mappedImage = emptyImage.map(value => value * 2)
    assert(mappedImage.getHeight() == 0)
    assert(mappedImage.getWidth() == 0)
  }
}
