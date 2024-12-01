package exporter

import models.basicImage

trait basicExport[T] {
  def exportSource(source : basicImage[T]): Unit
}
