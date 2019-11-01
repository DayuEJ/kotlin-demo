package lionmobi.dayu.test

fun main(args : Array<String>){
    val complex = Complex(3.0, 4.0)
    val complex2 = Complex(3.0, 5.0)
    maxOf(complex, complex2)
}

data class Complex(val a: Double, val b : Double) : Comparable<Complex>{
    override fun compareTo(other: Complex): Int {
        return (value() - other.value()).toInt();
    }

    fun value() : Double{
        return  a * a + b * b;
    }
}

fun <T : Comparable<T>>maxOf(a:T, b:T) : T{
    return if (a < b) b else a
}
