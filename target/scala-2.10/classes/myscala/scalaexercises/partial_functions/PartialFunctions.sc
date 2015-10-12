
 // A partial function is a trait that when implemented can be used as building blocks to determine a solution. The trait
 // PartialFunction requires that the method isDefinedAt and apply be implemented.

 val doubleEvens: PartialFunction[Int, Int] = new PartialFunction[Int, Int] {
   override def isDefinedAt(x: Int): Boolean = x % 2 == 0
   override def apply(v1: Int): Int = v1 * 2
 }

 val tripleOdds: PartialFunction[Int, Int] = new PartialFunction[Int,Int] {
   override def isDefinedAt(x: Int): Boolean = x % 2 != 0
   override def apply(v1: Int): Int = v1 * 3
 }

 val whatToDo = doubleEvens orElse tripleOdds
 whatToDo(3)
 whatToDo(4)
 doubleEvens(4)
 doubleEvens(3) //注意，这里没有报错。
 //Case statements are a quick way to create partial functions. When you create a case statement, the apply and
 // isDefinedAt is created for you.
 val doubleEvens2: PartialFunction[Int, Int] = {
   case x: Int if (x % 2 == 0) => x * 2
 }
 val tripleOdds2: PartialFunction[Int, Int] = {
   case x: Int if x % 2 != 0 => x * 3 //可以去掉括号
 }
 val whatToDo2 = doubleEvens2 orElse tripleOdds2
 whatToDo2(3)
 whatToDo2(4)
 doubleEvens2(4)
 //doubleEvens2(3) //error
 //tripleOdds2(4) //error
 tripleOdds2(3)

 //The result of partial functions can have an andThen function added to the end of the chain
 val addFive = (x: Int) => x + 5
 val whatToDo3 = doubleEvens orElse tripleOdds andThen addFive
 whatToDo3(3) // 3 * 3 + 5 = 14
 whatToDo3(4) // 4 * 2 + 5 = 13
 //The result of partial functions can have an andThen function added to the end of the chain used to continue onto
 // another chain of logic:
 val printEven: PartialFunction[Int, String] = {
   case x: Int if x % 2 == 0 => "Even"
 }
 val printOdd: PartialFunction[Int, String] = {
   case x: Int if x % 2 != 0 => "Odd"
 }
 val whatToDo4 = doubleEvens orElse tripleOdds andThen (printEven orElse printOdd)
 whatToDo4(3)
 whatToDo4(4)