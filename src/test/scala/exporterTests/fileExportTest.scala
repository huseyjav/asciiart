package exporterTests

import exporter.fileExport
import models.{asciiColor, basicImage}
import org.scalatest.funsuite.AnyFunSuite

import java.io.File
import scala.collection.mutable.ArrayBuffer

class fileExportTest extends AnyFunSuite{
  test("fileExport should correctly write a serialized basicImage to a file") {
    val pixels = ArrayBuffer(
      ArrayBuffer(asciiColor('a'), asciiColor('b'), asciiColor('c')),
      ArrayBuffer(asciiColor('d'), asciiColor('e'), asciiColor('f')),
      ArrayBuffer(asciiColor('g'), asciiColor('h'), asciiColor('i'))
    )
    val image = basicImage[asciiColor](pixels)
    val fileName = "test_output.txt"

    val exporter = fileExport(fileName)
    exporter.exportSource(image)

    val expectedContent = "abc\ndef\nghi\n"
    val fileContent = scala.io.Source.fromFile(fileName).getLines().mkString("\n") + "\n"
    assert(fileContent == expectedContent)

    File(fileName).delete()
  }

  test("fileExport should handle an empty basicImage") {
    val emptyImage = basicImage[asciiColor](ArrayBuffer())
    val fileName = "empty_output.txt"

    val exporter = fileExport(fileName)
    exporter.exportSource(emptyImage)

    val expectedContent = ""
    val fileContent = scala.io.Source.fromFile(fileName).getLines().mkString("\n")

    assert(fileContent == expectedContent)

    File(fileName).delete()
  }
}
