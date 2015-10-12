/*
In contrast to Java, all values in Scala are objects (including numerical values and functions). Since Scala is
 class-based, all values are instances of a class.
Class hierarchy is linear, a class can only extend from one parent class:
 */
class Worker(val firstName: String, val lastName: String) {}
class Employee(override val firstName: String, override val lastName: String, val employeeId: Long)
  extends Worker(firstName, lastName)
val me = new Employee("name", "you", 11)
me.firstName
me.lastName

/*
A class that extends from another is polymorphic:
 */
val worker: Worker = me
worker.firstName
worker.lastName

//An abstract class, as in Java, cannot be instantiated and only inherited:
abstract class Worker2(val firstName: String, val lastName: String) {}
//val worker2 = new Worker2("xx", "nn")

//A class can be placed inside an abstract class just like in java:
abstract class Worker3(val firstName: String, val lastName: String) {
  class Assignment(val hours: Long) {
    // nothing to do here.  Just observe that it compiles
  }
}
class Employee3(override val firstName: String, override val lastName: String,
               val employeeID: Long) extends Worker3(firstName, lastName)

val employee = new Employee3("Name", "Yourself", 2291)
val assignment = new employee.Assignment(11)