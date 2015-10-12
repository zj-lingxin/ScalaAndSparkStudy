package myscala.scala_for_the_impatient.typeparam

import myscala.scala_for_the_impatient.implicits.Fraction

/**
 * 要点：
 * 类、特质、方法和函数都可以有类型参数
 * 将类型参数放置在名称之后，用方括号括起来
 * 类型界定的语法是 T <: UpperBound、 T >: LowerBound、T <% ViewBound、T: ContextBound
 * 你可以用类型约束来约束一个方法，如(implicit ev: T <:<UpperBound)
 * 用 +T(协变)来表示某个泛型类的子类型关系和参数T方向一致，或用-T(逆变)来表示方向相反。
 * 协变适用于表示输出的类型参数，比如不可变集合中的元素
 * 逆变适用于表示输入的类型参数，比如函数参数。
 *
 * 1、泛型类
 * 略
 *
 * 2、泛型函数
 * 略
 *
 * 3、类型变量界定
 * 类型变量界定包括上界和下界
 * A <: B 表示上界,即A是B的子类
 * A >: B 表示下界,即A是B的父类
 *
 * 4、视图界定
 * 上节中，我们看过一个带上界的例子:
 * class Pair[T <: Comparable[T]]
 * 但是如果你试着new一个Pair(4,2),编译器会抱怨说Int不是Comparable[Int]的子类型。
 * 因为Scala的Int并没有实现Comparable。不过RichInt实现了Comparable[Int],同时还有
 * 一个从Int到RichInt的隐式转换。我们可以使用视图界定类解决上面的问题。
 * <% 是视图界定的符号
 * T <% Comparable[T]的意思是：T可以被隐式转换为Comparable[T]
 */
/** 类型变量界定案例 开始 **/
//############### 例1：上界的一个例子 #############
// first和second可以使用compareTo()来比较大小
//“T <% Comparable[T]” 是对T类型的限定，表示T必须是Comparable[T]的子类型
// compareTo是Comparable[T] 中的方法
class Pair[T <: Comparable[T]](val first: T, val second: T) {
  def bigger = if (first.compareTo(second) > 0) first else second
}

//############### 例1结束 ########################

//############### 例2：下界的一个例子 #############
class PairOfLowerBound[T](val first: T, val second: T) {
  //replaceFirst用于替换第一个元素
  //def replaceFirst(newFirst: T) = new PairTwo(newFirst, second)
  //但是我们可以做得更好。假定我们有一个PairTwo[Student]。我们应该允许用
  //一个Person来替换第一个组件。当然，这样做将会得到的结果将会是一个
  //PairTwo[Person]。通常，替换进来的必须是原类型的超类型
  //可以使用“下界”，返回值会被正确的
  def replaceFirst[P >: T](newFirst: P) = new PairOfLowerBound[P](newFirst, second)
}

class Person(val name: String)
class Student(override val name: String, val teacher: String) extends Person(name)
//###################例2结束#########################

//############### 例3：视图界定的一个例子 #############
//T <% Comparable[T]表示T可以隐式转化为Comparable[T]
class PairOfViewBound[T <% Comparable[T]](val first: T, val second: T) {
  def bigger = if (first.compareTo(second) > 0) first else second
}
//###################例3结束#########################

//############### 例4：视图界定的一个例子 #############
//用Ordered特质替换Comparable会更好，它在Comprable的基础上额外提供了关系操作符
//我们在上一节没有使用Ordered特质是因为java.lang.String实现了Comparable[String]，
//但是没有使用实现Ordered[String]。有了视图界定，这就不是一个问题了。String可以被
//隐式转换为RichString，而RichString是Ordered[String]的子类型。也就是说String可以
//隐式转换成Ordered[String]，这就是视图界定的作用啦。
class PairOfViewBound2[T <% Ordered[T]](val first: T, val second: T) {
  def bigger = if (first > second ) first else second
}
//###################例3结束#########################
/** 类型变量界定案例 结束 **/

object Main {
  def main(args: Array[String]) {
    //测试例1
    val pair = new Pair("2015-08-09", "2015-07-01")
    println(pair.bigger)

    //测试例2
    val pairWithoutLowerBound = new PairOfLowerBound[Person](new Person("颜回"), new Person("庄子"))
    // PairTwo[Person] -> PairTwo[Person]。不管是否使用下界，下面这句都是可以执行的
    val newPairWithoutLowerBound: PairOfLowerBound[Person] = pairWithoutLowerBound.replaceFirst(new Student("子路", "孔子"))

    val pairWithLowerBound = new PairOfLowerBound[Student](new Student("颜回", "孔子"), new Student("庄子", "老子"))
    //PairTwo[Student] -> PairTwo[Person] 。一般来说，替换进来的类型必须是原类型的超类型
    val newPairWithLowerBound: PairOfLowerBound[Person] = pairWithLowerBound.replaceFirst(new Person("子路"))

    println(newPairWithoutLowerBound.first.name + " " + newPairWithoutLowerBound.second.name) //子路 庄子
    println(newPairWithLowerBound.first.name + " " + newPairWithLowerBound.second.name) //子路 庄子

    //测试例3
    //val pairOfUpperBound = new Pair(4, 3) //报错
    //println(pairOfUpperBound.bigger)
    val pairOfViewBound = new PairOfViewBound(4, 3) // OK
    println(pairOfViewBound.bigger) //4
  }
}
