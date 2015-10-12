package myscala.scalaexercises.regular_expressions

import org.junit.Test

class RegexTest {
  @Test
  def regex1() = {
    //业务：用正则表达式将"[1112212,23232]"替换成 1112212,23232 ，即去掉",[,]
    val regex = """[\[\"\]]""".r
    val oldStr = "\"[1112212,23232]\""
    println("替换前的文本:" + oldStr)
    val newStr = regex.replaceAllIn(oldStr, "")
    println("替换后的文本:" + newStr)
  }
}
