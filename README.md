# 学习项目


## Kotlin 基础

val (value 的简写)用来声明一个不可变的变量。
var (variable 的简写)用来声明一个可变的变量。

### 基础数据类型：

Int, Long, Short, Float, Double, Boolean, Char, Byte

```kotlin
var a: Int = 10
a = a * 10
println("a = " + a)
```

### 函数：

```kotlin
fun largerNumber(num1: Int, num2: Int): Int {
    return max(num1, num2)
}

fun largerNumber2(num1: Int, num2: Int) = max(num1, num2)



/// 匹配值 -> { 执行逻辑 }
fun getScore(name: String = "Tom") = when (name) {
    "Tom" -> 86
    "Jim" -> 77
    "Lily" -> 100
    else -> 0
}

val range = 0..10 // [0, 10]两端都是闭区间
for (i in 0..10) {
    println(i)
}

val range = 0 until 10 // [0, 10)
for (i in 0 until 10 step 2) {
    println(i) // 0 2 4 6 8
}


for (i in 10 downTo 1) { 
    println(i) // 10 , 9, 8, ... , 1
}
```

### 面向对象

```kotlin
// 需要添加open 关键字才可以被继承
open class Persion(val name: String, val age: Int) {}

class Student(val sno: String, val grade: Int, name: String, age: Int): Persion(name, age) {
    init {
        println("sno is" + sno)
        println("grade is " + grade)
    }
    
    // 次构造器
    constructor(name: String, age: Int): this("", 0, name, age)
}
```

### 接口

```kotlin
interface Study {
    fun readBooks()
    
    // 提供默认实现
    fun doHomework() {
        println("do homework")
    }
}

class Student(name: String, age: Int) : Persion(name, age), Study {
    override fun readBooks() {
        println(name + "is reading")
    }
}
```


public 所有类可见，private 当前类可见，protected 当前类和子类可见, internal 同一模块的类可见

### 单例

```kotlin
object Singleton {}
```

### Lambda

{参数名1： 参数类型，参数名2: 参数类型 -> 函数体 }

```kotlin
val list = ArrayList<String>()
list.add("apple")
list.add("banana")

var list = listOf<String>("apple", "banana")

list.maxBy({fr : String -> fr.length })

var map = HashMap<String, Int>()
map.put("apple", 1)
map.put("banan", 2)
map["tmp"] = 3

var map = mapOf("Apple" to 1, "banan" to 2)

for ((k, v) in map) {
    println("key is " + k + "value is" + v)
}
```


let 函数： 后面的lambda 表达式中的代码会立即执行，并且这个obj 对象本身还会传递到 Lambda 表达式中

```kotlin
obj.let { obj2 -> 
    // 编写具体业务
}

/// ?. 操作符表示对象为空什么都不做，对象不为空就用let函数，而let 函数会将 study 对象本身传递到 lambda 中。此时 study 肯定不为空
fun doStudy(study: Study?) {
    study?.let {stu -> 
        stu.readBooks()
    }
}
```

```kotlin

"hello, ${obj.name} nice to meet you"
"hello, $name nice to meet you!"
```







