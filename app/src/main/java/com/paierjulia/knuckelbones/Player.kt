package com.paierjulia.knuckelbones

open class Player(
    val name: String
){
    val diceField = DiceFieldController()
    var totalPoints: Int = 0

    fun setRolledDice(value : Int) {
        diceField.setRolledDice(value)
    }

    fun getRolledDice() : Int {
        return diceField.getRolledDice()
    }

    fun placeDice(column : Int){
        diceField.placeDiceInColumn(column)
    }

    fun checkBoard() : Boolean {
        return diceField.isBoardFilled()
    }
}