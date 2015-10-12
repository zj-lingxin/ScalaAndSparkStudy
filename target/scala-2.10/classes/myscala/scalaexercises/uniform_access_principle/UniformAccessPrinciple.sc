/*
The Scala language implements a programming concept known as the Uniform Access Principle which was first put forth by
Bertrand Meyer, inventor of the Eiffel programming language.
This principle states that variables and parameterless functions should be accessed using the same syntax. Scala
supports this principle by not allowing parentheses to be placed at call sites of parameterless functions. As a result,
a parameterless function definition can be changed to a val, or vice versa, without affecting client code.
Let’s look at some code:
 */

class CalculatesAgeUsingMethod(var currentYear: Int, birthYear: Int) {
  def age = currentYear - birthYear
}

class CalculatesAgeUsingProperty(var currentYear: Int, birthYear: Int) {
  val age = currentYear - birthYear
}

//Can access age as parameterless method:
val me = new CalculatesAgeUsingMethod(2010, 2003)
me.age

//Cannot add parameter to Method invocation:
//me.age()

//Can access age as property:
val me2 = new CalculatesAgeUsingProperty(2010, 2003)
me2.age

//What happens when I update current year using property

me2.currentYear = 2011
me2.age

//What happens when I update current year using method:
me.currentYear = 2011
me.age