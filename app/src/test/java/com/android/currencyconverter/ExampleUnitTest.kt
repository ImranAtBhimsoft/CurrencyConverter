package com.android.currencyconverter

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun myTest(){
        //// input : "aaaa bb ccc a b"
        //// output : "a4b2c3a1b1"

    }

    fun abc(input:String):String{
        if(input.length==0)return ""
        //// input : "aaaab b ccc ab"
        //// output : "a4b2c3a1b1"
        var count =0
        var lastChar:Char = ' '
        val list = mutableListOf<String>()
        input.forEach {
            if(lastChar == ' ' || it==lastChar){
                count++ // 4
            }else{
                list.add(lastChar+"$count") // a4b2
                count = 1
            }

            lastChar = it
        }
        list.add(lastChar+"$count")
        println(list)
        return  ""
    }
}