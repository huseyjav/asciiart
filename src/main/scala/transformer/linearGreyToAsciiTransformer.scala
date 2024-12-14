package transformer

import models.{asciiColor, basicImage, greyColor}

class linearGreyToAsciiTransformer(transformationTable : String) extends greyToAsciiTransformer{
  override def transform(image: basicImage[greyColor]): basicImage[asciiColor] = {
    image.map[asciiColor](x => {
      asciiColor(transformationTable(x.getVal * (transformationTable.length-1) / 255 ))
    })
  }
  def getTransformationTable : String = transformationTable
}
