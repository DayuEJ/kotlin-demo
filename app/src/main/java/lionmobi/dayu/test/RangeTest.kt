package lionmobi.dayu.test

val range : IntRange = 0..1024;//代表着区间[0, 1024]
val range_exclusive : IntRange = 0 until 1024;//待变着半开区间[0, 1024) = [0, 1023]

val arrayOfInt : IntArray = intArrayOf(1,3,5,7)
val arrayOfInt1 : Array<Int> = arrayOf(1, 3, 5, 7)// arrayOfInt.slice(1..2)  -->数组切片

val arrayOfString : Array<String> = arrayOf("H","e","l","l","o")
val joinString = arrayOfString.joinToString("");//joinToString() 传入的连接的分隔符

class A{
    //var 用lateinit yanc
    lateinit var c : String//在使用前必须初始化 否则会报错
    val e : X by lazy {//再调用的时候才会执行lazy{ }
        X()
    }
}

class X{

}

//中缀表达式：只有一个参数 且用infix修饰的函数
private const val USERNAME = "kotlin";
private const val PASSWORD = "jetbrains"
fun main(args : Array<String>) {
    //if 表达式
    val mode = if (args.isNotEmpty() && args[0] == "1"){
        1
    }else{
        0
    }

    //分支表达式
    val x = 5
    when(x){
        is Int -> print("Hello $x")
        in 1..100-> print("x is in 1..100")
        !in 1..100 -> print("x is not in 1..100")
        args[0].toInt() -> print("x == arg[0]")// args[0]的值和 x一样
    }

    //循环语句
    for (arg in args){
        println(arg)
    }

    for ((index, value) in args.withIndex()){
        println("$index -> $value")
    }

    for (indexedValue in args.withIndex()){
        println("${indexedValue.index} -> ${indexedValue.value}")
    }

    val array = intArrayOf(1,2,3,4,5)// *array 数组的展开操作 目前仅限于可变参数vararg 传参使用， *这个运算符目前只支持array
}

