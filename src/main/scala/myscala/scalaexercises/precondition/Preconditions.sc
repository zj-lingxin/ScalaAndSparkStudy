// One of the benefits of object-oriented programming is that it allows you to encapsulate data inside objects so that
// you can ensure the data is valid throughout its lifetime. In the case of an immutable object such as Rational,
// this means that you should ensure the data is valid when the object is constructed.
// Given that a zero denominator is an invalid state for a Rational number, you should not let a Rational be constructed
// if a zero is passed in the d parameter.
//The best way to approach this problem is to define as a precondition of the primary constructor that d must be non-zero.
// A precondition is a constraint on values passed into a method or constructor, a requirement which callers must fulfill.
//One way to do that is to use require, like this:
class Rational(n: Int, d: Int) {
  require(d != 0)
  override def toString = n + "/" + d
}

new Rational(2, 3)
// new Rational(4, 0) //error

class WithParameterRequirement(val myValue: Int) {
  require(myValue != 0)

  def this(someValue: String) {
    this(someValue.size)
  }
}

//new WithParameterRequirement("")


