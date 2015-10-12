// A method with implicit parameters can be applied to arguments just like a normal method. In this case the implicit
// label has no effect. However, if such a method misses arguments for its implicit parameters, such arguments will
// be automatically provided.
//The actual arguments that are eligible to be passed to an implicit parameter fall into two categories: First,
// eligible are all identifiers x that can be accessed at the point of the method call without a prefix and that
// denote an implicit definition or an implicit parameter. Second, eligible are also all members of companion modules
// of the implicit parameter’s type that are labeled implicit.

//翻译：
//一个方法中如果带implicit参数，这个参数可以像正常方法的参数那样使用。这时，implicit标签不会起到任何作用。然而，如果这个
//方法中的implicit参数没有加，这个参数会自动地被提供。
//可以传入到implicit参数中的那些实参可以分为两类：

// In the following example we define a method sum which computes the sum of a list of elements using the monoid’s
// add and unit operations. Please note that implicit values can not be top-level, they have to be members of a template.
abstract class SemiGroup[A] {
  def add(x: A, y: A): A
}

abstract class Monoid[A] extends SemiGroup[A] {
  def unit: A
}

implicit object StringMonoid extends Monoid[String] {
  def add(x: String, y: String): String = x concat y
  def unit: String = ""
}

implicit object IntMonoid extends Monoid[Int] {
  def add(x: Int, y: Int): Int = x + y
  def unit: Int = 0
}

def sum[A](xs: List[A])(implicit m: Monoid[A]): A =
  if (xs.isEmpty) m.unit
  else m.add(xs.head, sum(xs.tail))
println(sum(List(1, 2, 3)))
println(sum(List("a", "b", "c")))
//Implicits wrap around existing classes to provide extra functionality. This is similar to monkey patching in Ruby,
// and Meta-Programming in Groovy.
//Creating a method isOdd for Int, which doesn't exist
class KoanIntWrapper(val original: Int) {
  def isOdd = original % 2 != 0
  def isEven = original % 2 == 0
}

implicit def thisMethodNameIsIrrelevant(value: Int) = new KoanIntWrapper(value)

1.isOdd
1.isEven
//Implicits can be used to automatically convert one type to another
import java.math.BigInteger
implicit def Int2BigIntegerConvert(i: Int) = new BigInteger(i.toString)
def add(a: BigInteger, b: BigInteger) = a.add(b)
add(1,2)
add(Int2BigIntegerConvert(3), Int2BigIntegerConvert(6)) == Int2BigIntegerConvert(9)
add(3, 6) == 9
add(3, 6) == Int2BigIntegerConvert(9)
add(3, 6) == (9:BigInteger)
add(3, 6).intValue == 9
//Implicits can be used declare a value to be provided as a default as long as an implicit value is set with in the
// scope. These are called implicit function parameters:
def howMuch(hours: Int)(implicit dollarsPerHour: BigDecimal) = {
  println("调用 隐式参数")
  dollarsPerHour * hours
}
//下面这句implicit和BigDecimal决定了可以作为上面隐式函数的参数
implicit val hourlyRate = BigDecimal(2.0)
//Default arguments though are preferred to Implicit Function Parameters
def howMuch(hours: Int,dollarsPerHour: BigDecimal = 3.0, currencyName: String = "Dollars") = {
  println("调用 默认参数")
  dollarsPerHour * hours
}

howMuch(3) //"调用 隐式参数"
howMuch(3,4) //调用 默认参数

