//Higher Order Functions
/**
 * Meet lambda. Scala provides a relatively lightweight syntax for defining anonymous functions. Anonymous functions in
 * source code are called function literals and at run time, function literals are instantiated into objects called
 * function values.
 * Scala supports first-class functions, which means you can express functions in function literal syntax,
 * i.e.,(x: Int) => x + 1, and that functions can be represented by objects, which are called function values
 */
def lambda = { x: Int => x + 1 }

//匿名函数
def lambda2 = (x: Int) => x + 1

val lambda3 = (x: Int) => x + 1

val lambda4 = new Function1[Int, Int] {
  def apply(v1: Int): Int = v1 + 1
}

def lambda5(x: Int) = x + 1

val result = lambda(3)
val result1andhalf = lambda.apply(3)
val result2 = lambda(3)
val result3 = lambda3(3)
val result4 = lambda4(3)
val result5 = lambda5(3)

/**
 * Meet closure.A closure is a function, whose return value depends on the value of one or more variables declared
 * outside this function. Consider the following piece of code with anonymous function:
 */
var incrementer = 1
def closure = {
  x: Int => x + incrementer
}
val r1 = closure(10)
incrementer = 2
val r2 =closure(10)

//------------------

//把闭包放入方法中，还是可以运行的
def summation(x: Int, y: Int => Int) = y(x)
incrementer = 3
def closure2 = (x: Int) => x + incrementer
val r3 = summation(10, closure)

//------------------

//函数中返回另一个函数
//见Demo

//------------------

//在函数中使用匿名函数来返回一个函数
//见Demo

//-----------------

//在函数中传入另一个函数作为参数
def makeUpper(ls: List[String]) = ls map{_.toUpperCase}

def makeWhatEverYouLike(ls: List[String])(sideEffect: String => String) = {
  ls map sideEffect
}

makeUpper(List("Hello","My","Name","Is","LiLy"))
makeWhatEverYouLike(List("Hello","My","Name","Is","LiLy"))(x => x toLowerCase)
//-----------------

//柯里化是将具有多个参数的函数转化为只有一个参数的函数的技术
def multiply(x: Int, y: Int) = x * y
//判断multiply这个函数是否是具有两个参数的函数
(multiply _).isInstanceOf[Function2[_,_,_]]
def curriedMultiply = (multiply _).curried
curriedMultiply(2)(3)

//Currying allows you to create specialized version of generalized function
def customFilter(f: Int => Boolean)(xs: List[Int]) = {
  xs filter f
}
customFilter(x => x % 2 == 0 )(List(1,2,3,11,22,32))
//下面就可以体会到柯里化的好处了
val evenFilter = customFilter(x => x % 2 == 0 ) _
evenFilter(List(1,2,3,11,22,32))




