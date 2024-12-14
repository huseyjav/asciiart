package transformerTests

import models.{asciiColor, basicImage, greyColor}
import org.scalatest.funsuite.AnyFunSuite
import transformer.linearGreyToAsciiTransformer

import scala.collection.mutable.ArrayBuffer

class linearGreyToAsciiTransformerTest extends AnyFunSuite{
  test("linearGreyToAsciiTransformer should map greyscale to ASCII") {
    val transformationTable = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/|()1{}[]?-_+~<>i!lI;:,\"^`'. "
    val inputPixels = ArrayBuffer(
      ArrayBuffer(greyColor(0), greyColor(128), greyColor(255))
    )
    val image = basicImage[greyColor](inputPixels)
    val transformer = linearGreyToAsciiTransformer(transformationTable)

    val outputImage = transformer.transform(image)

    val expectedPixels = ArrayBuffer(
      ArrayBuffer(asciiColor(transformationTable.head), // For greyColor(0)
        asciiColor(transformationTable(transformationTable.length / 2)), // For greyColor(128)
        asciiColor(transformationTable.last)) // For greyColor(255)
    )
    val expectedImage = basicImage[asciiColor](expectedPixels)

    for (y <- 0 until outputImage.getHeight()) {
      for (x <- 0 until outputImage.getWidth()) {
        assert(outputImage.getPixel(x, y).getVal == expectedImage.getPixel(x, y).getVal)
      }
    }
  }

  test("linearGreyToAsciiTransformer should handle single pixel input") {
    val transformationTable = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/|()1{}[]?-_+~<>i!lI;:,\"^`'. "
    val inputPixels = ArrayBuffer(
      ArrayBuffer(greyColor(64))
    )
    val image = basicImage[greyColor](inputPixels)
    val transformer = linearGreyToAsciiTransformer(transformationTable)

    val outputImage = transformer.transform(image)

    val expectedChar = transformationTable(64 * (transformationTable.length-1) / 255 )

    assert(outputImage.getPixel(0, 0).getVal == expectedChar)
  }

  test("linearGreyToAsciiTransformer should handle an empty image") {
    val transformationTable = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/|()1{}[]?-_+~<>i!lI;:,\"^`'. "
    val inputPixels = ArrayBuffer[ArrayBuffer[greyColor]]()
    val image = basicImage[greyColor](inputPixels)
    val transformer = linearGreyToAsciiTransformer(transformationTable)

    val outputImage = transformer.transform(image)

    assert(outputImage.getHeight() == 0)
    assert(outputImage.getWidth() == 0)
  }
}
