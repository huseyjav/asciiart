package models

import scala.collection.mutable.ArrayBuffer

class basicImage[T] (image: ArrayBuffer[ArrayBuffer[T]]) {
  def getPixel(x: Int, y: Int): T = {
    image(y)(x)
  }
  def getHeight() : Int = {
    image.length
  }
  def getWidth() : Int = {
    image(0).length
  }
  def getRow(i : Int) : ArrayBuffer[T] = {
    image(i)
  }
  def getColumn(i : Int) : ArrayBuffer[T] = {
    var column = ArrayBuffer[T]()
    for(y <- 0 until getHeight()){
      column += image(y)(i)
    }
    column
  }
  def map[N](func : T => N) : basicImage[N] = {
    basicImage[N](image.map[ArrayBuffer[N]](y => y.map[N](func)))
  }
}
