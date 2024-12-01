package transformer

import models.{basicImage, rgbColor}

class rgbToGreyTransformer extends baseTransformer[rgbColor, Int]{
  override def transform(image : basicImage[rgbColor]) : basicImage[Int] = {
    image.map[Int](x => x.getGreyScale)
  }
}
