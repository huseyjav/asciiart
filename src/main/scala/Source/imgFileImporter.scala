package Source
import models.{basicImage, rgbColor}

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import scala.collection.mutable.ArrayBuffer
class imgFileImporter(fileDir : String) extends ImgSource {
  def convertJavatoInternal(javaVersion: BufferedImage): basicImage[rgbColor] = {
    var convertedImage = ArrayBuffer[ArrayBuffer[rgbColor]]()

    for (y <- 0 until javaVersion.getHeight) {
      var row = ArrayBuffer[rgbColor]()
      for (x <- 0 until javaVersion.getWidth) {
        val colors = new Color(javaVersion.getRGB(x, y))
        row += rgbColor(colors.getRed, colors.getGreen, colors.getBlue)
      }
      convertedImage += row
    }
    basicImage[rgbColor](convertedImage)
  }
  override def load(): basicImage[rgbColor] = {
    if(!fileDir.endsWith(".png") && !fileDir.endsWith(".jpg")) throw IllegalArgumentException("wrong file format")
    var imgFile = new File(fileDir)
    var image = ImageIO.read(imgFile)
    convertJavatoInternal(image)
  }
}
