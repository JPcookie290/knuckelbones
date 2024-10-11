package com.paierjulia.knuckelbones

class ComputerPlayer(name: String = "Computer") : Player(name) {

    fun computerTurn(opponent : DiceFieldController){
        diceField.placeDiceInColumn(preferredColumn( opponent))
    }

    fun preferredColumn( opponent: DiceFieldController): Int {

        for (column in 0..2) {
            val currentColumn = diceField.getColumnDice(column)
            if (currentColumn.size < 3 && currentColumn.contains(diceField.getRolledDice())) {
                return column
            }
        }

        for (column in 0..2) {
            val opponentColumn = opponent.getColumnDice(column)
            if (opponentColumn.contains(diceField.getRolledDice())) {
                return column
            }
        }

        return (0..2).minByOrNull { diceField.getColumnDice(it).size } ?: 0
    }

}