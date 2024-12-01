package Source

import models.{basicImage, rgbColor}
import java.awt.image.BufferedImage
import scala.collection.mutable.ArrayBuffer
import java.awt.Color

object javaToInternal {
  def convertJavatoInternal(javaVersion : BufferedImage): basicImage[rgbColor] = {
    var convertedImage = ArrayBuffer[ArrayBuffer[rgbColor]]()

    for(y <- 0 until javaVersion.getHeight){
      var row = ArrayBuffer[rgbColor]()
      for(x <- 0 until javaVersion.getWidth){
        val colors= new Color(javaVersion.getRGB(x,y))
        row += rgbColor(colors.getRed, colors.getGreen, colors.getBlue)
      }
      convertedImage+=row
    }
    basicImage[rgbColor](convertedImage)
  }
}
