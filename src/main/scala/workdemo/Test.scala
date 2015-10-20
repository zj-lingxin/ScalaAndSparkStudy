package workdemo

import org.apache.spark._

class MyRDD[T]

object Test {
  def nonNegativeMod(x: Int, mod: Int): Int = {
    val rawMod = x % mod
    rawMod + (if (rawMod < 0) mod else 0)
  }
  def main1(args: Array[String]): Unit = {
    println(nonNegativeMod(-4,3))
  }

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("xx").setMaster("local")
    val sc = new SparkContext(conf)
    val rdd = sc.parallelize(Seq((1,1.2),(2,2.1),(3,3.4)))
    val rdd2 = sc.parallelize(Seq((1,1.2),(5,5.2),(2,2.5),(3,3.3)))
    val rdd3 = rdd.union(rdd2)
    println(rdd.id + rdd.name)
    println(rdd2.id + rdd2.name)
    println(rdd3.id + rdd3.name)
    sc.stop()
  }
}
