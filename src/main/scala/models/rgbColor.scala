package models

class rgbColor(r : Int, g : Int, b : Int){
  def getR : Int = r
  def getG : Int = g
  def getB : Int = b

  def getGreyScale : Int = ((0.3 * r) + (0.59 * g) + (0.11 * b)).toInt
}
