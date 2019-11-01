package lionmobi.dayu.test

import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

//data class 会默认生成toString
data class Person(val name : String, val age :Int)

fun Person.sayHello(){
    println("Hello")
}

class DefaultConstructor()

//Kotlin 使用java反射
fun testJavaReflection(){
    //获取class
    val clazz = Person::class.java

    val person : Person = Person("ljy", 18)
    val clazz1 = person.javaClass

    //person::class Kotlin class
    val clazz2 = person::class.java


    //通过java反射实例化
    val person2 = clazz1.newInstance()//默认调用的是无参构造方法 会crash
    val defaultConstructor = DefaultConstructor::class.java.newInstance()// 有无参构造方法 不会crash
    val person3 = clazz1.getConstructor(String::class.java, Int::class.java).newInstance("ljy", "18")

    //通过反射访问成员 方法
    val name = clazz2.getDeclaredField("name").apply { isAccessible = true }.get(person3)
    val name2 = clazz2.getDeclaredMethod("getName").invoke(person3)
    val person4 = clazz2.getDeclaredMethod("copy", String::class.java, Int::class.java).invoke(person3, person3.name, person3.age)

    //通过反射可以修改Kotlin val(只读变量)的值

    //通过反射访问扩展方法
    Class.forName("lionmobi.dayu.test.JavaReflectionsKt")
            .getDeclaredMethod("sayHello", Person::class.java)
            .invoke(null, person3)
}

class NoPrimaryContructor{
    constructor(){

    }

    constructor(int : Int){

    }
}

//kotlin 中使用Kotlin 反射
fun testKotlinReflection(){
    //反射构建对象实例
    val kClazz = Person::class//Kotlin class
    val primaryConstructor = kClazz.primaryConstructor!!
    val kPerson = primaryConstructor.call("ljy", 18)

    NoPrimaryContructor::class.constructors.first().call()
    NoPrimaryContructor::class.constructors.last().call(18)

    //反射访问属性  值得注意的是Kotlin反射无法访问某一具体属性 只能拿到所有属性的集合
    kClazz.memberProperties.first { it.name =="name" }.get(kPerson).let { println(it) }
}
