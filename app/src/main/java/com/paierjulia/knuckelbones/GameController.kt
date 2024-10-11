package com.paierjulia.knuckelbones

class GameController(private val playerName: String) {
    lateinit var playerOne: Player
    lateinit var playerTwo: Player

    private var currentPlayer : Player
    var twoPlayers : Boolean = false

    init {
        initializePlayers()
        currentPlayer = playerOne
    }

    private fun initializePlayers() {
        playerOne = Player(playerName)
        playerTwo = ComputerPlayer()
    }

    fun rollDice() : Int {
        var value = (1..6).random()
        currentPlayer.setRolledDice(value)
        return value
    }

    fun placeDice(column : Int) {
        if (currentPlayer.getRolledDice() == 0) return
        currentPlayer.placeDice(column)
        changePlayer()
        if (!twoPlayers){
            computerTurn()
        }
    }

   fun changePlayer() {
        currentPlayer = if (currentPlayer == playerOne) playerTwo else playerOne
    }

    fun getCurrentPlayer(): Player = currentPlayer

    private fun computerTurn() {
        rollDice()
        (playerTwo as ComputerPlayer).computerTurn(playerOne.diceField)
        changePlayer()
    }

    fun getDiceImageResource(value: Int): Int {
        return when (value) {
            1 -> R.drawable.dice_one_small
            2 -> R.drawable.dice_two_small
            3 -> R.drawable.dice_three_small
            4 -> R.drawable.dice_four_small
            5 -> R.drawable.dice_five_small
            6 -> R.drawable.dice_six_small
            else -> R.drawable.dice_one_small // default one
        }
    }

    fun checkGameEnd(){

    }
}