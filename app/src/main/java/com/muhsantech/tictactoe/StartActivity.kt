package com.muhsantech.tictactoe

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.MotionEvent
import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.muhsantech.tictactoe.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity(), View.OnTouchListener {

    private val binding by lazy {
        ActivityStartBinding.inflate(layoutInflater)

    }
    var playerOne: String? = null
    private var playerTwo: String? = null
    var islayout = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

        setContentView(binding.root)

        binding.startGameButton.setOnClickListener {
            val intent = Intent(this@StartActivity, MainActivity::class.java)
            startActivity(intent)
            //finish()
        }

        binding.playerOneBtn.setOnTouchListener(this)
        binding.playerOneBtn.setOnClickListener {
            if (TextUtils.isEmpty(binding.playerOneNameEdttxt.text.toString())) {
                Toast.makeText(this, "Enter Name", Toast.LENGTH_LONG).show()
            } else {
                islayout = false
                binding.playerOneLayout.visibility = View.GONE
                binding.playerTwoLayout.visibility = View.VISIBLE
                slideUp(binding.playerTwoLayout)
                playerOne = binding.playerOneNameEdttxt.text.toString()
            }
        }

        binding.playerTwoBtn.setOnTouchListener(this)
        binding.playerTwoBtn.setOnClickListener {
            if (TextUtils.isEmpty(binding.playerOneNameEdttxt.text.toString())) {
                Toast.makeText(this, "Enter Name", Toast.LENGTH_LONG).show()
            } else {
                playerTwo = binding.playerTwoNameEdttxt.text.toString()
                val intent = Intent(this@StartActivity, MainActivity::class.java)
                intent.putExtra("p1", playerOne)
                intent.putExtra("p2", playerTwo)
                startActivity(intent)
            }
        }

    }

    @Override
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        if (islayout) {
            if (v === binding.playerOneBtn) {
                if (event.action == MotionEvent.ACTION_DOWN) {
                    v.alpha = 0.5f
                } else {
                    v.alpha = 1f
                }
            }
        } else if (!islayout) {
            if (v === binding.playerTwoBtn) {
                if (event.action == MotionEvent.ACTION_DOWN) {
                    v.alpha = 0.5f
                } else {
                    v.alpha = 1f
                }
            }
        }
        return false
    }

    // Slide the view from below itself to the current position
    fun slideUp(view: View) {
        view.visibility = View.VISIBLE
        val animate = TranslateAnimation(
            0f,  // fromXDelta
            0f,  // toXDelta
            view.height.toFloat(),  // fromYDelta
            0f
        ) // toYDelta
        animate.duration = 500
        animate.fillAfter = true
        view.startAnimation(animate)
    }

    // Slide the view from its current position to below itself
    fun slideDown(view: View) {
        val animate = TranslateAnimation(
            0f,  // fromXDelta
            0f,  // toXDelta
            0f,  // fromYDelta
            view.height.toFloat()
        ) // toYDelta
        animate.duration = 500
        animate.fillAfter = true
        view.startAnimation(animate)
    }
}