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

    fun placeDice(column : Int, opponent: Player){
        diceField.placeDiceInColumn(column)
        removeOpponentInstances(column, opponent)
        totalPoints = diceField.calculateTotalPoints()
    }

    fun removeOpponentInstances( column: Int, opponent: Player) {
        opponent.diceField.removeAllInstancesInColumn(column, getRolledDice())
    }

    // TODO: not working
    fun checkBoard() : Boolean {
        return diceField.isBoardFilled()
    }
}