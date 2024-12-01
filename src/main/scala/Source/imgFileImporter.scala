package Source
import models.{basicImage, rgbColor}

import java.awt.Color
import java.io.File
import javax.imageio.ImageIO
import scala.collection.mutable.ArrayBuffer
class imgFileImporter(fileDir : String) extends ImgSource {
  override def load(): basicImage[rgbColor] = {
    var imgFile = new File(fileDir)
    var image = ImageIO.read(imgFile)
    javaToInternal.convertJavatoInternal(image)
  }
}
