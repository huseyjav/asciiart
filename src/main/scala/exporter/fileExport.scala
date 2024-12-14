package exporter

import models.{asciiColor, basicImage}

import java.io.{File, PrintWriter}

class fileExport(fileName : String) extends basicExport[asciiColor]{
  override def exportSource(source: basicImage[asciiColor]): Unit = {
    val writer = new PrintWriter(new File(fileName))
    try writer.print(serializer[asciiColor]().serialize(source))
    finally writer.close()
  }
}
