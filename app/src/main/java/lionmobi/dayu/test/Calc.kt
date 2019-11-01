package lionmobi.dayu.test

import java.lang.UnsupportedOperationException

fun main(args: Array<String>) {
    println("请输入算式例如： 3 + 4")
    val input = readLine()
    if (input != null){
        val splits = input.split(" ");
        val arg1 = splits[0].toDouble()
        val op = splits[1]
        val arg2 = splits[2].toDouble()
        println("$arg1 $op $arg2 = ${Operator(op).apply(arg1, arg2)}")
    }
}

class Operator(op : String){
    val opFun : (left : Double, right : Double) -> Double
    init {
        opFun = when(op){
            "+"-> {l,r -> l + r}
            "-"-> {l,r -> l - r}
            "*"-> {l,r -> l * r}
            "/"-> {l,r -> l / r}
            "%"->{l,r -> l % r}
            else->{
                throw UnsupportedOperationException(op)
            }
        }
    }

    fun apply (left: Double, right: Double): Double{
        return opFun.invoke(left, right)
    }
}