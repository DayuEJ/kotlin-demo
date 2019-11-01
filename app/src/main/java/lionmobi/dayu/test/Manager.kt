package lionmobi.dayu.test

import java.sql.DriverManager.println

//类的继承 方法的复写 被继承的类 与 被复写的方法必须用open修饰 抽象类自带open属性  override关键字是必要条件不能像java一样可以省略
//接口的代理
class Manager : Driver, Writer{
    override fun drive() {

    }

    override fun write() {

    }
}

interface Driver{
    fun drive()
}

interface Writer{
    fun write()
}

class SeniorManager(val driver: Driver, val writer: Writer) : Driver by driver, Writer by writer

class CarDriver : Driver{
    override fun drive() {
        println("")
    }
}

class PPTWriter : Writer{
    override fun write() {
        println("")
    }
}

fun main(args: Array<String>) {
    val driver = CarDriver()
    val writer = PPTWriter()
    val seniorManager : SeniorManager = SeniorManager(driver, writer)
    seniorManager.drive()
    seniorManager.write()
}
//函数签名--> jvm虚拟机上的所有语言都通用 包括方法名和参数列表的类型顺序

fun max(x : Int, y : Int) : Int{
    return if (x > y) x else y;//if (x > y) x else y
}