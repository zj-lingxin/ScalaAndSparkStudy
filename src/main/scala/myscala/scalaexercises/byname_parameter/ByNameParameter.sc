/*
() => Int is a Function type that takes a Unit type. Unit is known as void to a Java programmer. The function and
 returns an Int. You can place this as a method parameter so that you can you use it as a block, but still it doesn't
 look quite right.
 */
def calc(x: () => Int): Either[Throwable, Int] = {
  try {
    Right(x())
  } catch {
    case b: Throwable => Left(b)
  }
}

val y = calc {() =>
  14 + 15
}
y

object PigLatinizer {
  def apply(x:  => String) = x.toString().tail + x.toString().head + "ay"
}

val result = PigLatinizer {
  val x = "pret"
  val z = "zel"
  x ++ z //concatenate the strings
}

object Pig {
  def apply(f:  => String): String = {
    f + "xx"
  }

}




