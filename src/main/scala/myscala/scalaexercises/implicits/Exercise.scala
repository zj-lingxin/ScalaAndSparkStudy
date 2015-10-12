package myscala.scalaexercises.implicits

/**
 * Created by lingx on 2015/9/10.
 */

object T1 {
   abstract class RichNumber[T] {
    def addTwo(a: T, b: T): T
  }

  implicit object IntNumber extends RichNumber[Int] {
    override def addTwo(a: Int, b: Int): Int = a + b
  }

  implicit object DoubleNubmer extends RichNumber[Double] {
    override def addTwo(a: Double, b: Double): Double = a + b
  }

  def add[T](value: T, otherValue: T)(implicit rn: RichNumber[T]) = {
    rn.addTwo(value, otherValue)
  }
}
//快学scala 第21章 练习 第2题
object T2 {
  abstract class NumberFunction[T] {
    def add(a: T, b: T): T
    def multiply(a: T, b: T): T
  }

  implicit object DoubleFunction extends NumberFunction[Double] {
    override def add(a: Double, b: Double): Double = a + b
    override def multiply(a: Double, b: Double): Double = a * b
  }

  implicit object IntFunction extends NumberFunction[Int] {
    override def add(a: Int, b: Int): Int = a + b
    override def multiply(a: Int, b: Int): Int = a * b
  }

  class Operators[T](value:T) {
    def +%(rate: T)(implicit n: NumberFunction[T]) = {
      //value * rate * 0.01 + value
      n.add(n.multiply(value,rate),value)
    }

  }
  implicit def useOperators[T](v: T) = new Operators[T](v)
}

object Main {
  def main(args: Array[String]) {
    import myscala.scalaexercises.implicits.T1._
    println(add(11.1,2.2))
    println(add(1,1))
    import  myscala.scalaexercises.implicits.T2._
    println(120 +% 10)

  }
}