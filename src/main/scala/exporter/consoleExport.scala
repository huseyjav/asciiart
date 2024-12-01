package exporter

import models.basicImage

class consoleExport extends basicExport[Char]{
  override def exportSource(source: basicImage[Char]): Unit = {
    for (y <- 0 until source.getHeight()) {
      for (x <- 0 until source.getWidth()) {
        print(source.getPixel(x, y))
      }
      print('\n')
    }
  }
}
