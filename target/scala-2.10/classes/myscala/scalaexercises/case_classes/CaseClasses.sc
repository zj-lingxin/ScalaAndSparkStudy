//It makes only sense to define case classes if pattern matching is used to decompose
// data structures. The following object defines a pretty printer function for our
// lambda calculus representation:
abstract class Term

case class Var(name: String) extends Term

case class Fun(arg: String, body: Term) extends Term

case class App(f: Term, v: Term) extends Term

class Var2(name: String) extends Term

//This class hierarchy can be used to represent terms of the untyped lambda calculus. To facilitate the construction
//of case class instances, Scala does not require that the new primitive is used. One can simply use the class name
// as a function.
//case class 不需要 new 关键字
Fun("x", Var("y"))
// AnotherVar("x") // error
new Var2("x") //需要加new

//The constructor parameters of case classes are treated as public values and can be accessed directly.
val x = Var("x")
x.name //case class 的name默认是public的
val x2 = new Var2("x")
//不能使用x2.name,因为name不是public的
//For every case class the Scala compiler generates equals method which implements structural equality and a
// toString method. For instance:
val v1 = Var("x")
val v2 = Var("x")
val v3 = Var("y")
v1 == v2 // true
v1 == v3 // false

def printTerm(term: Term) {
  term match {
    case Var(n) =>
      print(n)
    case Fun(x, b) =>
      print("^" + x + ".")
      printTerm(b)
    case App(f, v) =>
      print("(")
      printTerm(f)
      print(" ")
      printTerm(v)
      print(")")
  }
}
printTerm(Var("x"))
printTerm(Fun("fun",Var("x")))
printTerm(App(Var("x"),Fun("fun",Var("x"))))
def isIdentityFun(term: Term): Boolean = term match {
  case Fun(x, Var(y)) if x == y => true
  case _ => false
}
isIdentityFun(Fun("x", Var("x")))
isIdentityFun(Fun("x", Fun("y", App(Var("x"), Var("y")))))

//Case classes have an automatic equals method that works:
case class Person(first: String, last: String)
val p1 = new Person("A", "B")
val p2 = new Person("C", "D")
val p3 = new Person("A", "B")
p1 == p2
p1 == p3
p1 eq p2
p1 eq p3
//Case classes have an automatic hashcode method that works:
p1.hashCode() == p2.hashCode()
p1.hashCode() == p3.hashCode()
//Case classes have a convenient way they can be created:
case class Dog(name: String, breed: String)
val d1 = Dog("Scooby", "Doberman")
val d2 = Dog("Rex", "Custom")
val d3 = new Dog("Scooby", "Doberman") // the old way of creating using new
d1 == d2 //false
d1 == d3 //true
d2 == d3 //false
//Case classes have a convenient toString method defined:
d3.toString
//Case classes have automatic properties:
d1.name
d1.breed
//Case classes can have mutable properties:
case class Cat(var name: String, breed: String) // you can rename a dog, but change its breed? nah!
val c1 = Cat("喵咪", "Doberman")
c1.name = "喵喵"
//Safer alternatives exist for altering case classes:
case class Pig(name: String, breed: String)
val pig1 = Pig("猪猪侠","面包")
val pig2 = pig1.copy(name = "猪猪妹")
//Case classes can have default and named parameters:
case class Student(first:String, last: String, age:Int = 0, ssn: String = "")
//Case classes can be disassembled to their constituent parts as a tuple:
val s = Student("ling", "xin", 12, "3304991-111")
val parts = Student.unapply(s).get
parts._1
parts._4

//Case classes are Serializable
s.isInstanceOf[Serializable]
