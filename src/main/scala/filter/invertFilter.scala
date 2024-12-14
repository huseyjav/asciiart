package filter
import models.{basicImage, greyColor}

import scala.collection.mutable.ArrayBuffer
class invertFilter extends baseFilter[greyColor]{
  override def applyFilter(image: basicImage[greyColor]): basicImage[greyColor] = {
    var invertedImage = ArrayBuffer[ArrayBuffer[greyColor]]()
    for (y <- 0 until image.getHeight()) {
      var row = ArrayBuffer[greyColor]()
      for (x <- 0 until image.getWidth()) {
        row += greyColor(255-image.getPixel(x, y).getVal)
      }
      invertedImage+=row
    }
    basicImage[greyColor](invertedImage)
  }
}
