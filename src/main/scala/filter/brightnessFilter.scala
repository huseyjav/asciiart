package filter
import models.basicImage

import scala.collection.mutable.ArrayBuffer

class brightnessFilter(brightnessDelta : Int) extends baseFilter[Int]{
  def normalize(x : Int) : Int = {
    if(x>255) return 255
    else if(x<0) return 0
    x
  }
  override def applyFilter(image: basicImage[Int]): basicImage[Int] = {
    var newImage = ArrayBuffer[ArrayBuffer[Int]]()
    for (y <- 0 until image.getHeight()) {
      var row = ArrayBuffer[Int]()
      for (x <- 0 until image.getWidth()) {
        row += normalize(image.getPixel(x, y) + brightnessDelta)
      }
      newImage += row
    }
    basicImage[Int](newImage)
  }
}
