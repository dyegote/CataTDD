package com.example.testtdd

import org.junit.Assert.assertEquals
import org.junit.Test

class SurvivorShould {

    @Test
    fun `has a name`(){

        //dado un sobreviviente
        val survivor = Survivor("Alex")

        //cuando le pido el nombre
        val survivorName: String = survivor.getExpectedName()
        //Entonces espero que tenga nombre
        assertEquals("Alex", survivorName)
    }

    @Test
    fun `has other name`(){

        //dado un sobreviviente
        val survivor = Survivor("Diego")

        //cuando le pido el nombre
        val survivorName: String = survivor.getExpectedName()
        //Entonces espero que tenga nombre
        assertEquals("Diego", survivorName)
    }
    @Test
    fun `has not wounds`(){

        //dado un sobreviviente
        val survivor = Survivor("Alex")

        //cuando le pido las heridas
        val wounds: Int = survivor.wounds
        //Entonces espero que tenga 0 heridas
        assertEquals(0, wounds)
    }

    @Test
    fun `has received an attack from another survivor`(){

        //dado un sobreviviente
        val survivor = Survivor("Alex")
        val survivor2 = Survivor("Julian")
        survivor.attack(survivor2)//cuando le pido las heridas
        val wounds: Int = survivor2.wounds
        //Entonces espero que tenga 0 heridas
        assertEquals(1, wounds)
    }



}

data class Survivor(val name: String, var wounds: Int = 0) {
    fun getExpectedName(): String {
        return this.name
    }

    private fun receiveAttack() {
        this.wounds++
    }

    fun attack(survivor: Survivor) {
        survivor.receiveAttack()
    }


}
