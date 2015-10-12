// Lists
/**
 * Scala Lists are quite similar to arrays which means, all the elements of a list have the same type
 * but there are two important differences. First, lists are immutable, which means elements of a list cannot
 * be changed by assignment. Second, lists represent a linked list whereas arrays are flat. The type of
 * a list that has elements of type T is written as List[T].
 */
val a = List(1, 2, 3)
val b = List(1, 2, 3)
//eq检查引用
a eq b

//== 比较的是否是相同的内容
a == b

//Nil是相同的，即使是不同的对象
val c: List[String] = Nil
val d: List[Int] = Nil
c == Nil
c eq Nil
d == Nil
d eq Nil
c == d
c eq d

//Lists are easily created
val a1 = List(1, 2, 3)
//Lists can be accessed via head and tail
a1.head
a1.tail

//Lists can be accessed by position
val a2 = List(1, 3, 5, 7, 9)
a2(0)
a2(3)

//Lists are immutable
val b2 = a2.filterNot(_ == 5)


//Lists have many useful methods
a2.length
a2.reverse
a2.toString()
a2.map{_ * 2}
a2.filter(_ % 3 == 0)

//Lists can be reduced with a mathematical operation
val a3 = List(1, 3, 5, 7)
//reduce和reduceLeft貌似是一样的
a3.reduce(_ + _)
a3.reduceLeft(_ + _)
a3.reduceLeft(_ * _)

//Foldleft is like reduce, but with an explicit starting value
a3.foldLeft(0)(_ + _)
a3.foldRight(10)(_ + _)
a3.foldLeft(1)(_ * _)
a3.foldLeft(0)(_ * _)

//You can create a list from a range
val a4 = (1 to 5) toList

//Lists reuse their tails
val h = Nil
val g = 3 :: h
val f = 2 :: g
val e = 1 :: f




