package lionmobi.dayu.test

fun getName() : String ?{
    return null;
}

fun nullSafeTest(){
    val name : String = getName()?: return;//getName() 为null的话就return,否则会将getName的值赋值给name
    print(name.length);

    val value : String? = "hello World";
    print(value!!.length);//!! 告诉编译器不用担心为null的问题

    if (value is String){
        print(value.length);//smart cast to kotlin.string 智能类型转换
    }


    val parent : Parent = Parent();
    val chlid : Child? = parent as Child;//这里的as相当于java的强制类型转换(This cast can never succeed)
    val child1 : Child? = parent as? Child;//这里强转不成功会将child1 赋值为null
}

class Parent
class Child