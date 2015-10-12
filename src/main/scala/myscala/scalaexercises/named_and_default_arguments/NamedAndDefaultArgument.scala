package myscala.scalaexercises.named_and_default_arguments

/**
 * Created by lingx on 2015/9/11.
 */
object NamedAndDefaultArgument extends App{
  //When calling methods and functions, you can use the name of the variables expliclty in the call, like so:
  def printName(first: String, last: String) = {
    println(first + " " + last)
  }

  printName("John", "Smith")
  printName(first = "John", last = "Smith" )
  printName(last = "Smith", first = "John")

  //Note that once you are using parameter names in your calls, the order doesn’t matter, so long as all parameters
  // are named. This feature works well with default parameter values:
  def printName2(first: String = "John", last: String = "Smith") = {
    println(first + " " + last)
  }

  printName2(last = "Jones")

  //Given classes below:
  class WithoutClassPatameters {
    def addColors(red: Int, green: Int, blue: Int) = {
      (red, green, blue)
    }
    def addColorsWithDefault(red: Int = 0, green: Int = 0, blue: Int = 0): Unit = {
      (red, green, blue)
    }
  }

  class WithClassParameters(val defaultRed: Int, val defaultGreen: Int, val defaultBlue: Int) {
    def addColors(red: Int, green: Int, blue: Int) = {
      (red + defaultRed, green + defaultGreen, blue + defaultBlue)
    }

    def addColorsWithDefaults(red: Int = 0, green: Int = 0, blue: Int = 0) = {
      (red + defaultRed, green + defaultGreen, blue + defaultBlue)
    }
  }

  class WithClassParametersInClassDefinition(val defaultRed: Int = 0, val defaultGreen: Int = 25, val defaultBlue: Int = 100) {
    def addColors(red: Int, green: Int, blue: Int) = {
      (red + defaultRed, green + defaultGreen, blue + defaultBlue)
    }

    def addColorsWithDefaults(red: Int = 0, green: Int = 0, blue:Int = 0) = {
      (red + defaultRed, green + defaultGreen, blue + defaultBlue)
    }
  }

  //Can specify arguments in any order if you use their names:
  val me = new WithoutClassPatameters()
  val myColor = me.addColorsWithDefault(green = 0, red = 255, blue = 0)

  //Default parameters can be functional too
  def reduce(a: Int, f: (Int, Int) => Int = _ + _): Int = f(a, a)
  println(reduce(5))
  println(reduce(5, _ * _))

}
