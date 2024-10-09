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
            currentDiceValue = gameController.rollDice() // TODO: this will be changed
            // for testing
            if (currentDiceValue > 3) {
                playerTwoPoints.text = currentDiceValue.toString()
                binding.diceRollButton.setBackgroundColor(Color.parseColor("#FFC0CB"))
            } else {
                playerOnePoints.text = currentDiceValue.toString()
                binding.diceRollButton.setBackgroundColor(Color.parseColor("#0000FF"))
            }
        }

        // Dice Fields



    }
}