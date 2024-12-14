package UserInterface

import Source.ImgSource
import exporter.basicExport
import filter.baseFilter
import models.{asciiColor, greyColor}
import transformer.{greyToAsciiTransformer, rgbToGreyTransformer}

import scala.collection.mutable.ArrayBuffer

trait baseInterface {
  def getSource : ImgSource
  def getFilters : ArrayBuffer[baseFilter[greyColor]]
  def getRgbToGreyTransformer : rgbToGreyTransformer
  def getGreyToAsciiTransformer : greyToAsciiTransformer
  def getExport : ArrayBuffer[basicExport[asciiColor]]

}
