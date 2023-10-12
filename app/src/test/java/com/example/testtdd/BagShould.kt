package com.example.testtdd

import com.example.testtdd.core.domain.entities.Bag
import com.example.testtdd.core.domain.entities.Equipment
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class BagShould {

    companion object {
        private lateinit var myBag: Bag
    }

    @Test
    fun `add equipment`(){
        val equipments = mutableListOf<Equipment>()
        myBag = Bag(equipments)
        val baseballBat: Equipment = mockk()

        myBag.addEquipment(baseballBat)

        Assert.assertEquals(1, equipments.size)
    }


    @Test
    fun `not add more than 3 equipments`(){
        val equipments = mutableListOf<Equipment>()
        myBag = Bag(equipments)
        val baseballBat: Equipment = mockk()

        myBag.addEquipment(baseballBat)
        myBag.addEquipment(baseballBat)
        myBag.addEquipment(baseballBat)
        myBag.addEquipment(baseballBat)

        Assert.assertEquals(3, equipments.size)
    }


}