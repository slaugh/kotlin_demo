package com.example.kotlindemo

import org.junit.Assert.assertThat
import org.junit.Test

class ShapeValidatorTest {
    @Test
    fun shapeValidator_Square() {
        var square = Square(4f)
        assert(square.area == 16f)
        assert(square.name == "Square")
        assert(square.name != "Circle")
    }
}