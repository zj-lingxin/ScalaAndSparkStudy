package myscala.scalaexercises.traits

/**
 * Created by lingx on 2015/9/10.
 */
//Similar to interfaces in Java, traits are used to define object types by specifying the signature of the supported
// methods. Unlike Java, Scala allows traits to be partially implemented; i.e. it is possible to define default
// implementations for some methods. In contrast to classes, traits may not have constructor parameters.

//例1：Here is an example:
//This trait consists of two methods isSimilar and isNotSimilar. While isSimilar does not provide a concrete method
// implementation (it is abstract in the terminology of Java), method isNotSimilar defines a concrete implementation.
// Consequently, classes that integrate this trait only have to provide a concrete implementation for isSimilar.
// The behavior for isNotSimilar gets inherited directly from the trait. Traits are typically integrated into a class
// (or other traits) with a mixin class composition:
trait Similarity {
  def isSimilar(x: Any): Boolean

  def isNotSimilar(x: Any): Boolean = !isSimilar(x)
}

class Point(var x: Int, var y: Int) extends Similarity {
  override def isSimilar(obj: Any): Boolean = {
    obj.isInstanceOf[Point] && (obj.asInstanceOf[Point].x == x)
  }
}

//例2：
case class Event(name: String)

trait EventListener {
  def listen(event: Event): String
}

class MyListener extends EventListener {
  override def listen(event: Event): String = {
    event match {
      case Event("发生雪崩") => "很不幸，某地发生雪崩"
      case _ => "没有发生什么重大的事"
    }
  }
}

//A class can only extend from one class or trait, any subsequent extension should use the keyword with:
//注意：如果有特质和类时，要把类放在extends后
class OurListener

class MyListener2 extends OurListener with EventListener {
  override def listen(event: Event): String = ???
}

//Traits can have concrete implementations that can be mixed into concrete classes with it's own state:
trait Logging {
  var logCache = List[String]()

  def log(value: String) = {
    logCache =  value +: logCache // +:的意思应该是将value这个String类型放入logCache(List)中后生成一个新的List
    println(value)
  }
}

class Welder extends Logging {
  def weld() {
    log("welding pipe") //英语：welding pipe 焊接管
  }
}

class Baker extends Logging {
  def bake(): Unit = {
    log("baking cake")
  }
}

//Traits are instantiated before a classes instantiation:
object T2 extends App {
  var sb = List[String]()

  class Cc1 extends Tt1 {
    sb = sb :+ "In Cc1: y=%s".format(y)
    val y = 2
    sb = sb :+ "In Cc1: y=%s".format(y)
  }

  trait Tt1 {
    sb = sb :+ "In Tt1: x=%s".format(x)
    val x = 1
    sb = sb :+ "In Tt1: x=%s".format(x)
  }

  sb = sb :+ "Creating Cc1"
  new Cc1
  sb = sb :+ "Creating Cc1"
  println(sb.mkString(";"))
}

object T3 extends App {

  var sb = List[String]()

  trait Tt1 {
    sb = sb :+ "In Tt1: x=%s".format(x)
    val x = 1
    sb = sb :+ "In Tt1: x=%s".format(x)
  }

  trait Tt2 {
    sb = sb :+ "In Tt2: z=%s".format(z)
    val z = 1
    sb = sb :+ "In Tt2: z=%s".format(z)
  }
  //Traits are instantiated before a classes instantiation from left to right:
  class Cc1 extends Tt2 with  Tt1{
    sb = sb :+ "In Cc1: y=%s".format(y)
    val y = 2
    sb = sb :+ "In Cc1: y=%s".format(y)
  }

  sb = sb :+ "Creating Cc1"
  new Cc1
  sb = sb :+ "Created Cc1"

  println(sb.mkString(";"))
  //Instantiations are tracked and will not allow a duplicate instantiation. " + Note T1 extends T2, and C1 also extends T2, but T2 is only instantiated once:
  //he diamond of death is avoided since instantiations are tracked and will not allow multiple instantiations:
}

object TestT1 extends App {
  val p1 = new Point(2, 3)
  val p2 = new Point(2, 4)
  val p3 = new Point(3, 4)
  println(p1.isSimilar(p2))
  println(p2.isNotSimilar(p3))
  println(p2.isNotSimilar(3))

  val e = new Event("发生雪崩")
  val myListener = new MyListener
  println(myListener.listen(e))




  //Traits can have concrete implementations that can be mixed into concrete classes with it's own state:
  val welder = new Welder
  welder.weld()
  val baker = new Baker
  baker.bake()
  println(welder.logCache.size) //1
  println(baker.logCache.size)  //1

  //Traits are instantiated before a classes instantiation:
}