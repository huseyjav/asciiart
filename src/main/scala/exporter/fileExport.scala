package exporter

import models.basicImage

import java.io.{File, PrintWriter}

class fileExport(fileName : String) extends basicExport[Char]{
  override def exportSource(source: basicImage[Char]): Unit = {
    val writer = new PrintWriter(new File(fileName))
    for (y <- 0 until source.getHeight()) {
      for (x <- 0 until source.getWidth()) {
        writer.print(source.getPixel(x, y))
      }
      writer.print('\n')
    }
  }
}
