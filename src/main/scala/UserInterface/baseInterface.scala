package UserInterface

import Source.ImgSource
import exporter.basicExport
import filter.baseFilter
import transformer.{greyToAsciiTransformer, rgbToGreyTransformer}

import scala.collection.mutable.ArrayBuffer

trait baseInterface {
  def getSource : ImgSource
  def getFilters : ArrayBuffer[baseFilter[Int]]
  def getrgbToGreyTransformer : rgbToGreyTransformer
  def getGreyToAsciiTransformer : greyToAsciiTransformer
  def getExport : ArrayBuffer[basicExport[Char]]

}
