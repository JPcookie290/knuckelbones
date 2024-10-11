package com.paierjulia.knuckelbones

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.paierjulia.knuckelbones.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity(){
    private lateinit var binding: ActivityGameBinding
    private lateinit var gameController: GameController

    private var currentDiceValue : Int = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Input Collection
        val playerName = intent.getStringExtra("playerOne") ?: "Player 1"
        gameController = GameController(playerName)

        // View Binding
        // Player Info
        binding.playerOneName.text = gameController.playerOne.name
        val playerOnePoints = binding.playerOnePoints
        playerOnePoints.text = gameController.playerOne.totalPoints.toString()
        binding.playerTwoName.text = gameController.playerTwo.name
        val playerTwoPoints = binding.playerTwoPoints
        playerTwoPoints.text = gameController.playerTwo.totalPoints.toString()

        // Dice Roll
        binding.diceRollButton.setOnClickListener{
            currentDiceValue = gameController.rollDice()
            updateRollDice(currentDiceValue)
            if (gameController.getCurrentPlayer() == gameController.playerOne){
                playerOnePoints.text = currentDiceValue.toString()
            } else {
                playerTwoPoints.text = currentDiceValue.toString()
            }
            gameController.changePlayer()

        }

        // Dice Fields
        // Player 1
        var columnOne = binding.diceFieldPlayerOne.columnOne
        var columnTwo = binding.diceFieldPlayerOne.columnTwo
        var columnThree = binding.diceFieldPlayerOne.columnThree


        if (gameController.getCurrentPlayer() == gameController.playerOne){
            columnOne.setOnClickListener{
                columnOne.setBackgroundColor(Color.parseColor("#FFC0CB"))
                //gameController.placeDice(1)
            }
            columnTwo.setOnClickListener{
                columnTwo.setBackgroundColor(Color.parseColor("#0000FF"))
                //gameController.placeDice(2)
            }
            columnThree.setOnClickListener{
                columnThree.setBackgroundColor(Color.parseColor("#FFC0CB"))
                //gameController.placeDice(3)
            }
        }

        // Player 2 if not against a computer
        if (gameController.twoPlayers) {
            var columnOnePlayerTwo = binding.diceFieldPlayerTwo.columnOne
            var columnTwoPlayerTwo = binding.diceFieldPlayerTwo.columnTwo
            var columnThreePlayerTwo = binding.diceFieldPlayerTwo.columnThree

            if (gameController.getCurrentPlayer() == gameController.playerTwo){
                columnOnePlayerTwo.setOnClickListener{
                    gameController.placeDice(1)
                }
                columnTwoPlayerTwo.setOnClickListener{
                    gameController.placeDice(2)
                }
                columnThreePlayerTwo.setOnClickListener{
                    gameController.placeDice(3)
                }
            }


        }


    }
    fun updateRollDice(value: Int) {
        binding.diceRollButton.setImageResource(gameController.getDiceImageResource(value))
    }
}