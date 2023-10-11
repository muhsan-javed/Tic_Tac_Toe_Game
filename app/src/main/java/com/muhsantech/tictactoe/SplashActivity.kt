package com.muhsantech.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.muhsantech.tictactoe.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageIcon.translationY = -1000f
        binding.TitleTextView.translationY = 1000f

        binding.imageIcon.animate().translationY(0f).duration = 2000
        binding.TitleTextView.animate().translationY(0f).duration = 2000


        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashActivity, StartActivity::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}