package com.paierjulia.knuckelbones

import android.util.Log

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
        Log.d("DiceFieldController", "Points ${calculateTotalPoints()}")
        filledBoard = columnOne.size == 3 && columnTwo.size == 3 && columnThree.size == 3
    }

    private fun calculateColumnScore(column: List<Int>): Int {
        var totalScore = 0

        for (diceValue in column.distinct()) {
            val count = column.count { it == diceValue }
            totalScore += diceValue * count * count
        }

        return totalScore
    }

    // TODO: not working right
    fun removeAllInstancesInColumn(columnId: Int, value: Int) {
        when (columnId) {
            1 -> columnOne.removeAll { it == value }
            2 -> columnTwo.removeAll { it == value }
            3 -> columnThree.removeAll { it == value }
            else -> throw IllegalArgumentException("Invalid column ID")
        }
    }


    fun getColumnValue(columnId: Int): Int {
        val column = when (columnId) {
            1 -> columnOne
            2 -> columnTwo
            3 -> columnThree
            else -> throw IllegalArgumentException("Invalid column ID")
        }

        return calculateColumnScore(column)
    }

    fun calculateTotalPoints(): Int {
        val columnOneScore = calculateColumnScore(columnOne)
        val columnTwoScore = calculateColumnScore(columnTwo)
        val columnThreeScore = calculateColumnScore(columnThree)

        return columnOneScore + columnTwoScore + columnThreeScore
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

    fun clearBoard() {
        columnOne.clear()
        columnTwo.clear()
        columnThree.clear()
    }
}
