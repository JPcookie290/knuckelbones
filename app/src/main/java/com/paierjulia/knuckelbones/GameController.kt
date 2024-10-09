package com.paierjulia.knuckelbones

class GameController(private val playerName: String) {
    lateinit var playerOne: Player
    lateinit var playerTwo: Player

    private var currentPlayer : Player = playerOne

    init {
        initializePlayers()
    }

    private fun initializePlayers() {
        playerOne = Player(playerName)
        playerTwo = ComputerPlayer()
    }

    fun rollDice() : Int {
        var value = (1..6).random()
        currentPlayer.diceField.setRolledDice(value)
        return value
    }

    fun placeDice(column : Int) {
        currentPlayer.diceField.placeDiceInColumn(column, currentPlayer.diceField.getRolledDice())
    }

    fun changePlayer() {
        currentPlayer = if (currentPlayer == playerOne) playerTwo else playerOne
    }

    fun getCurrentPlayer(): Player = currentPlayer
}