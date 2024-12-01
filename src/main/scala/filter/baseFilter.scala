package filter

import models.basicImage

trait baseFilter [T]{
  def applyFilter(image : basicImage[T]) : basicImage[T]
}
