package myscala.scala_for_the_impatient.implicits

/**
 * 5、隐式参数
 * 函数或方法可以带有一个标记为implicit的参数列表。这种情况下，编译器会查找缺省值，提供给该
 * 函数或方法。
 *
 * 6、利用隐式参数进行隐式转换
 * 隐式的函数参数也可以被用作隐式转换。
 */

//############ 例6 隐式参数 ###############
case class Delimiters(left: String, right: String)
object FrenchPunctuation {
  implicit val quoteDelimiters = new Delimiters("<",">")
  implicit val quoteLeft = "<"
  // ...
}

object ImplicitsParams {
  //这个方法是柯里化的，并且该方法带了一个隐式参数。当隐式参数缺省时，会去查找相同类型的带有implicit的值，作为这个值。
  def quote(what: String)(implicit delims: Delimiters) = {
    delims.left + what + delims.right
  }
  def quoteThree(what: String, delims: Delimiters = new Delimiters("<",">")) = {
    delims.left + what + delims.right
  }

  //对于给定的数据类型，只能有一个隐式的值。因此使用常用类型的隐式参数并不是一个好主意。例如：
/*  def quoteTwo(what: String)(implicit left: String,right: Right) = {  //别这样做！！
    left + what + right
  }*/
  //上面的代码行不通，因为没法提供两个不同的字符串。

  def main(args: Array[String]) {
    //测试例6
    //在quote方法中你可以显示地传入一个Delimiters对象
    val q1 = quote("Hadoop")(new Delimiters("<", ">"))
    println(q1) // <<Hadoop>>

    //也可以省略隐式参数列表,直接写成quote("Hadoop")，这时编译器将会查找一个类型为Delimiters的隐式值。
    //这必须是一个被声明为implicit的值。编译器将会在如下两个地方查找这样的一个对象：
    //1、在当前作用域所有可以用单个标识符指代的满足类型要求的val和def
    //2、与所有要求类型相关联的类型的伴生对象。相关联的类型包括所要求类型本身，以及它的类型参数
    // (如果它是一个参数化的类型的话)
    //引入所有值import FrenchPunctuation._，引入特定值import FrenchPunctuation.quoteDelimiters
    import FrenchPunctuation._
    println(quote("Spark"))
    println(quote("Spark")(new Delimiters("@","!")))

    println(quoteThree("Spark"))
    println(quoteThree("Spark",new Delimiters("@","!")))

  }
}

//############ 例6 结束 ###################

//############ 例7 利用隐式参数进行隐式转换 #############
object ImplicitParamsAsConvert{
  // 以下函数行不通
  // def smaller[T](a:T, b:T) = if(a < b) a else b //error
  // 之前已经学过，可以使用上界或者视图界定来完成该功能,如下使用视图界定来实现
  def smallerWithViewBound[T <% Ordered[T]](a: T, b: T) = if(a < b) a else b

  // 上面是一种解决方法，使用转换函数也可以来达到这个目的：
  // 下面方法的第二个参数是隐式参数，但是所要提供的隐式值其实又是一个隐式转换(函数)，
  // 它将T类型转换成Ordered[T]
  // ord(a) < b 中的"<"是Ordered[T]中提供的方法，这个方法是：
  // def < (that: A): Boolean = (this compare that) <  0
  // "<"是Orderd[A]对象的成员，而"<"这个方法中传入的参数的类型是A
  // 所以ord(a) < b 可以运行。
  // 那么ord这个隐式参数的隐式值定义在哪里呢？它其实定义在Predef对象中，
  // Predef对象会自动被import。Predef中定义了一些隐式转换函数，例如有：
  // implicit def intWrapper(x: Int) = new runtime.RichInt(x)
  // 它会将Int转化成RichInt，而RichInt中混入了Ordered[T]这个特质，所以可以使用"<"
  def smaller[T](a: T, b: T)(implicit ord: T => Ordered[T]) = {
    //if(ord(a) < b) a else b
    //由于这边已经提供了一个隐式转换函数 ord: T => Ordered[T] ，所以ord(a)可以直接写成a,a会自动调用ord(a)
    //如下：
    if(a < b) a else b
    //《快学scala》原文：ord是一个带单个参数的函数，被打上了implicit标签，并且有一个以单个标识符出现的名称。
    //因此，它不仅是一个隐式参数，还是一个隐式转换。正因为这样，我们可以在函数体中省去对ord的调用。
  }

  def main(args: Array[String]) {
    println(smallerWithViewBound(1,3))
    smaller(1,3)

    //如果想成功运行smaller(new Fraction(88,77), new Fraction(99,88))该怎么做？
    //需要自己定义一个Fraction => Ordered[Fraction]的函数
    import myscala.scala_for_the_impatient.implicits.Fraction._
    println(smaller(new Fraction(99,88), new Fraction(88,77)))
  }
}
//####################  例7 结束 ########################