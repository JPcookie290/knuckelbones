package com.paierjulia.knuckelbones

import android.util.Log

class ComputerPlayer(name: String = "Computer") : Player(name) {

    fun computerTurn(opponent: Player) {
        var columnId = preferredColumn(opponent.diceField)
        Log.d("ComputerPlayer", "Chosen column: $columnId, current rolledDice: ${diceField.getRolledDice()}")
        diceField.placeDiceInColumn(columnId)
        removeOpponentInstances(columnId, opponent)
    }

    fun preferredColumn(opponent: DiceFieldController): Int {

        for (column in 1..3) {
            val currentColumn = diceField.getColumnDice(column)
            if (currentColumn.size < 3 && currentColumn.contains(diceField.getRolledDice())) {
                return column
            }
        }

        for (column in 1..3) {
            val opponentColumn = opponent.getColumnDice(column)
            if (opponentColumn.contains(diceField.getRolledDice())) {
                return column
            }
        }

        return (1..3).minByOrNull { diceField.getColumnDice(it).size } ?: 1
    }
}
