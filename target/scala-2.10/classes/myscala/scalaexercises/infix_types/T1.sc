/*
An infix type T1 op T2 consists of an infix operator op which gets applied to two type operands T1 and T2. The type is
equivalent to the type application op[T1,T2].
The infix operator op may be an arbitrary identifier, except for *, which is reserved as a postfix modifier denoting a
 repeated parameter type.
We can make a type infix, meaning that the type can be displayed in complement between two types in order to make a
readable declaration:
 */
case class Person(name: String)
class Loves[A, B](val a: A, val b: B)

def announceCouple(couple: Person Loves Person) = {
  couple.a.name + "is in love with " + couple.b.name
}

val jim = new Person("Jim")
val lucy = new Person("Lucy")

announceCouple(new Loves(jim, lucy))

//Of course we can make this a bit more elegant by creating an infix operator method to use with our infix type:
case class Person2(name: String) {
  def loves(person: Person2) = new Loves(this, person)
}

val lucius = new Person2("Lucius")
val lily = new Person2("Lily")

def announceCouple2(couple: Person2 Loves Person2) = {
  couple.a.name + "is in love with " + couple.b.name
}

announceCouple2(lucius loves lily)