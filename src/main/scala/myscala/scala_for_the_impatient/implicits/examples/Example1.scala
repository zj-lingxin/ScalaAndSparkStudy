package myscala.scala_for_the_impatient.implicits.examples

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.Row
import org.apache.spark.{SparkConf, SparkContext}
import myscala.scala_for_the_impatient.implicits.examples.DataPrepServiceImpl._

/**
 * Created by lingx on 2015/9/24.
 * 利用隐式转化丰富现有类库功能的实战例子
 */

object BaseContext {
  private var _sc: SparkContext = _

  def getSparkContext(): SparkContext = {
    if (_sc == null)
      _sc = initSparkContext()
    _sc
  }

  def initSparkContext(master: String = null): SparkContext = {
    val conf = new SparkConf().setAppName("app name")
    if (master != null) conf.setMaster(master)
    this._sc = new SparkContext(conf)
    _sc
  }

  def stopSparkContext() = _sc.stop
}

object DataPrepServiceImpl {
  val shopIdRelationPath = "/ysf/input/shop_id_relation"
  //退款率的文件路径
  val refundRatePath = "/ysf/input/refund_rate"

  //这是一个用implicit声明的带有单个参数的函数，所以这是一个隐式转换函数，它将RDD转化成RichWrapper。
  //RichWrapper中提供了所需要的方法。这样RDD[(String, T)]这样的对象就有了toPropertyUUID和addIndustryType方法。
  //该隐式转换函数遵循source2Target的命名规则
  implicit def rdd2RichRDD[T](tuple: RDD[(String, T)]) = new RichRDD(tuple)

  class RichRDD[T](val tuple: RDD[(String, T)]) {
    def toPropertyUUID: RDD[(String, T)] = {
      BaseContext.getSparkContext().textFile(shopIdRelationPath).map(_.split(",")).map(t => ((t(0), t(2))))
        .leftOuterJoin(tuple).map(t => (t._2._1, t._2._2.getOrElse(null).asInstanceOf[T])).filter(t => Option(t._2).isDefined)
    }

    def addIndustryType: RDD[(String, String, T)] = {
      BaseContext.getSparkContext().textFile("/ysf/input/industry_relation").
        map(_.split(",")).filter(_.length == 2).map(a => (a(0).trim, a(1).trim)).
        rightOuterJoin(tuple).map(t => (t._1, t._2._1.getOrElse("其他"), t._2._2.asInstanceOf[T]))
    }
  }
}

object CreditSource {
  def registerShop() = {
    BaseContext.getSparkContext().textFile("/ysf/input//property_shop").
      map(_.split(",")).filter(_.length == 19).
      map(a => (a(4).trim, (a(2).trim, a(3).trim))). //(3C数码,(2c01b6044e0f48348251fca53bb714fa,美的天天购专卖店))
      addIndustryType. //(3C数码,3C,(2c01b6044e0f48348251fca53bb714fa,美的天天购专卖店))
      map(t => Row(t._3._1, t._3._2, t._1, t._2)) //(2c01b6044e0f48348251fca53bb714fa,美的天天购专卖店,3C数码,3C) / (property_uuid, shop_name, major_business, industry_type)
  }
}