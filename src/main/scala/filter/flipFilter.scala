package filter
import models.basicImage

import scala.collection.mutable.ArrayBuffer

class flipFilter(isFlipOnX : Boolean) extends baseFilter[Int]{

  def flipOnX(image : basicImage[Int]): basicImage[Int] = {
    var flipped = ArrayBuffer[ArrayBuffer[Int]]()
    for (x <- 0 until image.getWidth()) {
      flipped += image.getColumn(x).reverse
    }
    basicImage[Int](flipped.transpose)
  }

  def flipOnY(image: basicImage[Int]): basicImage[Int] = {
    var flipped = ArrayBuffer[ArrayBuffer[Int]]()
    for (y <- 0 until image.getHeight()) {
      flipped += image.getRow(y).reverse
    }
    basicImage[Int](flipped)
  }
  override def applyFilter(image: basicImage[Int]): basicImage[Int] = {
    if(isFlipOnX) return flipOnX(image)
    return flipOnY(image)
  }

}
