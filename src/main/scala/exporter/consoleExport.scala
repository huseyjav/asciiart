package exporter

import models.{asciiColor, basicImage}

class consoleExport extends basicExport[asciiColor]{
  override def exportSource(source: basicImage[asciiColor]): Unit = {
    print(serializer[asciiColor]().serialize(source))
  }
}
