//Infix 中缀, Prefix 前缀 and Postfix 后缀  Operators
//Any method which takes a single parameter can be used as an infix operator: a.m(b) can be written a m b.
val g: Int = 3
g + 4 // + is an infix operator
g.+(4) //same result but not using the infix operator

//Infix Operators do NOT work if an object has a method that takes two parameters:
val s: String = "Check out the big brains on Brad!"
s indexOf 'o' //indexOf(Char) can be used as an infix operator
s indexOf('o', 11) //indexOf(Char, Int) cannot be used an infix operator
//s indexOf 'o', 11 //error

//Any method which does not require a parameter can be used as a postfix operator: a.m can be written a m.
//For instance a.##(b) can be written a ## b and a.! can be written a!
/*
Postfix operators have lower precedence than infix operators, so:
  foo bar baz means foo.bar(baz).
  foo bar baz bam means (foo.bar(baz)).bam
  foo bar baz bam bim means (foo.bar(baz)).bam(bim).
*/
//Prefix operators work if an object has a method name that starts with unary_ :
-g

//Here we create our own prefix operator for our own class. The only identifiers that can be used as prefix
//operators are +, -, !, and ~:
class Stereo {
  def unary_+ = "on"
  def unary_- = "off"
}
val stereo = new Stereo
+stereo
-stereo

