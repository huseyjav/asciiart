package exporterTests

import exporter.serializer
import models.basicImage
import org.scalatest.funsuite.AnyFunSuite

import scala.collection.mutable.ArrayBuffer

class serializerTest extends AnyFunSuite{
  test("serializer should correctly serialize a basicImage of integers") {
    val pixels = ArrayBuffer(
      ArrayBuffer(1, 2, 3),
      ArrayBuffer(4, 5, 6),
      ArrayBuffer(7, 8, 9)
    )
    val image = basicImage[Int](pixels)
    val cserializer = serializer[Int]()

    val expected = "123\n456\n789\n"
    assert(cserializer.serialize(image) == expected)
  }

  test("serializer should correctly serialize a basicImage of characters") {
    val pixels = ArrayBuffer(
      ArrayBuffer('a', 'b', 'c'),
      ArrayBuffer('d', 'e', 'f'),
      ArrayBuffer('g', 'h', 'i')
    )
    val image = basicImage[Char](pixels)
    val cserializer = serializer[Char]()

    val expected = "abc\ndef\nghi\n"
    assert(cserializer.serialize(image) == expected)
  }

  test("serializer should handle an empty basicImage") {
    val pixels = ArrayBuffer[ArrayBuffer[Int]]()
    val image = basicImage[Int](pixels)
    val cserializer = serializer[Int]()

    val expected = ""
    assert(cserializer.serialize(image) == expected)
  }

  test("serializer should correctly serialize a basicImage with single pixel") {
    val pixels = ArrayBuffer(
      ArrayBuffer(42)
    )
    val image = new basicImage[Int](pixels)
    val cserializer = serializer[Int]()

    val expected = "42\n"
    assert(cserializer.serialize(image) == expected)
  }
}
