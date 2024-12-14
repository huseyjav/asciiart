package filter
import models.{basicImage, greyColor}

import scala.collection.mutable.ArrayBuffer

class brightnessFilter(brightnessDelta : Int) extends baseFilter[greyColor]{
  private def normalize(x : Int) : Int = {
    if(x>255) return 255
    else if(x<0) return 0
    x
  }
  override def applyFilter(image: basicImage[greyColor]): basicImage[greyColor] = {
    var newImage = ArrayBuffer[ArrayBuffer[greyColor]]()
    for (y <- 0 until image.getHeight()) {
      var row = ArrayBuffer[greyColor]()
      for (x <- 0 until image.getWidth()) {
        row += greyColor(normalize(image.getPixel(x, y).getVal + brightnessDelta))
      }
      newImage += row
    }
    basicImage[greyColor](newImage)
  }
  def getBrightness : Int = brightnessDelta
}
