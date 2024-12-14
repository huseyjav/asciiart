package filter
import models.{basicImage, greyColor}

import scala.collection.mutable.ArrayBuffer

class flipFilter(isFlipOnX : Boolean) extends baseFilter[greyColor]{

  def getisFlipOnX : Boolean = isFlipOnX

  private def flipOnX(image : basicImage[greyColor]): basicImage[greyColor] = {
    var flipped = ArrayBuffer[ArrayBuffer[greyColor]]()
    for (x <- 0 until image.getWidth()) {
      flipped += image.getColumn(x).reverse
    }
    basicImage[greyColor](flipped.transpose)
  }

  private def flipOnY(image: basicImage[greyColor]): basicImage[greyColor] = {
    var flipped = ArrayBuffer[ArrayBuffer[greyColor]]()
    for (y <- 0 until image.getHeight()) {
      flipped += image.getRow(y).reverse
    }
    basicImage[greyColor](flipped)
  }
  override def applyFilter(image: basicImage[greyColor]): basicImage[greyColor] = {
    if(isFlipOnX) return flipOnX(image)
    return flipOnY(image)
  }

}
