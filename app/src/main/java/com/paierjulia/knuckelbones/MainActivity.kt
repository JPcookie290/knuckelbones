package com.paierjulia.knuckelbones

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.paierjulia.knuckelbones.databinding.ActivityMainBinding
import com.paierjulia.knuckelbones.databinding.DialogInfoGameBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Start Button
        binding.startButton.setOnClickListener {
            var playerName = binding.inputPlayerOne.text.toString()
            if (playerName.isBlank()){
                playerName = "Player 1"
            }


            val intent = Intent(this, GameActivity::class.java).apply {
                putExtra( "playerOne", playerName)
            }
            startActivity(intent)
        }

        // Info Button
        binding.infoButton.setOnClickListener {
            showGameInfoDialog()
        }
    }

    private fun showGameInfoDialog() {
        val dialogBinding = DialogInfoGameBinding.inflate(LayoutInflater.from(this))

        val builder = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .create()

        dialogBinding.dialogCloseButton.setOnClickListener {
            builder.dismiss()
        }

        builder.show()
    }
}