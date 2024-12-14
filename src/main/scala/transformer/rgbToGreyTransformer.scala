package transformer

import models.{basicImage, rgbColor, greyColor}

class rgbToGreyTransformer extends baseTransformer[rgbColor, greyColor]{
  private def getGreyScale(x : rgbColor) : greyColor = greyColor(((0.3 * x.getR) + (0.59 * x.getG) + (0.11 * x.getB)).toInt)
  override def transform(image : basicImage[rgbColor]) : basicImage[greyColor] = {
    image.map[greyColor](x => getGreyScale(x))
  }
}
