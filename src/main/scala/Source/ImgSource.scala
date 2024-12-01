package Source

import models.basicImage
import models.rgbColor

trait ImgSource {
  def load() : basicImage[rgbColor]
}
