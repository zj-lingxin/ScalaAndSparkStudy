//At the top of the collection hierarchy is trait Traversable. Its only abstract operation is foreach:
//Collection classes that implement Traversable just need to define this method; all other methods can be inherited
// from Traversable.
//The foreach method is meant to traverse all elements of the collection, and apply the given operation, f, to each
// element. The type of the operation is Elem => U, where Elem is the type of the collection’s elements and U is an
// arbitrary result type. The invocation of f is done for its side effect only; in fact any function result of f is
// discarded by foreach.
// Traversables are the superclass of Lists, Arrays, Maps, Sets, Streams, and more. The methods involved can be applied
// to each other in a different type. ++ appends two Traversables together.
val set = Set(1,6,7,8,2)
val list = List(3, 4, 5, 10)
var result = set ++ list //Set(5, 10, 1, 6, 2, 7, 3, 8, 4)
val result2 = list ++ set //List(3, 4, 5, 10, 1, 6, 2, 7, 8)
//map will apply the given function on all elements of a Traversable and return a new collection of the result.
val result3 = set.map(_ * 4)

//flatten will smash all child Traversables within a Traversable
val list4 = List(List(1), List(2, 3, 4),List(5, 6, 7, 8))
list4.flatten //List(1, 2, 3, 4, 5, 6, 7, 8)
val list5 = List(List(1), List(2, 3, 4),List(5, 6, List(7, 8)))
list5.flatten //List(1, 2, 3, 4, 5, 6, List(7, 8))

//flatMap will not only apply the given function on all elements of a Traversable, but all elements within the elements
//and flatten the results:
val list6 = List(List(1), List(2, 3, 4), List(5, 6, 7), List(8, 9, 10))
val result6 = list6.flatMap(_.map(_ * 4)) // List(4, 8, 12, 16, 20, 24, 28, 32, 36, 40)

//flatMap of Options will filter out all Nones and Keep the Somes
val list7 = List(1, 2, 3, 4, 5)
val result7 = list7.flatMap(it => if(it % 2 == 0) Some(it) else None)

//collect will apply a partial function to all elements of a Traversable and will return a different collection.
// In this exercise, a case fragment is a partial function:
val result8 = list7.collect{
  case x: Int if(x % 2 == 0) => x * 3
}

//collect will apply a partial function to all elements of a Traversable and will return a different collection.
// In this exercise, two case fragments are chained to create a more robust result:
val list9 = List(1, 2, 3, 4, 5)

val partialFunction1: PartialFunction[Int, Int] = {
  case x: Int if x % 2 == 0 => -x
}

val partialFunction2: PartialFunction[Int, Int] = {
  case y: Int if y % 2 != 0 => y * 2
}

val result9 = list9.collect(partialFunction1 orElse partialFunction2)

//foreach will apply a function to all elements of a Traversable, but unlike the map function, it will not
// return anything since the return type is Unit, which is like a void return type in Java, C++
val list10 = List(4, 6, 7, 8, 9, 13, 14)
list10.foreach(num => print(num * 4 + " "))

//toArray will convert any Traversable to an Array, which is a special wrapper around a primitive Java array.
//toList will convert any Traversable to a List.
//toList, as well as other conversion methods like toSet, toArray, will not convert if the collection type is the same.
//toIterable will convert any Traversable to an Iterable. This is a base trait for all Scala collections that define
// an iterator method to step through one-by-one the collection's elements.
val set11 = Set(4, 2, 1, 3, 6, 7, 8)
val result11 = set.toIterable
result11.isInstanceOf[Iterable[Int]]
result11.isInstanceOf[Iterable[_]]

//result11.isInstanceOf[Iterable[Long]] //error
//result11.isInstanceOf[Iterable[Double]]//error
//toSeq will convert any Traversable to a Seq which is an ordered Iterable and is the superclass to List, Queues, and
// Vectors. Sequences provide a method apply for indexing. Indices range from 0 up the the length of a sequence.
set.toSeq

//toIndexedSeq will convert any Traversable to an IndexedSeq which is an indexed sequence used in Vectors and Strings
set.toIndexedSeq
//toStream will convert any Traversable to a Stream which is a lazy list where elements are evaluated as they are needed.

val map = Map("Phoenix" -> "Arizona", "Austin" -> "Texas")
val set12 = Set()
//isEmpty is pretty self evident
map.isEmpty
set12.isEmpty
//nonEmpty is pretty self evident too
map.nonEmpty
set12.nonEmpty

//size provides the size of the traversable
//hasDefiniteSize will return true if there is traversable that has a finite end, otherwise false.
val map2 = Map("Phoenix" -> "Arizona", "Austin" -> "Texas")
map2.hasDefiniteSize
import Stream.cons
val stream = cons(0, cons(1, Stream.empty))
stream.hasDefiniteSize

//head will return the first element of an ordered collection, or some random element if order is not defined like
// in a Set or Map
val list12 = List(10, 19, 45, 1, 22)
list12.head

//headOption will return the first element as an Option of an order collection, or some random element if order is not
// defined. If a first element is not available, then None is returned.
val list13 = List(10, 19, 45, 1, 22)
list13.headOption

//last will return the last element of an ordered collection, or some random element if order is not defined like
// in a Set or Map.
val list14 = List(10, 19, 45, 1, 22)
list14.last

//lastOption will return the first element as an Option of an order collection, or some random element if order is not
// defined. If a first element is not available, then None is returned:
val list15 = List(10, 19, 45, 1, 22)
list15.lastOption

//find will locate the first item that matches a predicate p as Some or None if an element is not found:
val list16 = List(10, 19, 45, 1, 22)
list16.find(_ % 2 != 0)

//tail will return the rest of the collection without the head
val list17 = List(10, 19, 45, 1, 22)
list17.tail

//init will return the rest of the collection without the last
val list18 = List(10, 19, 45, 1, 22)
list18.init

//Given a from index, and a to index, slice will return the part of the collection including from, and excluding to:
val list19 = List(10, 19, 45, 1, 22)
list19.slice(1, 3) //List(19, 45)

//take will return the the first number of elements given.
val list20 = List(10, 19, 45, 1, 22)
list20.take(3)

//take is used often with Streams, and Streams after all are Traversable.
def streamer(v: Int): Stream[Int] = cons(v, streamer(v + 1))
val a = streamer(2)
(a take 3 toList)

//drop will take the rest of the Traversable except the number of elements given
val list21 = List(10, 19, 45, 1, 22)
list21.drop(2)      //List(45, 1, 22)
list21.dropRight(2) //List(10, 19, 45)

//takeWhile will continually accumulate elements until a predicate is no longer satisfied. In this exercise, TreeSet
// is Traversable. TreeSet also is also sorted.
val list22 = List(87, 44, 5, 4, 200, 10, 39, 100)
list22.takeWhile(_ < 100) //List(87, 44, 5, 4)

//ropWhile will continually drop elements until a predicate is no longer satisfied. Again, TreeSet is Traversable.
// TreeSet also is also sorted.
val list23 = List(87, 44, 5, 4, 200, 10, 39, 100)
list23.dropWhile(_ < 100) //List(200, 10, 39, 100)

//filter will take out all elements that don't satisfy a predicate. An Array is also Traversable.
val array = Array(87, 44, 5, 4, 200, 10, 39, 100)
array.filter(_ < 100) //Array(87, 44, 5, 4, 10, 39)

//filterNot will take out all elements that satisfy a predicate. An Array is also Traversable.
val array24 = Array(87, 44, 5, 4, 200, 10, 39, 100)
array24.filterNot(_ < 100)

//splitAt will split a Traversable at a position, returning a 2 product Tuple. splitAt is also defined as
//(xs take n, xs drop n)
val array25 = Array(87, 44, 5, 4, 200, 10, 39, 100)
val result25 = array25 splitAt 3
result25._1.mkString(" ") // 87 44 5
result25._2.mkString(" ") // 4 200 10 39 100

//span will split a Traversable according to predicate, returning a 2 product Tuple. span is also defined as
// (xs takeWhile p, xs dropWhile p)
val array26 = Array(87, 44, 5, 4, 200, 10, 39, 100)
val result26 = array26 span (_ < 100)
result26._1
result26._2

// partition will split a Traversable according to predicate, return a 2 product Tuple. The left side are the elements
// satisfied by the predicate, the right side is not. Array is Traversable, partition is also defined as (xs filter p, xs filterNot p)
val array27 = Array(87, 44, 5, 4, 200, 10, 39, 100)
val result27 = array27 partition (_ < 100)
result27._1
result27._2

// groupBy will categorize a Traversable according to function, and return a map with the results. This exercise uses
// Partial Function chaining.
val array28 = Array(87, 44, 0, 4, 200, -10, 39, 100, 0)
val oddAndSmallPartial: PartialFunction[Int, String] = {
  case x: Int if x % 2 != 0 && x < 100 => "Odd and less than 100"
}

val evenAndSmallPartial: PartialFunction[Int, String] = {
  case x: Int if x != 0 && x % 2 == 0 && x < 100 => "Even and less than 100"
}

val largePartial: PartialFunction[Int, String] = {
  case x: Int if x > 99 => "Large Number"
}

val zeroPartial: PartialFunction[Int, String] = {
  case x: Int if x == 0 => "Zero"
}

val negativePartial: PartialFunction[Int, String] = {
  case x: Int if x < 0 => "Negative Number"
}

val result28 = array28 groupBy {
  oddAndSmallPartial orElse
    evenAndSmallPartial orElse
    negativePartial orElse
    largePartial orElse
    zeroPartial
}

result28("Odd and less than 100").mkString(" ")
result28("Even and less than 100").mkString(" ")
result28("Large Number").mkString(" ")
result28("Zero").mkString(" ")

//forall will determine if a predicate is valid for all members of a Traversable.
val list29 = List(87, 44, 5, 4, 200, 10, 39, 100)
val result29 = list29 forall (_ < 100)  //false

//exists will determine if a predicate is valid for some members of a Traversable.
val list30 = List(87, 44, 5, 4, 200, 10, 39, 100)
list30.exists(_ < 100)

//count will count the number of elements that satisfy a predicate in a Traversable.
val list31 = List(87, 44, 5, 4, 200, 10, 39, 100)
list31.count(_ < 100)

// /: or foldLeft will combine an operation starting with a seed and combining from the left. Fold Left is defined as
// (seed /: list), where seed is the initial value. Once the fold is established, you provide a function that takes two
// arguments. The first argument is the running total of the operation, and the second element is the next element of
// the list.
//:\ or foldRight` will combine an operation starting with a seed and combining from the right. Fold right is defined
// as (list :\ seed), where seed is the initial value. Once the fold is established, you provide a function that takes
// two elements. The first is the next element of the list, and the second element is the running total of the operation.
// Given a Traversable (x1, x2, x3, x4), an initial value of init, an operation op, foldRight is defined as: x1 op
// (x2 op (x3 op (x4 op init)))
// reduceLeft is the similar to foldLeft, except that the seed is the head value
// reduceRight is the similar to foldRight, except that the seed is the last value
// There are some methods that take much of the folding work out by providing basic functionality. sum will add all the elements, product will multiply, min would determine the smallest element, and max the largest.
val intList = List(5, 4, 3, 2, 1)
intList.sum
intList.product
intList.max
intList.min

// You would choose foldLeft/reduceLeft or foldRight/reduceRight based on your mathematical goal. One other reason for
// deciding is performance. foldLeft is more performant since it uses tail recursion and is optimized. This exercise
// will either work or you will receive a StackOverflowError.

//transpose will take a traversable of traversables and group them by their position in it's own traversable.
// E.g.: ((x1, x2),(y1, y2)).transpose = (x1, y1), (x2, y2) or ((x1, x2, x3),(y1, y2, y3),(z1, z2, z3)).transpose =
// ((x1, y1, z1), (x2, y2, z2), (x3, y3, z3))
val list32 = List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9))
list32.transpose

//addString will take a StringBuilder to add the contents of list into the builder.
val stringBuilder = new StringBuilder()
val list33 = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
stringBuilder.append("I want all numbers 6-12: ")
list33.filter(it => it > 5 && it < 13).addString(stringBuilder, ",")
list33.filter(it => it > 5 && it < 13).addString(stringBuilder, ",")
stringBuilder.mkString

//Traversables can have views which allow you to efficiently do compound work.
//Views can also accept a to and from value which takes a subset and performs your view functions on the subset.
val list34 = List(122, 211, 3334, 24, 345, 116, 117, 338)
list34.view(3, 6).force