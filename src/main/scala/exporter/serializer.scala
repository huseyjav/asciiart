package exporter

import models.basicImage

class serializer[T]{
  def serialize(image : basicImage[T]) : String = {
    var toReturn = String()
    for (y <- 0 until image.getHeight()) {
      for (x <- 0 until image.getWidth()) {
        toReturn += image.getPixel(x, y)
      }
      toReturn += '\n'
    }
    toReturn
  }
}
