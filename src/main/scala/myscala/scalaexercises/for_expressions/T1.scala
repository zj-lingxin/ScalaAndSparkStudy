package myscala.scalaexercises.for_expressions

/**
 * Created by lingx on 2015/9/10.
 */
object T1 extends App {
  //For loops can be simple:
  val someNumbers = Range(0, 10)
  var sum = 0
  for ( i <- someNumbers)
    sum += i
  println(sum)
}

object T2 extends App {
  //For loops can contain additional logic:
  val someNumbers = Range(0, 10)
  var sum = 0
  for (i <- someNumbers)
    if (i % 2 == 0) sum += i
  println(sum)
}

object T3 extends App {
  //Using 'for' we can make more readable code
  val nums = List(List(1), List(2), List(3), List(4), List(5))
  val result = for {
    numList <- nums
    num <- numList
    if(num%2 == 0)
  } yield (num)
  println(result)
}
