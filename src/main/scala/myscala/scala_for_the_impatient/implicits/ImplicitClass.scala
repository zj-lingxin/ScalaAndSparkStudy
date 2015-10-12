package myscala.scala_for_the_impatient.implicits

import java.io.File
import scala.io.Source

/**
 * 隐式类
 */
object ContextHelper {
  //隐式类，作用其实也是可以由隐式转换来完成的
  implicit class FileRich(file: File) {
    def read = Source.fromFile(file.getPath).mkString
  }
  implicit class Op(x: Int) {
    def add(second: Int) = x + second
  }
  //隐式对象,没有构造器。可以继承一个父类，然后把它当成隐式值来使用
  implicit object PointOrdering extends Ordering[Point]{
    override def compare(a: Point, b: Point): Int =
      if ((math.pow(a.x, 2) + math.pow(a.y, 2)) > (math.pow(b.x, 2) + math.pow(b.y, 2))) 1 else -1
  }
  //隐式值，功能跟上面的隐式对象是一样的
  implicit val pointOrdering: Ordering[Point] = new Ordering[Point] {
    override def compare(a: Point, b: Point): Int =
      if ((math.pow(a.x, 2) + math.pow(a.y, 2)) > (math.pow(b.x, 2) + math.pow(b.y, 2))) 1 else -1
  }
}

object ImplicitClass {
  def main(args: Array[String]) {
    import ContextHelper._
    println(1.add(2))
    println(new File("D:\\word.txt").read)
  }
}

