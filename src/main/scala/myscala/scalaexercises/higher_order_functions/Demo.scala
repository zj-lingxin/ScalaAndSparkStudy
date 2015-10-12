package myscala.scalaexercises.higher_order_functions

/**
 * Created by lingx on 2015/9/8.
 */
object Demo {
  //函数中返回函数
  def addWithoutSyntaxSugar(x: Int) = {
    //只带有一个参数的函数
    new Function1[Int, Int]() {
      def apply(y: Int): Int = x + y
    }
  }

  //在函数中使用匿名函数来返回一个函数
  def addWithoutSyntaxSugar2(x: Int) = (y: Int) => x + y

  def multiply(x: Int, y: Int) = x * y

  def main(args: Array[String]) {
    val r1 = addWithoutSyntaxSugar(1).isInstanceOf[Function1[Int, Int]]

    val r2 = addWithoutSyntaxSugar(2)(3)

    def fiveAdder = addWithoutSyntaxSugar(5)
    val r3 = fiveAdder(1)

    val r4 = addWithoutSyntaxSugar2(2)(3)

    val r5 = (multiply _).isInstanceOf[Function2[_, _, _]]

  }
}
