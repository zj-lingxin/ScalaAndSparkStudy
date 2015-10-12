package myscala.scala_for_the_impatient.typeparam

/**
 * 类型约束
 * 类型约束提供给你另一个限定类型的方式。总共有两种关系可供使用：
 * T =:= U  测试T是否等于U
 * T <:< U  测试T是否是U的子类型
 * 注意: T <%< U 已经在2.10中被废弃了！
 * 要使用这样一个约束，需要添加“隐式类型证明参数”，就像这样：
 * class Pair[T](val first: T, val second: T)(implicit ev: T <:< Comparable[T])
 * 注意：这些约束并非内建在语言中。它们是Scala类库提供的特性。
 * 在之前学习的示例中，使用类型约束并没有带来比类型界定class Pair[T < Comparable[T]更多的优点。
 * 但是在某些特定的场景下，类型约束有两个用途：
 * 1、类型约束让你可以在泛型类中定义只能在特定条件下使用的方法。
 */
//类型约束implicit ev: T <:< Comparable[T]可以完成类型界定class Pair[T < Comparable[T]]的功能(个人理解)
class PairOne[T](val first: T, val second: T)(implicit ev: T <:< Comparable[T]){
  def smaller = if(first.compareTo(second) < 0) first else second
}

//优点一：类型约束让你可以在泛型类中定义只能在特定条件下使用的方法。例如：
//你可以构造出Pair[File],尽管File不是Ordered[T]的子类。只有当你调用smaller方法时，才会报错。
/*
class Pair[T](val first: T, val second: T) {
  def smaller(implicit ev: T <:< Ordered[T]) =
    if (first < second) first else second
}
*/

object TypeConstraint extends App{
  println(new PairOne("123","345").smaller)
}




