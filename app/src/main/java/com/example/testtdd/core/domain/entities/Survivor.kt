package com.example.testtdd.core.domain.entities

data class Survivor(
    val name: String,
    var wounds: Int = 0,
    val actionPerTurn: Int = 3,
    val bag: Bag
) {

    fun attack(survivor: Survivor) {
        survivor.receiveAttack()
    }

    private fun receiveAttack() {
        if (wounds < MAX_WOUNDS) {
            this.wounds++
        }
    }

    fun life(): Boolean {
        return wounds <= 1
    }

    fun pickUp(equipment: Equipment) {
        bag.addEquipment(equipment)
    }

    companion object {
        private const val MAX_WOUNDS = 2
    }

}

interface Equipment {

}

class Bag(private val equipments: MutableList<Equipment>) {
    fun addEquipment(baseballBat: Equipment) {
        if (equipments.size < 3) {
            equipments.add(baseballBat)
        }
    }
}
