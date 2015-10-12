/*
Primary Constructor
In Java we have a no-args default constructor which is provided for every class which doesn't provide its own
constructor methods. On a similar lines Primary Constructor in Scala is the kind-of default constructor in the way every class in Scala would have a Primary Constructor.
The primary constructor spans the complete class definition i.e in the example below the age field gets initialized as
part of the Primary Constructor of the Employee class.
 */
class Employee{
  var age: Int = 20
}

/*
In the above example the primary constructor didn't accept any parameters. But the primary constructor can also accepts
 parameters, this is where it is different from default myscala.scalaexercises.constructors in Java. Let me not try to draw an analogy any further.
 */
class Employee2(val firstName: String, val lastName: String) {
  override def toString: String = {
    "First Name: "+firstName+" Last Name: "+lastName
  }
}

/*
Auxiliary Constructor
In Java one can overload the myscala.scalaexercises.constructors to provide different myscala.scalaexercises.constructors accepting different parameters. On similar
lines Scala classes can declare Auxiliary myscala.scalaexercises.constructors which are overloaded forms of the Primary Constructor. The auxiliary myscala.scalaexercises.constructors are named as this. Lets see an example:
 */