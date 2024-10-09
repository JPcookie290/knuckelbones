package com.paierjulia.knuckelbones

class DiceFieldController {
    private var rolledDice: Int = 0
    private val columnOne = mutableListOf<Int>()
    private val columnTwo = mutableListOf<Int>()
    private val columnThree = mutableListOf<Int>()

    fun placeDiceInColumn(columnId: Int, diceValue: Int) {
        when (columnId) {
            1 -> columnOne.add(diceValue)
            2 -> columnTwo.add(diceValue)
            3 -> columnThree.add(diceValue)
            else -> throw IllegalArgumentException("Invalid ID")
        }
    }

    fun getColumnValue(columnId: Int): Int {
        return when (columnId) {
            1 -> columnOne.sum()
            2 -> columnTwo.sum()
            3 -> columnThree.sum()
            else -> throw IllegalArgumentException("Invalid ID")
        }
    }

    fun getColumnDice(columnId: Int): List<Int> {
        return when (columnId) {
            1 -> columnOne
            2 -> columnTwo
            3 -> columnThree
            else -> throw IllegalArgumentException("Invalid ID")
        }
    }

    fun setRolledDice( rolledValue: Int){
        rolledDice = rolledValue
    }

    fun getRolledDice(): Int = rolledDice
}