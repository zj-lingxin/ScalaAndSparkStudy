package myscala.scala_for_the_impatient.implicits

/**
 * 隐式对象
 */
abstract class Template[A] {
  def add(x: A, y: A): A
}

abstract class SubTemplate[A] extends Template[A] {
  def unit: A
}

object SubTemplate {
  implicit object StringTemplate extends SubTemplate[String] {
    override def unit: String = ""
    override def add(x: String, y: String): String = x concat y
  }

  implicit object IntTemplate extends SubTemplate[Int] {
    override def unit: Int = 0
    override def add(x: Int, y: Int): Int = x + y
  }
}

abstract class SubTemplateTwo[A] extends Template[A] {
  def unit: A
}

object SubTemplateTwo {
  //貌似与SubTemplate中的StringTemplate这个object功能一样
  implicit val stringTemplate = new SubTemplateTwo[String] {
    override def unit: String = ""
    override def add(x: String, y: String): String = x concat y
  }
  //貌似与SubTemplate中的IntTemplate这个object功能一样
  implicit val intTemplate = new SubTemplateTwo[Int] {
    override def unit: Int = 0
    override def add(x: Int, y: Int): Int = x + y
  }
}

object ImplicitObject {
  def main(args: Array[String]) {
    def sum[A](xs: List[A])(implicit m: SubTemplate[A]): A =
      if (xs.isEmpty) m.unit else m.add(xs.head, sum(xs.tail))
    //sum方法在SubTemplate伴生对象中找到 SubTemplate[Int]这个值(对象)，即IntTemplate。所以m就是IntTemplate
    println(sum(List(1, 2, 3)))
    //sum方法在SubTemplate伴生对象中找到 SubTemplate[String]这个值(对象)，即StringTemplate。所以m就是StringTemplate
    println(sum(List("I", " am", " lucius")))

    def sumTwo[A](xs: List[A])(implicit m: SubTemplateTwo[A]): A =
      if (xs.isEmpty) m.unit else m.add(xs.head, sumTwo(xs.tail))
    println(sumTwo(List(1, 2, 3)))
    println(sumTwo(List("I", " am", " lucius")))
  }
}
