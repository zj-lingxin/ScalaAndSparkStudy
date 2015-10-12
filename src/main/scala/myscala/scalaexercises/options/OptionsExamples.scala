package myscala.scalaexercises.options

/**
 * Created by lingx on 2015/9/8.
 */
object OptionsExamples {

  def getOrElse[T](option: Option[T], defaultValue: T): T = {
    if (option.isEmpty) defaultValue else option.get
  }

  def getOrElse2[T](value: Option[T], defaultValue: T): T = {
    value match {
      case Some(v) => v
      case None => defaultValue
    }
  }

  def main(args: Array[String]) {
    val a = getOrElse(None, "ss")
    println(a)
  }
}
