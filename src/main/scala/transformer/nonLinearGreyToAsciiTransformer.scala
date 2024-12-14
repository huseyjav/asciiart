package transformer

import models.{asciiColor, basicImage, greyColor}

class nonLinearGreyToAsciiTransformer extends greyToAsciiTransformer {
  override def transform(image: basicImage[greyColor]): basicImage[asciiColor] = {
    image.map[asciiColor](x => {
      if(x.getVal>=0 && x.getVal<200) asciiColor('@')
      else if(x.getVal<220) asciiColor('+')
      else if(x.getVal<230) asciiColor('-')
      else asciiColor('.')
    })
  }
}
