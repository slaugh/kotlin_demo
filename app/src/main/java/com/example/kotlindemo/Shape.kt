package com.example.kotlindemo

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.shapes.Shape

/*
This Function is takes in the side of a square S and then multiplies S * S to return the squared value
This is mainly being used to experiment with unit tests in Kotlin
 */
fun square1(side: Float): Float{
    return side * side
}


/*\
This is a basic Shape class being used to experiment with unit tests in Kotlin
 */
open class Shape2D(){
    /*
This Function is takes in the side of a square S and then multiplies S * S to return the squared value
 */
    var area: Float = 0f
    var name: String = ""

}

/*\
This is a basic Square class being used to experiment with inheritance and unit testing in Kotlin
 */
class Square(side: Float): Shape2D() {
    /*
    This Function is takes in the side of a square S and then multiplies S * S to return the squared value
    This is mainly being used to experiment with unit tests in Kotlin
     */
    var side: Float = 0f

    init {
        this.name = "Square"
        this.side = side
        this.area = square2(side)
    }

    fun square2(side: Float): Float{
        return side * side
    }
}


