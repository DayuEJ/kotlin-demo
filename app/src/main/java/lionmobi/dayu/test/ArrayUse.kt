package lionmobi.dayu.test

//类名后面跟着的为主构造器 其他用constructor 定义的为次构造器
class ArrayUse constructor(firstName : String) {

    //此构造器必须委托给主构造器 没有主构造器的会委托给init{ }初始化代码块
    constructor(age : Int, firstName: String) : this(firstName){

    }

    private fun createArray(){
        //数组为Array<T>  这里的类型会自动推断
        var strArr = arrayOf("java", "kotlin", "swift","go")
        var intArr = arrayOf(1, 2, 3,4)

        //声明数组长度与类型
        var nullArr = arrayOfNulls<Int>(5)

        //创建长度为0的空数组
        var emptyArr = emptyArray<String>()

        //使用Array(size : Int, init : (Int) -> T)数组构造器
        var creatorArr = Array(5, {(it * 2 + 97).toChar()})

        //循环遍历数组
        for (i in 0 until strArr.size){
            println(strArr[i])
        }

        for (lan in strArr){
            println(lan)
        }

        //indices 返回数组的索引区间 根据索引区间来遍历数组
        for (i in strArr.indices){
            println(strArr[i])
        }

        for((index, value) in strArr.withIndex()){
            println("索引为${index}的元素是：${value}")
        }

        //检查index 是否位于数组的索引区间内
        var  index = java.util.Random().nextInt(10)
        var isRange =  index in strArr.indices

        //lastIndex属性
        strArr.lastIndex
    }
}