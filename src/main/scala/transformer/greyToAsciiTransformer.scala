package transformer
import models.basicImage

class greyToAsciiTransformer(transformationTable : String) extends baseTransformer[Int,Char]{
  override def transform(image: basicImage[Int]): basicImage[Char] = {
    image.map[Char](x => {
      val pixelsKey = 255.0 / transformationTable.length
      transformationTable(Math.floor(x * pixelsKey).toInt % transformationTable.length)
    })
  }
}
