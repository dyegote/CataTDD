package com.example.testtdd

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class OperationTest {

    private lateinit var operation: Operation;

    @Before
    fun init() {
        operation = Operation()
    }

    @Test
    fun sum() {
        //Arrange
        val expected = 3;
        val num1 = 1
        val num2 = 2

        //Act
        val result = operation.sum(num1, num2)

        //Assert
        assertEquals(result, 3)
    }

}