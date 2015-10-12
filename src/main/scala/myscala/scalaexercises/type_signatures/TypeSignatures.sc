/*
A method’s type signature comprises its name, the number, order, and types of its parameters, if any, and its result
type. The type signature of a class, trait, or singleton object comprises its name, the type signatures of all of its
members and myscala.scalaexercises.constructors, and its declared inheritance and mixin relations.
In Java you declare a generic type within a <>, in Scala it is []
 */
val z: List[String] = "Do" :: "Re" :: "Mi" :: "Fa" :: "So" :: "La" :: Nil
//Most of the time, Scala will infer the type and [] are optional:
val z2 = "Do" :: "Re" :: "Mi" :: "Fa" :: "So" :: "La" :: Nil

/*
A trait can be declared containing a type, where a concrete implementer will satisfy the type:
 */
trait Randomizer[A] {
  def draw(): A
}

class IntRandomizer extends Randomizer[Int] {
  def draw() = {
    import util.Random
    Random.nextInt()
  }
}

val intRand = new IntRandomizer
intRand.draw() < Int.MaxValue

/*
Class meta-information can be retrieved by class name by using classOf[className]
 */
classOf[IntRandomizer].getCanonicalName
classOf[IntRandomizer].getSimpleName

/*
Class meta-information can be derived from an object reference using getClass()
 */
val zoom = "zoom"
zoom.getClass
zoom.getClass.getCanonicalName
zoom.getClass.getSimpleName