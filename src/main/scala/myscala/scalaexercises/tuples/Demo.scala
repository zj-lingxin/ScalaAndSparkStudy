package myscala.scalaexercises.tuples

/**
 * Created by lingx on 2015/9/8.
 */
object Demo {
  def main(args: Array[String]) {
    //二元组有一个swap方法
    val tuple = ("apple",2).swap
    println(tuple._1)
    println(tuple._2)
  }
}
