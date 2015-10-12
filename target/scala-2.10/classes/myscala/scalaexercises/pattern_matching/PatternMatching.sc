/**
 * Scala has a built-in general pattern matching mechanism. It allows to match on any sort of data with a first-match
 * policy. Here is a small example which shows how to match against an integer value:
 */
def matchTest(x: Int): String = x match {
  case 1 => "one"
  case 2 => "two"
  case _ => "many"
}

matchTest(3)

//Pattern matching returns something:
val stuff = "blue"

val myStuff = stuff match {
  case "red" => println("RED"); 1
  case "blue" => println("BLUE");2
  case "green" => println("GREEN");3
  case _ => println(stuff); 0
}

//Pattern matching can return complex somethings:
val myStuff2 = stuff match {
  case "red" => (255, 0, 0)
  case "green" => (0, 255, 0)
  case "blue" => (0, 0, 255)
  case _ => println(stuff); 0
}

//Pattern matching can match complex expressions:
//Pattern matching can wildcard parts of expressions:
def goldilocks(expr: Any) = expr match {
  case (food, "Papa") => "Papa eating " + food
  case ("porridge", "Mama") => "Papa eating porridge"
  case (_, "Baby") => "Papa eating porridge"
  case _ => "没有合适的匹配项"
}
goldilocks(("汉堡包","Papa"))
goldilocks(("汉堡包", "Mama"))
goldilocks(("porridge", "Mama"))
goldilocks(("汉堡包", "Baby"))

//Pattern matching can substitute parts of expressions:
def goldilocks2(expr: Any) = expr match {
  case ("porridge", bear) => bear + " said someone's been eating my porridge"
  case ("chair", bear) => bear + " said someone's been sitting in my chair"
  case ("bed", bear) => bear + " said someone's been sleeping in my bed"
  case _ => "what?"
}
goldilocks(("chair", "Mama"))

//Pattern matching can done on regular expression groups:
val EatingRegularExpression = """Eating Alert: bear=([^,]+),\s+source=(.+)""".r //.r turns a String to a regular expression
val SittingRegularExpression = """Sitting Alert: bear=([^,]+),\s+source=(.+)""".r
val SleepingRegularExpression = """Sleeping Alert: bear=([^,]+),\s+source=(.+)""".r
def goldilocks3(expr: String) = expr match {
  case (EatingRegularExpression(bear, source)) => "%s said someone's been eating my %s".format(bear, source)
  case (SittingRegularExpression(bear, source)) => "%s said someone's been sitting on my %s".format(bear, source)
  case (SleepingRegularExpression(bear, source)) => "%s said someone's been sleeping in my %s".format(bear, source)
  case _ => "what?"
}
goldilocks3("Eating Alert: bear=Papa, source=porridge")
goldilocks3("Sitting Alert: bear=Mama, source=chair")
//A backquote can be used to refer to a stable variable in scope to create a case statement.
// This prevents what is called "Variable Shadowing"
val foodItem = "porridge"
def goldilocks4(expr: Any) = expr match {
  case(`foodItem`, _) => "eating"
  case ("chair", "Mama") => "sitting"
  case ("bed", "Baby") => "sleeping"
  case _ => "what?"
}
goldilocks4(("porridge", "Papa"))
goldilocks4(("chair", "Mama"))
goldilocks4(("porridge", "Cousin"))
goldilocks4(("beer", "Cousin"))
//A backquote can be used to refer to a method parameter as a stable variable to create a case statement:
def patternEquals(i: Int, j: Int) = j match {
  case `i` => true
  case _ => false
}
patternEquals(3, 3)
patternEquals(7, 9)
patternEquals(9, 9)
//To pattern match against a List, the list can be broken out into parts, in this case the head x and the tail xs.
// Since the case doesn't terminate in Nil, xs is interpreted as the rest of the list:
def secondElement(ls: List[Int]) = ls match {
  case x :: Nil => "Nil"
  case x :: xs => xs.head
  case _ => 0
}
secondElement(List(1, 2, 3))
secondElement(List(1))
secondElement(List())
//To obtain the second you can expand on the pattern. Where x is the first element, y is the second element,
// and xs is the rest:
def thirdElement(ls: List[Int]) = ls match {
  case x :: Nil  => "一个元素"
  case x :: y :: Nil => "两个元素"
  case x :: y :: xs  => "大于等于三个元素"
  case _ => 0
}
thirdElement(List())
thirdElement(List(1))
thirdElement(List(1,2))
thirdElement(List(1,2,3))
//Same koan as above, but we are pattern matching of a list with only one item!
val secondElement2 = List(1) match {
  case x :: y :: xs => y
  case _ => 0
}

//To pattern match against List, you can also establish a pattern match if you know the exact number of elements in a List:
val r = List(1, 2, 3) match {
  case x :: y :: Nil => y
  case _ => 0
}




