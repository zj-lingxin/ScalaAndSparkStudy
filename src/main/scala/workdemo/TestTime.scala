package workdemo

import java.text.SimpleDateFormat
import java.util.Calendar
import org.apache.spark.{SparkConf, SparkContext}
import scala.collection._

/**
 * Created by lingx on 2015/10/8.
 */
object TestTime {
  private val sdf = new SimpleDateFormat("yyyy-MM-dd")

  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("xx").setMaster("local")
    val sc = new SparkContext(conf)
    //(订单号，"2015-07-11",      11, 23.3,
    val dataRDD = sc.parallelize(Seq(
      (11211, "2015-07-11", 1, 23.0), //(订单号,日期,shop_id,订单额)
      (11213, "2014-08-11", 1, 32.5),
      (11214, "2015-08-11", 1, 56.0),
      (11215, "2015-09-12", 1, 22.3),
      (11216, "2015-10-05", 1, 10.2),
      (11311, "2015-07-16", 4, 23.0),
      (11313, "2014-08-11", 4, 32.5),
      (11314, "2015-08-10", 4, 56.0),
      (11315, "2015-09-12", 5, 22.3),
      (11316, "2015-10-06", 5, 10.2)
    )).map(t => (t._1, t._2, t._3, t._4, getWeekDay(t._2), getQuarterStartTime(t._2))).cache() //(11211,2015-07-11,1,23.0,7,2015-07-01) (订单号,日期,shop_id,订单额,星期几，季度)

    val avgSalesRDD = dataRDD.groupBy(t => (t._6, t._3, t._5)). //按季度、shop_id、星期几排序 ((2015-07-01,1,7),CompactBuffer((11211,2015-07-11,1,23.0,7,2015-07-01), (11215,2015-09-12,1,22.3,7,2015-07-01)))
      map(t => (t._1, count(t._2.toIterator))) //((2015-07-01,1,7),22.65)

    val tempRDD = dataRDD.map(t => ((t._6, t._3, t._5), t))
    val resultRDD = avgSalesRDD.leftOuterJoin(tempRDD).map(t => (t._2._2.get, t._2._1)).collect().foreach(println)
    // rdd.collect().foreach(println)
  }

  private def count(itr: Iterator[(Int, String, Int, Double, Int, String)]) = {
    var sum: Double = 0
    var count: Int = 0
    while (itr.hasNext) {
      val a = itr.next()
      sum += a._4
      count += 1
    }
    if (count != 0) sum / count else 0D
  }

  def getWeekDay(strDay: String): Int = {
    strDayToCalendar(strDay).get(Calendar.DAY_OF_WEEK)
  }

  def getQuarterStartTime(strDay: String) = {
    val c = strDayToCalendar(strDay)
    val currentMonth = c.get(Calendar.MONTH) + 1
    if (currentMonth >= 1 && currentMonth <= 3)
      c.set(Calendar.MONTH, 0)
    else if (currentMonth >= 4 && currentMonth <= 6)
      c.set(Calendar.MONTH, 3)
    else if (currentMonth >= 7 && currentMonth <= 9)
      c.set(Calendar.MONTH, 6)
    else if (currentMonth >= 10 && currentMonth <= 12)
      c.set(Calendar.MONTH, 9)
    c.set(Calendar.DATE, 1)
    sdf.format(c.getTime)
  }

  def strDayToCalendar(strDay: String) = {
    val calendar = Calendar.getInstance()
    val date = sdf.parse(strDay)
    calendar.setTime(date)
    calendar
  }

}
