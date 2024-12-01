package Source

import models.{basicImage, rgbColor}

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class imgGenerator extends ImgSource{
  override def load(): basicImage[rgbColor] = {
    // TODO

    var generated = ArrayBuffer[ArrayBuffer[rgbColor]]()
    var width = Random.between(100,500)
    var height = Random.between(100,500)
    for(y <- 0 until height){
      var row = ArrayBuffer[rgbColor]()
      for(x <- 0 until width){
        row += rgbColor(Random.between(0,255),Random.between(0,255),Random.between(0,255))
      }
      generated += row
    }
    basicImage[rgbColor](generated)
  }
}
