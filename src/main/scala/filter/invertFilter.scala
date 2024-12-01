package filter
import models.basicImage

import scala.collection.mutable.ArrayBuffer

class invertFilter extends baseFilter[Int]{
  override def applyFilter(image: basicImage[Int]): basicImage[Int] = {
    var invertedImage = ArrayBuffer[ArrayBuffer[Int]]()
    for (y <- 0 until image.getHeight()) {
      var row = ArrayBuffer[Int]()
      for (x <- 0 until image.getWidth()) {
        row += 255-image.getPixel(x, y)
      }
      invertedImage+=row
    }
    basicImage[Int](invertedImage)
  }

}
