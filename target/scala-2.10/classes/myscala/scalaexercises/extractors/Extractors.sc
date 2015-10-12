// In Scala, patterns can be defined independently of case classes. To this end, a method named unapply is defined to
// yield a so-called extractor.
// For instance, the following code defines an extractor object Twice.
object Twice {
  def apply(x: Int): Int = x * 2
  def unapply(z: Int): Option[Int] = if (z % 2 == 0) Option(z / 2) else None
}

val x = Twice(21)

x match {
  case Twice(n) => println(n)
}

/*
There are two syntactic conventions at work here:
•The pattern case Twice(n) will cause an invocation of Twice.unapply, which is used to match even number; the return value of the unapply signals whether the argument has matched or not, and any sub-values that can be used for further matching. Here, the sub-value is z/2
•The apply method is not necessary for pattern matching. It is only used to mimick a constructor. val x = Twice(21) expands to val x = Twice.apply(21).
  The code in the preceding example would be expanded as follows:
*/
Twice.unapply(x) match {
  case Some(n) => println(n)
}

/*
The return type of an unapply should be chosen as follows:
•If it is just a test, return a Boolean. For instance case even()
•If it returns a single sub-value of type T, return a Option[T]
•If you want to return several sub-values T1,...,Tn, group them in an optional tuple Option[(T1,...,Tn)].
Sometimes, the number of sub-values is fixed and we would like to return a sequence. For this reason, you can also
define patterns through unapplySeq. The last sub-value type Tn has to be Seq[S]. This mechanism is used for instance
in pattern case List(x1, ..., xn).
When you create a case class, it automatically can be used with pattern matching since it has an extractor:
 */
case class Employee(firstName: String, lastName: String)
val rob = new Employee("Robin", "Williams")
val result = rob match {
  case Employee("Robin", _) => "Where's Batman?"
  case _ => "No Batman Joke for you"
}

/*
What's an extractor? In Scala it's a method in any object called unapply, and that method is used to disassemble the
object given by returning a tuple wrapped in an option. Extractors can be used to assign values:
 */
class Car(val make: String, val model:String, val year: Short, val topSpeed: Short)
object ChopShop {
  def unapply(x: Car) = Some(x.make, x.model, x.year, x.topSpeed)
}

val ChopShop(a, b, c, d) = new Car("Chevy", "Camaro", 1978, 120)
a
b
c
d

/*
Of course an extractor can be used in pattern matching...
 */
val x2 = new Car("Chevy", "Camaro", 1978, 120) match {
  case ChopShop(s, t, u, v) => (s, t)
  case _ => ("Ford", "Edsel")
}
x2._2

/*
Since we aren't really using u and v in the previous pattern matching with can replace them with _.
 */
val x3 = new Car("Chevy", "Camaro", 1978, 120) match {
  case ChopShop(s, t, _, _) => (s, t)
  case _ => ("Ford", "Edsel")
}

/*
As long as the method signatures aren't the same, you can have as many unapply methods as you want:
 */

class Ship(val make: String, val model: String, val year: Short, val topSpeed: Short)
class Student(val firstName: String, val middleName: Option[String], val lastName: String)

object Tokenizer {
  def unapply(x: Ship) = Some(x.make, x.model, x.year, x.topSpeed)
  def unapply(x: Student) = Some(x.firstName, x.lastName)
}

val result2 = new Student("Kurt", None, "vonnegut") match {
  case Tokenizer(c, d) => "c: %s, d: %s".format(c, d)
  case _ => "Not found"
}

//An extractor can be any stable object, including instantiated classes with an unapply method.
class Car3(val make: String, val model: String, val year: Short, val topSpeed: Short) {
  def unapply(x: Car) = Some(x.make, x.model)
}

val camaro3 = new Car3("Chevy", "Camaro", 1978, 122)

val result3 = camaro3 match {
  case camaro3(make, model) => "make: %s, model: %s".format(make, model)
  case _ => "unknown"
}

/*
What is typical is to create a custom extractor in the companion object of the class. In this exercise, we use it as \
an assignment:
 */
class Employee4(val firstName: String,
               val middleName: Option[String],
               val lastName: String)

object Employee4 {
  //factory methods, extractors, apply
  //Extractor: Create tokens that represent your object
  def unapply(x: Employee4) =
    Some(x.lastName, x.middleName, x.firstName)
}

val singri = new Employee4("Singri", None, "Keerthi")

val Employee4(a4, b4, c4) = singri

/*
In this exercise we use the unapply for pattern matching employee objects
 */
class Employee5(val firstName: String,
               val middleName: Option[String],
               val lastName: String)

object Employee5 {
  //factory methods, extractors, apply
  //Extractor: Create tokens that represent your object
  def unapply(x: Employee5) =
    Some(x.lastName, x.middleName, x.firstName)
}

val singri5 = new Employee5("Singri", None, "Keerthi")

val result5 = singri match {
  case Employee5("Singri", None, x) => "Yay, Singri %s! with no middle name!".format(x)
  case Employee5("Singri", Some(x), _) => "Yay, Singri with a middle name of %s".format(x)
  case _ => "I don't care, going on break"
}
