package com.example.textdemo2

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.json.JSONObject

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.textdemo", appContext.packageName)
        //创建数组
        val a by lazy {
            arrayListOf<Int>(11, 22, 12, 1111, 21, 442, 45)
        }
        //排序
        a.sort()
        var iterator = a.iterator()
        while (iterator.hasNext()){
            val next = iterator.next();
            if(next==22){
                iterator.remove()
            }
        }
        for (i in a) {
            println(i)
        }


        action(10){
            println("action方法$it")
        }
        action2(11,object :IA{
            override fun fun1(i: Int) {
                println("action2方法$i")
            }
        })




        var person:Person?=null
        person=Person(1,"")
        person=null
        var text=when(person?.age){
            1->{
                "这是1,"
            }
            2->{
                "这是2,"
            }
            else->{
                "这是else"
            }

        }
        println("${person?.age}when输出的值=$text")

    }

    fun action(i:Int ,ac:(value:Int)->Unit):Unit{
        ac.invoke(i)
    }
    fun action2(i:Int ,ac:IA){
        ac.fun1(i)
    }



    @Test
    fun Text2(){
        val b = false
        require(b)
        println("end")

        val nullableList: List<Int?> = listOf(1, 2, null, 4)
        val intList: List<Int> = nullableList.filterNotNull()
        println(intList)

    }


    // 冷流-> ChannelFlow， FLow
    //热流-> StateFlow, SharedFlow
    // 上游，中游，下游
    // 最上面 中问 最下面
    //生产消费模型
    // produce -> eat
    // 冷流是指，不 collect 流不会调用(执行)// 热流是指，不管 collect 流都会执行
    // 对于 fLow 的所有挂起函数，都是 末端操作符
    // 对于 fLow 的其它操作，都属于变换
    @Test
    fun Text3(){
        runBlocking {
            val flow = creatFlow()
            flow.map {
                "姓名="+it
            }.onEach {

            }.distinctUntilChanged { old, new ->
                println("旧的=$old,  新的=$new")
                true
            }.collect{

            }
        }
    }
    fun creatFlow():kotlinx.coroutines.flow.Flow<String>{
        return flow<String> {
            repeat(100){
                emit("张三")
                emit("李四")
            }

        }
    }
    @Test
    fun Text4(){
        var string1:String="账单"
        var sure= string1 join "平账"
        println(sure)

    }


    class sTest {
        var oldName: Int = 0
        var newName: Int by ::oldName
    }

    @Test
    fun Text5() {
        val test = sTest()
        test.newName = 100
        println("旧的="+test.oldName)
        println("新的="+test.newName)
    }
    @Test
    fun Text6() {
        compose("猪八戒"){ s: String, i: Int ->

            println("打印=$s+$i")
        }
    }
    @Test
    fun Text7() {
        var jsonObject = JSONObject(mapOf("cod1e" to 101))
        println( jsonObject.optString("code","102")?:"99")
    }


    @Test
    fun Text8() {
        var s1:String?="1"
        s1=null
        var s2=s1?.toInt()?.plus(2)?.plus(1)?:"11"
        println("结果等于="+s2)
    }


}

fun compose(needStr:String,need:(name:String,age:Int)->Unit){
    need.invoke(needStr,needStr.length)
}

infix fun String.join(string: String):String {
    return this+string
}

fun interface IA{
    fun fun1(i:Int)
}

data class Person(var age:Int=0,var city:String){
    var name:String=""

}


