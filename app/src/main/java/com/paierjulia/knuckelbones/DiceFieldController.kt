package com.paierjulia.knuckelbones

class DiceFieldController {
    private val columnOne = mutableListOf<Int>()
    private val columnTwo = mutableListOf<Int>()
    private val columnThree = mutableListOf<Int>()

    private var rolledDice: Int = 0
    private var filledBoard: Boolean = false

    fun placeDiceInColumn(columnId: Int) {
        when (columnId) {
            1 -> if (columnOne.size < 3) columnOne.add(rolledDice)
            2 -> if (columnTwo.size < 3) columnTwo.add(rolledDice)
            3 -> if (columnThree.size < 3) columnThree.add(rolledDice)
            else -> throw IllegalArgumentException("Invalid column ID")
        }

        filledBoard = columnOne.size == 3 && columnTwo.size == 3 && columnThree.size == 3
    }

    // TODO rework for points
    fun getColumnValue(columnId: Int): Int {
        return when (columnId) {
            1 -> columnOne.sum()
            2 -> columnTwo.sum()
            3 -> columnThree.sum()
            else -> throw IllegalArgumentException("Invalid column ID")
        }
    }

    fun getColumnDice(columnId: Int): List<Int> {
        return when (columnId) {
            1 -> columnOne
            2 -> columnTwo
            3 -> columnThree
            else -> throw IllegalArgumentException("Invalid column ID")
        }
    }

    fun setRolledDice(rolledValue: Int) {
        rolledDice = rolledValue
    }

    fun getRolledDice(): Int = rolledDice

    fun isBoardFilled(): Boolean = filledBoard
}
