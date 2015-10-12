package myscala.scala_for_the_impatient.implicits

/**
 * 7、上下文界定
 * 类型参数可以有一个形式为T:M的上下文界定，其中M是另一个泛型类型。
 * 它要求作用域存在一个类型为M[T]的隐式值。
 * 例如：
 * class Pair[T : Ordering]
 * 要求存在一个类型为Ordering[T]的隐式值。该隐式值可以被用在该类的方法中。
 */

// 要求存在一个Ordering[T]的隐式值
// 如果我们new一个Pair(3,4)，编译器将推断出我们需要一个Pair[Int]。
// 由于Predef作用域中已经有一个类型为Ordering[Int]的隐式值，因此Int满足上下文界定。
// 这个Ordering[Int]就“成为该类的一个字段”，被传入需要该值的方法中。
class Pair[T: Ordering](val first: T, val second: T) {
  def smaller(implicit ord: Ordering[T]) = {
    if (ord.compare(first, second) < 0) first else second
  }
}

//如果不使用T:Ordering这个上下文界定，那么是不是可以写成下面的样子：
class PairNotUseContextBound[T <% Ordered[T]](val first: T, val second: T) {
  //作用域中存在一个Ordering[T],相当于该类的一个字段。
  //这个ordering相当于上面Pair的作用域中的Ordering[T],Pair中的那个Ordering[T]相当于一个隐藏的字段。
  private val ordering = new Ordering[T] {
    override def compare(x: T, y: T): Int = if (x > y) 1 else -1
  }

  def smaller: T = smaller(ordering)

  def smaller(ord: Ordering[T]): T = {
    if (ord.compare(first, second) < 0) first else second
  }
}

//如果你愿意，也可以使用Predef类的implicitly方法获取值：
class PairUseImplicitly[T: Ordering](val first: T, val second: T) {
  //但是这样的话，就不能显示地传入Ordering[T]这个对象了。
  def smaller = if (implicitly[Ordering[T]].compare(first, second) < 0) first else second
}

// 或者，你也可以利用Ordered特质中定义的从Ordering到Ordered的隐式转换，一旦引入了这个转换，就可以使用关系操作符了(《快学Scala》的原文)
// 感觉也可以理解成“从T到Ordered[T]的隐式转换”，因为这个隐式转换，需要一个参数T和一个隐式参数Ordering[T])
// 内部的工作机制其实是这样的：
// 在下例中，import Ordered._ 其实用到的是 import Ordered.orderingToOrdered
// 我们再看一下orderingToOrdered这个隐式转换：
/*
object Ordered {
  implicit def orderingToOrdered[T](x: T)(implicit ord: Ordering[T]): Ordered[T] =
    new Ordered[T] { def compare(that: T): Int = ord.compare(x, that) }
}
 */
// 在Ordered的伴生对象中定义了一个隐式转换：orderingToOrdered，它将T类型转换成Ordered[T]类型，
// 同时这个隐式转换还带了一个隐式参数，它的类型是Ordering[T]。PairImportOrdered[T: Ordering]的上下文界定
// 确保必须在作用域中存在一个Ordering[T]的隐式值，而这个隐式值会传入到orderingToOrdered的隐式参数中。
// 这样就把 T转换成了Ordered[T],而Ordered[T]中定义了关系操作符，所以就可以使用“<”了
class PairImportOrdered[T: Ordering](val first: T, val second: T) {
  def smaller = {
    import Ordered._
    //import Ordered.orderingToOrdered
    if (first < second) first else second
  }
}

class Point(val x: Double, val y: Double){
  override def toString: String = s"(${x}, ${y})"
}

object Point {
  //注意：下面两种写法都可以作为Ordering[Point]类型的隐式值
  //写法一
  implicit val pointOrdering: Ordering[Point] = new Ordering[Point] {
    override def compare(a: Point, b: Point): Int =
      if ((math.pow(a.x, 2) + math.pow(a.y, 2)) > (math.pow(b.x, 2) + math.pow(b.y, 2))) 1 else -1
  }
  //写法二
  implicit object PointOrdering extends Ordering[Point]{
    override def compare(a: Point, b: Point): Int =
      if ((math.pow(a.x, 2) + math.pow(a.y, 2)) > (math.pow(b.x, 2) + math.pow(b.y, 2))) 1 else -1
  }
}

object ContextBound {
  def main(args: Array[String]) {
    //smaller使用的是Predef中定义的Ordering[Int]的这个隐式值
    /*   println(new Pair(3, 4).smaller) //ok
       println(new PairNotUseContextBound(3, 4).smaller) //ok
       println(new PairUseImplicitly(3, 4).smaller)*/
    println(new PairImportOrdered(3, 4).smaller)
    //smaller使用的是自己定义的order这个Ordering[Int]的隐式值
    val order = new Ordering[Int] {
      override def compare(x: Int, y: Int): Int = if (x > y) 1 else -1
    }
    new Pair(4, 3).smaller(order) //ok

    //Point的伴生对象中有一个Ordering[Point]类型的隐式值(二选一即可)，所以我们可以将Point传入到Pair中
    println(new Pair(new Point(4, 2),new Point(3, 2)).smaller)
  }
}
