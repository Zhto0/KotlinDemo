/**
 * 分支表达式
 */
//if...else 表达式 替代三目
var a = 1
var c = if (a > 3) 4 else 2

//when表达式
var aa = "123"
var cc = when {
    aa is String -> 2
    else -> 0
}

// switch..case表达式

var exception = try {
    //
} catch (e: Exception) {
    e.printStackTrace()
}

/**
 * 运算符与中缀表达式
 */
//kotlin支持运算符重载
var a2 = 3
var b2 = 4
var result = a2.plus(b2)
// +  plus
// in contains
// == equals
// []  get/set
// > compareTo
// () invoke

//自定义运算符
class Complex(var real: Double, var image: Double) {
    override fun toString() = "$real +${image}i"
}

operator fun Complex.plus(other: Complex): Complex {
    return Complex(this.real + other.real, this.image + other.image)
}

//中缀表达式
//2 to 3
infix fun <A, B> A.to(that: B): Pair<A, B> = Pair(this, that)

//函数表达式，如果函数是一个表达式，可以去掉括号，以等号的方式写

/**
 * lambada表达式
 */
//普通函数
fun func() {}

//匿名函数   类型：()->Unit
val f = fun() {}

//lambda表达式
val lambda: (Int) -> String = {
    //仅有1个参数，默认it
    //lambda表达式最后一行为返回值
    println(it)
    "hello"
}
// java lambda表达式是单一方法接口（SAM）的语法糖，而kotlin是匿名函数的语法糖

//案例：为person实现equals和hashCode
class Person(val age: Int, val name: String) {
    // is/!is : instanceof
    // as/as? : 强转
    override fun equals(other: Any?): Boolean {
        if (other !is Person) {
            return false
        }
        return other.age == age && other.name == name
    }

    override fun hashCode(): Int {
        return 1 + 7 * age + 13 * name.hashCode()
    }
}

fun main() {
    val c1 = Complex(3.0, 4.0)
    val c2 = Complex(1.0, 2.0)
    println(c1 + c2)
    2 to 3
    var str = lambda(1)
    println(str)
}