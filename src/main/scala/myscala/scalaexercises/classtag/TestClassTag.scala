package myscala.scalaexercises.classtag

import org.apache.hadoop.mapred.InputFormat
import org.apache.spark.rdd.RDD

import scala.reflect.ClassTag
/**
 *
 * A `ClassTag[T]` stores the erased class of a given type `T`, accessible via the `runtimeClass`
 * field. This is particularly useful for instantiating `Array`s whose element types are unknown
 * at compile time.
 * 泛型对象在运行的时候，它的T是被擦除的。ClassTag[T]存储的是给定类型的T，我们通过runtimeClass来访问
 * 这个泛型在运行时指定的对象。在实例化Array的时候，这个特别有用。在构建数组但是它的元素类型不知道
 * 的时候。在编译时是数组的元素类型是不知道的(运行时知道)。
 * `ClassTag`s are a weaker special case of [[scala.reflect.api.TypeTags#TypeTag]]s, in that they
 * wrap only the runtime class of a given type, whereas a `TypeTag` contains all static type
 * information. That is, `ClassTag`s are constructed from knowing only the top-level class of a
 * type, without necessarily knowing all of its argument types. This runtime information is enough
 * for runtime `Array` creation.
 * ClassTag是比TypeTag更弱的一种情况。ClassTag只包含了运行时给定的类的类别信息。而TypeTag不仅包含类
 * 的类别信息，还包含了所有静态的类信息。我们在绝大多数情况下会使用ClassTag，因为ClassTag告诉我们运
 * 行时实际的类型已经足够我们在做泛型的时候去使用了。
 *
 */

/**
 * Created by lingx on 2015/9/22.
 * 数组本身是泛型。而如果我们想创建一个泛型数组的话，理论上是不可以的。
 * 在Scala中运行时，数组必须要有具体的类型，如果你继续是泛型的话，会提示你
 * 具体的类型没有，无法创建相应的数组，这是个很大的问题！
 * 在Scala中引入了ClassTag。有了它，我们就可以创建一个泛型数组。
 *
 * 我们要构建泛型对象，这里是泛型数组。我们需要ClassTag来帮我们存储T的实际的类型。
 * 在运行时我们就能获取这个实际的类型。
 *
 */
class MyType[T]

object TestClassTag {
  def arrayMake[T: ClassTag](first: T, second: T) = {
    val r = new Array[T](2)
    r(0) = first
    r(1) = second
    r
  }
  //上面的写法其实与下面的写法可以认为是等价的。下面的写法是一种更原生的写法。不建议使用下面的写法
  def arrayMake2[T](first: T, second: T)(implicit m: ClassTag[T]) = {
    val r = new Array[T](2)
    r(0) = first
    r(1) = second
    r
  }

  //implicit m: Manifest[T] 改成implicit m: ClassTage[T]也是可以的
  def manif[T](x: List[T])(implicit m: Manifest[T]) = {
    if(m <:< manifest[String])
      println("List strings")
    else
      println("Some other type")
  }

  def manif2[T](x: List[T])(implicit m: Manifest[T]) = {
    //classManifest比manifest获取信息的能力更弱一点
    if(m <:< classManifest[String])
      println("List strings")
    else
      println("Some other type")
  }


  //ClassTag是我们最常用的。它主要在运行时指定在编译时无法确定的类别的信息。
  // 我这边 Array[T](elems: _*) 中的下划线是占位符，表示很多元素
  def mkArray[T: ClassTag](elems: T*) = Array[T](elems: _*)

  def main(args: Array[String]) {
    arrayMake(1,2).foreach(println)
    arrayMake2(1,2).foreach(println)
    manif(List("Spark", "Hadoop"))
    manif(List(1, 2))
    manif(List("Scala", 3))
    manif2(List("Spark", "Hadoop"))
    manif2(List(1, 2))
    manif2(List("Scala", 3))

    val m = manifest[MyType[String]]
    println(m)//myscala.scalaexercises.classtag.MyType[java.lang.String]
    val cm = classManifest[MyType[String]]
    println(cm)//myscala.scalaexercises.classtag.MyType[java.lang.String]

    /*
    其实manifest是有问题的，manifest对实际类型的判断会有误(如依赖路径)，所以后来推出了ClassTag和TypeTag，
    用TypeTag来替代manifest，用ClassTag来替代classManifest
    */
    mkArray(1, 2, 3, 4, 5).foreach(println)
  }
}
