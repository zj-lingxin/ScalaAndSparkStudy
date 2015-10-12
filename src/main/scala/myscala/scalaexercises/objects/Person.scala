package myscala.scalaexercises.objects

/**
 * Created by lingx on 2015/9/8.
 * 伴生对象可以访问类的私有值
 */
class Person(val name: String, private val superheroName: String)

object Person {
  def showMeInnerSecret(x: Person) = x.superheroName
}

object Main {
  def main(args: Array[String]) {
    val clark = new Person("Clark Kent", "Superman")
    val peter = new Person("Peter Parker", "Spiderman")
    println(Person.showMeInnerSecret(clark))
    println(Person.showMeInnerSecret(peter))
  }
}
