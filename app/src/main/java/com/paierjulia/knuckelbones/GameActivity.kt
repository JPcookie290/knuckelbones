package com.paierjulia.knuckelbones

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.paierjulia.knuckelbones.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private lateinit var gameController: GameController

    private var currentDiceValue: Int = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Input Collection
        val playerName = intent.getStringExtra("playerOne") ?: "Player 1"
        gameController = GameController(playerName) {
            updateUI()
        }

        // View Binding
        // Player Info
        binding.playerOneName.text = gameController.playerOne.name
        binding.playerOnePoints.text = gameController.playerOne.totalPoints.toString()
        binding.playerTwoName.text = gameController.playerTwo.name
        binding.playerTwoPoints.text = gameController.playerTwo.totalPoints.toString()

        // Dice Roll
        binding.diceRollButton.setOnClickListener {
            currentDiceValue = gameController.rollDice()
            updateRollDice(currentDiceValue)
            // TODO : add visual clue who is active
            /*
            if (gameController.getCurrentPlayer() == gameController.playerOne) {
            } else {
            }
            */
        }

        setupColumnClickListeners()
    }

    private fun setupColumnClickListeners() {
        binding.diceFieldPlayerOne.columnOne.setOnClickListener { handleColumnClick(1, gameController.playerOne) }
        binding.diceFieldPlayerOne.columnTwo.setOnClickListener { handleColumnClick(2, gameController.playerOne) }
        binding.diceFieldPlayerOne.columnThree.setOnClickListener { handleColumnClick(3, gameController.playerOne) }

        if (gameController.twoPlayers) {
            binding.diceFieldPlayerTwo.columnOne.setOnClickListener { handleColumnClick(1, gameController.playerTwo) }
            binding.diceFieldPlayerTwo.columnTwo.setOnClickListener { handleColumnClick(2, gameController.playerTwo) }
            binding.diceFieldPlayerTwo.columnThree.setOnClickListener { handleColumnClick(3, gameController.playerTwo) }
        }
    }

    private fun handleColumnClick(columnId: Int, player: Player) {
        if (gameController.getCurrentPlayer() == player) {
            Log.d("GameActivity", "${player.name}: dice in column $columnId")
            gameController.placeDice(columnId)
            updateColumnUI(columnId, player)
            gameController.checkGameEnd()
        } else {
            Toast.makeText(this, gameController.getCurrentPlayer().name, Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateColumnUI(columnId: Int, player: Player) {
        try {
            val columnDice = player.diceField.getColumnDice(columnId)
            val columnView = when (columnId) {
                1 -> if (player == gameController.playerOne) binding.diceFieldPlayerOne.columnOne else binding.diceFieldPlayerTwo.columnOne
                2 -> if (player == gameController.playerOne) binding.diceFieldPlayerOne.columnTwo else binding.diceFieldPlayerTwo.columnTwo
                3 -> if (player == gameController.playerOne) binding.diceFieldPlayerOne.columnThree else binding.diceFieldPlayerTwo.columnThree
                else -> throw IllegalArgumentException("Invalid column ID")
            }

            for (i in columnDice.indices) {
                val diceImageView = columnView.getChildAt(i) as ImageView
                diceImageView.setImageResource(gameController.getDiceImageResource(columnDice[i]))
            }
            val pointsTextView = columnView.getChildAt(3) as TextView
            pointsTextView.text = player.diceField.getColumnValue(columnId).toString()
            updatePlayerPoints()
        } catch (e: Exception) {
            Log.e("GameActivity", "Error updating column UI: ${e.message}")
        }

    }

    private fun updatePlayerPoints() {
        binding.playerOnePoints.text = gameController.playerOne.totalPoints.toString()
        binding.playerTwoPoints.text = gameController.playerTwo.totalPoints.toString()
    }

    private fun updateRollDice(value: Int) {
        binding.diceRollButton.setImageResource(gameController.getDiceImageResource(value))
    }


    private fun updateUI() {
        binding.diceRollButton.setImageResource(gameController.getDiceImageResource(gameController.lastRolledDice))

        updateColumnUI(1, gameController.playerOne)
        updateColumnUI(2, gameController.playerOne)
        updateColumnUI(3, gameController.playerOne)
        updateColumnUI(1, gameController.playerTwo)
        updateColumnUI(2, gameController.playerTwo)
        updateColumnUI(3, gameController.playerTwo)

        updatePlayerPoints()
    }


}
