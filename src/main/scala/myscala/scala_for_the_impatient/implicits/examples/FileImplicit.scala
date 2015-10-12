package myscala.scala_for_the_impatient.implicits.examples

import java.io.File
import scala.io.Source

/**
 * 隐式转换的例子。
 * 将隐式转换函数放在伴生对象中，这样就不需要import了
 */
class RichFile(file: File) {
  def read = Source.fromFile(file.getPath).mkString
}

class FileImplicit(path: String) extends File(path)

object FileImplicit {
  implicit def file2RichFile(file: File) = new RichFile(file)
}

object Main extends App {
  new FileImplicit("D://words.txt").read
}
