package com.paierjulia.knuckelbones

import android.util.Log

class GameController(
    private val playerName: String,
    private val updateUI: () -> Unit
) {
    lateinit var playerOne: Player
    lateinit var playerTwo: Player

    private var currentPlayer: Player
    val currentOpponent: Player
    var twoPlayers: Boolean = false
    var lastRolledDice: Int = 0

    init {
        initializePlayers()
        currentPlayer = playerOne
        currentOpponent = playerTwo
    }

    private fun initializePlayers() {
        playerOne = Player(playerName)
        playerTwo = if (twoPlayers) Player("Player 2") else ComputerPlayer()
    }

    fun rollDice(): Int {
        lastRolledDice = (1..6).random()
        currentPlayer.setRolledDice(lastRolledDice)
        Log.d("GameController", "Dice: $lastRolledDice")
        return lastRolledDice
    }

    fun placeDice(column: Int) {
        setCurrentOpponent()
        if (currentPlayer.getRolledDice() == 0) return
        currentPlayer.placeDice(column, currentOpponent)

        changePlayer()
        if (!twoPlayers) {
            computerTurn()
        }
    }

    fun changePlayer() {
        currentPlayer = if (currentPlayer == playerOne) playerTwo else playerOne
    }

    fun setCurrentOpponent() {
        if (currentPlayer == playerOne) playerTwo else playerOne
    }

    fun removeOpponentInstances() {

    }

    fun getCurrentPlayer(): Player = currentPlayer

    private fun computerTurn() {
        rollDice()
        (playerTwo as ComputerPlayer).computerTurn(playerOne)
        updateUI()
        checkGameEnd()
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

    // TODO: not working
    fun checkGameEnd() {
        if (currentPlayer.checkBoard()){
            Log.d("GameController", "Board filled player: $currentPlayer")
            if (playerOne.totalPoints > playerTwo.totalPoints){
                Log.d("GameController", "$playerOne won against $playerTwo")
            } else if (playerOne.totalPoints == playerTwo.totalPoints){
                Log.d("GameController", "It's a draw!")
            } else {
                Log.d("GameController", "$playerTwo won against $playerOne")
            }
        }
    }
}
