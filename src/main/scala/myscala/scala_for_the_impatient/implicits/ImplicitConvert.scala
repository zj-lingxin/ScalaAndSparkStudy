package myscala.scala_for_the_impatient.implicits

import java.io.File
import scala.io.Source

/**
 * 隐式转换和隐式参数是Scala的两个功能强大的工具，在幕后处理那种很有价值的东西。
 * 要点：
 * 1)、隐式转换用于在类型之间做转换
 * 2)、你必须引入隐式转换，并确保它们可以以单个标识符的形式出现在当前作用域。
 * 3)、隐式参数列表会要求指定类型的对象。它们可以从当前作用域中以单个标识符定义的隐式
 * 对象获取，或者从目标类型的伴生对象获取
 * 4)、如果隐式参数是一个单参数的函数，那么它同时也会作为隐式转换使用。
 * 5)、类型参数的上下文界定要求存在一个指定类型的隐式对象。
 * 6)、如果有可能定位到一个隐式对象，这一点可以作为证据证明某个类型转换是合法的。
 *
 * 1、隐身转换(隐式转换函数)
 * 所谓“隐式转换函数”指的是以“implicit”关键字声明的带有“单个”参数的函数。
 * 隐式转换函数将被自动应用，将值(即单个参数的值)从一种类型转换为另一种类型。
 * 你可以给隐式转换函数取任何名称。建议用source2Target这种约定俗成的名称。
 * 一般情况下我们不显示地调用它，但有时候我们也需要显示地引入转换函数。
 *
 * 2、利用隐式转换丰富现有类库的功能
 * 如果希望某个类有某个方法，而这个类的作者却没有提供，那么可以使用隐式转换丰富
 * 现有类库的功能。做法如下：
 * 步骤1:定义一个经过丰富的类型，提供你想要的功能
 * 步骤2：再提供一个隐式转换函数将原来的类型转换到这个新的类型。
 * 这样就可以在原来的对象上调用新增的方法了
 *
 * 3、引入隐式转换
 * Scala会考虑如下的隐式转换函数：
 * 1)、位于源或目标类型的伴生对象中的隐式函数
 * 2)、位于当前作用域可以以单个标识符指代的隐式函数
 * 比如我们可以将int2Fraction放入到Fraction的伴生对象中，这样就不用显示的import了。
 * 假定我们有另一个double2Fraction放到了FractionConversions中，FractionConversions对象
 * 位于myscala.scala_for_the_impatient.implicits这个包中。如果想使用这个转换，就需要引入
 * FractionConversions对象，像这样：
 * import myscala.scala_for_the_impatient.implicits.FractionConversions._
 * 仅仅是
 * import myscala.scala_for_the_impatient.implicits.FractionConversions
 * 是不够的。
 * 可以将引入局部化，以避免不必要的转换发生，如在方法中引入。
 *
 * 4、隐式转换的规则
 * 隐式转换会在如下三种情况时会被考虑：
 * 1)表达式的类型与预期的不同时
 * 2)当对象访问一个不存在的成员时
 * 3)当对象调用某个方法，而这个方法的参数类型与传入参数不匹配时
 *
 */

class Fraction(val fz: Int, val fm: Int) {
  def *(that: Fraction) = {
    Fraction(this.fz * that.fz, this.fm * that.fm)
  }

  override def toString: String = this.fz + "/" + this.fm
}

object Fraction {
  //############# 例1 隐式转换函数 ########
  //############# 例4 引入隐式转换 ########
  // 将int2Fraction放入Fraction的伴生对象中
  implicit def int2Fraction(n: Int) = new Fraction(n, 1)

  //隐式转换
  implicit def fraction2Ordered(fraction:Fraction) = new Ordered[Fraction] {
    override def compare(that: Fraction): Int = {
      if ((fraction.fz.toDouble / fraction.fm - that.fz.toDouble / that.fm) > 0) 1 else -1
    }
  }
  /*  这边隐式转换和隐式值应该只要写一个就可以了
  //隐式值。该隐式值是将一个Fraction转换成Ordered[Fraction]的函数
  implicit val orderedFraction = (fraction:Fraction) => new Ordered[Fraction]{
    override def compare(that: Fraction): Int = {
      if((fraction.fz.toDouble / fraction.fm - that.fz.toDouble / that.fm) > 0) 1 else -1
    }
  }
  */

  def apply(fz: Int, fm: Int) = {
    new Fraction(fz, fm)
  }
  //############# 例4 结束 #########################
  //##################### 例1 结束 ##################
}

//############# 例5 引入隐式转换 ########
object FractionConversions {
  implicit def double2Fraction(n: Double) = Fraction(n.toInt, 1)

  implicit def fraction2Double(f: Fraction) = f.fz.toDouble / f.fm
}

//############# 例5 结束 ########

//#############例2 利用隐式转换丰富现有类库的功能###########
//业务：丰富java.io.File类，使得它有个read方法来读取文件
//步骤1：写一个经过丰富的类，提供read方法
class RichFile(val from: File) {
  def read = Source.fromFile(from.getPath).mkString
}

object RichFile {
  //步骤2：再提供一个隐身转换函数，将File类型转化成RichFile类型
  implicit def file2RichFile(from: File) = new RichFile(from)
}

//#################### 例2 结束 #########################

//############# 例3 利用隐式转换丰富现有类库的功能 见Example1 ###########

class NeedDouble(n: Double)

object ImplictsMain {
  def main(args: Array[String]) {
    //测试例1、例4
    val result = 2 * Fraction(2, 3)
    println(result)

    //测试例2
    import myscala.scala_for_the_impatient.implicits.RichFile.file2RichFile
    val contents = new File("D:\\words.txt").read
    println(contents)
    //############ 引入隐式转换的测试 开始#################
    //必须引入下面这句,为了下面的测试，所以将下面两句注释掉
    //import myscala.scala_for_the_impatient.implicits.FractionConversions._
    //val result2: Fraction = 2.3 * Fraction(2,3)

    //可以选择你想要的特定的转换。如果你想要的是一个fraction2Double，而不是double2Fraction，你可以直接引用它
    //import myscala.scala_for_the_impatient.implicits.FractionConversions.fraction2Double
    //val result3: Double = 2.3 * Fraction(2,3)

    //如果某个特定的隐式转换给你带来麻烦，你也可以将它排除 //测试后发现有问题，无法排除
    //import myscala.scala_for_the_impatient.implicits.FractionConversions.{fraction2Double => _,_}
    //val result4: Double = 2.3 * Fraction(2,3)
    //############ 引入隐式转换的测试 结束 #################

    //############ 隐式转换规则 ############################
    //表达式的类型与预期的类型不符时
    import myscala.scala_for_the_impatient.implicits.FractionConversions._
    println(new NeedDouble(Fraction(4, 2)))

    //当对象访问一个不存在的成员时
    import myscala.scala_for_the_impatient.implicits.RichFile._
    new File("D:\\words.txt").read

    //当对象调用某个方法，而这个方法的参数类型与传入参数不匹配时
    3 * Fraction(4, 5)

  }
}
