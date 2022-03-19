/**
 * 函数
 */
fun func(arg: Int): String {
    return arg.toString()
}

/**
 * 匿名函数
 */
val f: () -> Unit = fun() {}

/**
 * 高阶函数 :参数/返回值包含函数类型的函数
 */
fun argFuntion(block: () -> Unit) { //函数类型参数
    block()
}

fun returnFunction(): () -> Long {
    return { System.currentTimeMillis() } //函数类型返回值
}

//public inline fun IntArray.forEach(action: (Int) -> Unit): Unit {
//    for (element in this) action(element)
//}

//intArr.forEach {} 只有一个lambda表达式作为参数可省略函数的小括号
//lambda表达式作为最后一个参数可以放括号外

fun cost(block: () -> Unit): Long {
    val start = System.currentTimeMillis()
    block()
    return System.currentTimeMillis() - start
}

fun fibonacci(): () -> Long {
    var first = 0L
    var second = 1L
    return {
        val next = first + second
        val current = first
        first = second
        second = next
        current
    }
}

//fun main() {
//    cost {
//        val fibonacciNext = fibonacci()
//        for (i in 0..10) {
//            println(fibonacciNext)
//        }
//    }
//}

/**
 * 内联函数   减少函数的调用，优化性能开销
 */
public inline fun IntArray.forEach(action: (Int) -> Unit): Unit {
    for (element in this) action(element)
}

//高阶函数多考虑用内联
inline fun costTime(block: () -> Unit): Unit {
    val start = System.currentTimeMillis()
    block()
    println(System.currentTimeMillis() - start)
}

//内联高阶函数的return
inline fun func(block: () -> Unit) {
    block()
}

fun main() {
    func {
        return@func // local return 只跳出当前函数
        return // non-local return 跳出所在的外部函数 从main函数返回了
    }
    println("end")

    val ints = intArrayOf(1, 2, 3, 4)
    ints.forEach {
        if (it == 3) {
            return@forEach // 在循环场景下等价于continue，非循环场景下只能用这个return
            return // 等价于break
        }
        println("element:$it")
    }
}

//可能存在不合法的non-local return，因为block的调用处与定义处不在同一个调用上下文
//crossinline 禁止non-local return
//noinline 这样函数的inline就没意义了
inline fun Runnable(crossinline block: () -> Unit): Runnable {
    return object : Runnable {
        override fun run() {
            block()
        }
    }
}

//内联函数的限制规则：
// 1.public/protected只能访问public
// 2.内联只能访问内联
// 3.参数不能被存

/**
 * 内联属性
 */
//没有backing-filed的属性的getter/setter可以被内联

var pocket: Double = 0.0
var money: Double
    inline get() = pocket
    inline set(value) {
        pocket = value
    }
//因为money没有backing-field 内联后就没它什么事儿了，就简化为pocket的调用

/**
 * 几个有用的高阶函数
 */
//let
//run
//also
//apply
//use