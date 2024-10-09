package com.paierjulia.knuckelbones

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.paierjulia.knuckelbones.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
    }
}