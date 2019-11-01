package lionmobi.dayu.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

class HeaderBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private fun initView(){
        val letStr = "123";
        //如果letStr 不为空 将会进入let{},并且letStr 将会作为参数传入， 这里的it指的是letStr
        letStr?.let {
            print(it);
        }
    }

    fun getABoolean() =  true;
}

//数据类
data class Country(val id : Int, val name : String)
//静态内部类 和 非静态内部类的区别？ 非静态内部类持有了外部类的应用
//java的内部类默认为非静态内部类 而kotlin 内部类默认为静态内部类， 若想构造非静态内部类 用inner修饰。


//高阶函数：函数作为参数或者返回值的函数
// 类名::方法名 可以拿到函数的引用

fun mapTest(){
    val list = listOf(1,3,5, 7,9)
    val newList = ArrayList<Int>()
    list.forEach{
        val newElement = it * 2 + 3
        newList.add(newElement)
    }
    newList.forEach(::println)

    val newList1 = list.map { it * 2 + 3 }
    val newList2 = list.map { it.toDouble() }
}


fun flatMapTest(){
    val list  = listOf(
            1..20, 2..5, 100..322
    )
    val flatList = list.flatMap { it }
    flatList.forEach(::println)
}


/**
 * reduce函数：累加函数，第一个参数是用来叠加的返回值，第二个参数是本次循环中列表的值
 */
fun factorial(n: Int): Int { //求阶乘
    if (n == 0) return 1
    return (1..n).reduce { acc, i -> acc * i }
}

fun reduceTest() {
    val list = listOf(1, 2, 3, 4, 5)
    //1到5求和
    println(list.reduce { acc, i -> acc + i }) //acc是累加的返回值，i是当前遍历列表中的值

    println(factorial(5)) //求5的阶乘

    println()
}

data class person(val name : String, val age : Int)

fun letTest(){
    findPerson()?.let {

    }
}

fun findPerson() : person?{
    return null
}
//扩展方法 this是被扩展类的实例
//函数的复合
val add5 = {i : Int -> i + 5}
val multiplyBy2 = {i : Int -> i * 2}


