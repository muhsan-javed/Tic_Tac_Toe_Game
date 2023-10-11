package com.muhsantech.tictactoe

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.muhsantech.tictactoe.databinding.ActivityMainBinding
import com.muhsantech.tictactoe.databinding.WinnerDialougeBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
    }

    fun btnClick(view:View) {
        var cellId = 0
        val btnSelected = view as AppCompatButton

        when(btnSelected.id){
            R.id.btn1-> cellId = 1
            R.id.btn2-> cellId = 2
            R.id.btn3-> cellId = 3
            R.id.btn4-> cellId = 4
            R.id.btn5-> cellId = 5
            R.id.btn6-> cellId = 6
            R.id.btn7-> cellId = 7
            R.id.btn8-> cellId = 8
            R.id.btn9-> cellId = 9

        }
        //Log.d("btnClick:", btnSelected.id.toString())
        //Log.d("btnClick:", cellId.toString())
        playGame(cellId,btnSelected)
    }

    var activePlayer = 1
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    fun playGame(cellId : Int, btnSelected: AppCompatButton) {

        if (activePlayer == 1){
            btnSelected.text = "X"
            btnSelected.setBackgroundResource(R.color.green)
            player1.add(cellId)
            activePlayer = 2
        }
        else {
            btnSelected.text = "0"
            btnSelected.setBackgroundResource(R.color.Yellow)
            player2.add(cellId)
            activePlayer = 1
        }

        btnSelected.isEnabled = false
        checkWinner()
    }

    fun checkWinner(){
        var winner = -1

        // row 1 winner
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)){
            winner = 1
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)){
            winner = 2
        }

        // row 2 winner
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)){
            winner = 1
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)){
            winner = 2
        }

        // row 3 winner
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)){
            winner = 1
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)){
            winner = 2
        }


        // row 1 winner vert
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)){
            winner = 1
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)){
            winner = 2
        }

        // row 2 winner vert
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)){
            winner = 1
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)){
            winner = 2
        }

        // row 3 winner vert
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)){
            winner = 1
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)){
            winner = 2
        }

        // row 3 winner 1 5 9
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)){
            winner = 1
        }
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)){
            winner = 2
        }

        // row 3 winner 3 5 7
        if (player1.contains(3) && player1.contains(5) && player1.contains(7)){
            winner = 1
        }
        if (player2.contains(3) && player2.contains(5) && player2.contains(7)){
            winner = 2
        }


        if (winner == 1)
        {

            // Inflate dialog main
            val winnerDBinding = WinnerDialougeBinding.inflate(layoutInflater)
            // Initialize dialog
            val dialog = Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            // set background transparent
            //dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            // set view
            dialog.setContentView(winnerDBinding.root)

            winnerDBinding.winnerResultTextView.text = "Player 1 win the Game"

            winnerDBinding.btnExit.setOnClickListener {
                val intent = Intent(this,StartActivity::class.java)
                startActivity(intent)
            }
            winnerDBinding.btnPlayAgain.setOnClickListener {
                val intent = Intent(this,MainActivity::class.java)
                finish()
                startActivity(intent)
            }
            dialog.show()

//
//            val dialog = Dialog(this)
//            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//            dialog.setCancelable(false)
//            dialog.setContentView(R.layout.winner_dialouge)
//            var winnerResult = dialog.findViewById<TextView>(R.id.winnerResultTextView)
           // val winnerDialougeBinding as winnerDia

        }
        else if (winner ==2 ){
            // Inflate dialog main
            val winnerDBinding = WinnerDialougeBinding.inflate(layoutInflater)
            // Initialize dialog
            val dialog = Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            // set background transparent
            dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            // set view
            dialog.setContentView(winnerDBinding.root)

            winnerDBinding.winnerResultTextView.text = "Player 2 win the Game"

            winnerDBinding.btnExit.setOnClickListener {
                val intent = Intent(this,StartActivity::class.java)
                startActivity(intent)
            }
            winnerDBinding.btnPlayAgain.setOnClickListener {
                val intent = Intent(this,MainActivity::class.java)
                finish()
                startActivity(intent)
            }
            dialog.show()
        }

        /*if (winner == 1){
            Toast.makeText(this,"PLayer 1 win the Game ",Toast.LENGTH_LONG).show()
        }else if (winner ==2 ){
            Toast.makeText(this,"PLayer 2 win the Game ",Toast.LENGTH_LONG).show()
        }*/
    }
}