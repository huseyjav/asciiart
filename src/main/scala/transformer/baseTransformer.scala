package transformer

import models.basicImage

trait baseTransformer[A,B] {
  def transform(image : basicImage[A]) : basicImage[B]
}
