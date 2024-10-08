package com.paierjulia.knuckelbones

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.paierjulia.knuckelbones.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}