package myspark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkContext, SparkConf}

object ElmTest {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("xx").setMaster("local")
    val sc = new SparkContext(conf)
    sc.textFile("dfds")
    val rdd = sc.parallelize(Array("2016-02-12", "2014-10-16", "2015-11-03", "2016-01-03", "2015-09-16", "2013-01-22", "2014-10-16"))
    val (minDate, maxDate) = getMinAndMaxDate(rdd)
    println(minDate)
    println(maxDate)
    sc.stop()
  }

  def getMinAndMaxDate(dateRdds: RDD[String]) = {
    val rdd = dateRdds.filter(a => a.substring(5, 7) != "02").distinct()
    var maxDate = rdd.max()
    val yearMonthOfMaxDate = maxDate.substring(0, 7)
    val dayOfMaxDate = maxDate.substring(8, 10)
    //如果最大的年月的日期小于15号，就把该年月过滤掉，然后找到最大的那个年月。
    if (dayOfMaxDate < "15") {
      val rddWithOutMaxYearMonth = rdd.filter(a => a.substring(0, 7) != yearMonthOfMaxDate)
      if (!rddWithOutMaxYearMonth.isEmpty()) {
        maxDate = rddWithOutMaxYearMonth.max()
      }
    }
    (rdd.min(), maxDate)
  }
}
