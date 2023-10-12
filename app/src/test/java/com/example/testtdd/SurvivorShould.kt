package com.example.testtdd

import com.example.testtdd.core.domain.entities.Bag
import com.example.testtdd.core.domain.entities.Equipment
import com.example.testtdd.core.domain.entities.Survivor
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Test

class SurvivorShould {

    companion object {
        private lateinit var survivor: Survivor
        private lateinit var attackedSurvivor: Survivor
        private lateinit var myBag: Bag
    }

    private fun givenASurvivorNamed(name: String) {
        survivor = Survivor(name, bag = myBag)
    }

    private fun givenAAtteckdSurvivorNamed(name: String) {
        attackedSurvivor = Survivor(name, bag = myBag)
    }

    private fun givenABag(bag: Bag) {
        myBag = bag
    }


    @Test
    fun `has not wounds`() {
        givenABag(mockk(relaxed = true))
        whenCreateASurvivor("Alex", myBag)

        val expectedSurvivor = Survivor("Alex", 0, 3, bag = myBag)
        thenTheSurvivorIs(expectedSurvivor)
    }


    @Test
    fun `has received an attack from another survivor`() {
        givenABag(mockk(relaxed = true))
        givenASurvivorNamed("Alex")

        givenAAtteckdSurvivorNamed("Julian")
        survivor.attack(attackedSurvivor)//cuando le pido las heridas
        val wounds: Int = attackedSurvivor.wounds
        //Entonces espero que tenga 0 heridas
        assertEquals(1, wounds)
    }


    @Test
    fun `has received 2 wounds then is dead`() {
        givenABag(mockk(relaxed = true))

        val survivor = Survivor("Alex", bag = myBag)
        val attackedSurvivor = Survivor("Julian", bag = myBag)

        survivor.attack(attackedSurvivor)//cuando le pido las heridas
        survivor.attack(attackedSurvivor)//cuando le pido las heridas
        //Entonces espero que tenga 0 heridas
        assertEquals(false, attackedSurvivor.life())
    }

    @Test
    fun `has received 1 wounds then is not dead`() {
        givenABag(mockk(relaxed = true))
        val survivor = Survivor("Alex", bag = myBag)
        val attackedSurvivor = Survivor("Julian", bag = myBag)
        survivor.attack(attackedSurvivor)//cuando le pido las heridas
        val hasLife = survivor.life()
        //Entonces espero que tenga 0 heridas
        assertEquals(true, hasLife)
    }

    @Test
    fun `has received more than 2 wounds then additionals wound are ignored`() {
        givenABag(mockk(relaxed = true))
        val survivor = Survivor("Alex", bag = myBag)
        val survivor2 = Survivor("Julian", bag = myBag)
        survivor.attack(survivor2)//cuando le pido las heridas
        survivor.attack(survivor2)//cuando le pido las heridas
        survivor.attack(survivor2)//cuando le pido las heridas


        val wounds: Int = survivor2.wounds
        //Entonces espero que tenga 2 heridas
        assertEquals(2, wounds)
    }

    @Test
    fun `has pick up an equipment piece`() {
        givenABag(mockk(relaxed = true))
        givenASurvivorNamed("Alex")
        val baseballBat: Equipment = mockk()


        survivor.pickUp(baseballBat)


        verify { myBag.addEquipment(baseballBat) }
    }

    private fun thenTheSurvivorIs(expectedSurvivor: Survivor) {
        assertEquals(expectedSurvivor, survivor)
    }

    private fun whenCreateASurvivor(name: String, bag: Bag) {
        survivor = Survivor(name, bag = bag)
    }
}

