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


## android

重写方法快捷键： control + O

### Intent

Intent 是android 程序中各组件之间进行交互的一种重要方式，它不仅可以指明当前组件想要执行的动作，还可以在不同组件之间传递数据。
Intent 一般可用于启动Activity,启动 Service 以及发送广播等场景

Intent 大致可分为：显示Intent 和隐式Intent

```kotlin
btn.setOnClickListener {
    val intent = Intent(this, FirstActivity::class.java)
    startActivity(intent)
}
```
Intent 的意图非常明显，所以这种叫做 显示 Intent

隐式的话，相对比较含蓄，并不明确指出想要启动哪个activity,而是指定一些类更为抽象的 action 和 category 等信息，
然后交由系统去分析这个 intent,并帮我们找出合适的Activity.

```xml
 <activity
    android:name=".FirstActivity"
    android:exported="true" >
    <intent-filter>
        <action android:name="com.example.android_learn.ACTION_START" />
        <category android:name="android.intent.category.DEFAULT"/>
    </intent-filter>
</activity>
```

```kotlin
btn.setOnClickListener {
    val intent = Intent("com.example.android_learn.ACTION_START")
    startActivity(intent)
}
```

`<action>` 标签中我们指定了当前 Activity 可以响应的 action, 而 `<category>` 标签则包含了一些附加信息，更精确地指明了当前 Activity 能够响应的 Intent 
中还可能带有的 category. 只有 action 和 category 同时匹配，这个 activity 才能响应该 Intent.

而 `android.intent.category.DEFAULT` 是一种默认的category, 在调用 startActivity() 方法的时候会自动将这个 activity 添加到 Intent 中。

```kotlin
btn.setOnClickListener {
    val intent = Intent("com.example.android_learn.ACTION_START")
    intent.addCategory("com.example.android_learn.My_CATEGORY")
    startActivity(intent)
}
```

### 数据传递

#### 正向传递

Intent 中提供了一系列的 putExtra() 方法的重载，可以把数据暂存在 Intent 中，启动另一个Activity 后，只要把这个数据从 Intent 中取出来即可。

```kotlin
btn.setOnClickListener {
    val intent = Intent(this, FirstActivity::class.java)
    intent.putExtra("extra_data", "hello oldbirds")
    startActivity(intent)
}
```

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_first)
    val extraData = intent.getStringExtra("extra_data")
    Log.d("FirstActivity", "extradta is $extraData")
}
```

#### 反向传值

通过 `startActivityForResult()`

```kotlin
val btn = findViewById<Button>(R.id.first_active_back)
btn.setOnClickListener { 
    val intent = Intent()
    intent.putExtra("data_return", "返回数据")
    setResult(RESULT_OK, intent)
    finish()
}
```

```kotlin
btn.setOnClickListener {
    val intent = Intent(this, FirstActivity::class.java)
    intent.putExtra("extra_data", "hello oldbirds")
    startActivityForResult(intent, 1)
}

override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    when(requestCode) {
        1 -> if (resultCode == RESULT_OK) {
            val returnData = data?.getStringExtra("data_return")
            Log.d("MainActivity", "returned data is $returnData")
        }
    }
}
```

#### activity 的生存期

* onCreate() , Activity 第一次被创建的时候调用，你应该在整个方法中完成 Activity 的初始化操作，比如加载布局，绑定时间等
* onStart(). Activity 由不可见变为可见的时候调用
* onResume() , Activity 准备好喝用户进行交互的时候调用，此时的Activity 已定位与返回栈的栈顶，并且处于运行状态
* onPause() , 系统准备去启动或者回复赢一个Activity 的时候调用
* onStop（）， 在 Activity 完全不可见的时候调用
* onDestroy(), 这个方法在 Activity 被销毁前调用 
* onRestart() ，Activity 由停止状态变为运行状态之前调用






