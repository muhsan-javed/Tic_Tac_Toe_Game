package com.muhsantech.tictactoe

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.muhsantech.tictactoe.databinding.ActivityMainBinding
import com.muhsantech.tictactoe.databinding.WinnerDialougeBinding
import java.util.Random


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.resetBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intent)
        }
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
    var btnUsed = 0
    fun playGame(cellId : Int, btnSelected: AppCompatButton) {

        if (activePlayer == 1){
            btnSelected.text = "X"
            btnSelected.setBackgroundResource(R.drawable.playeronebox)
            player1.add(cellId)
            activePlayer = 2
            autoPlay()
        }
        else {
            btnSelected.text = "0"
            btnSelected.setBackgroundResource(R.drawable.playertwobox)
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


        // Column 1 winner vert
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)){
            winner = 1
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)){
            winner = 2
        }

        // Column 2 winner vert
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)){
            winner = 1
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)){
            winner = 2
        }

        // Column 3 winner vert
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)){
            winner = 1
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)){
            winner = 2
        }

        // \  winner 1 5 9
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)){
            winner = 1
        }
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)){
            winner = 2
        }

        // /  winner 3 5 7
        if (player1.contains(3) && player1.contains(5) && player1.contains(7)){
            winner = 1
        }
        if (player2.contains(3) && player2.contains(5) && player2.contains(7)){
            winner = 2
        }


        if (winner != -1) {
            // Winner
            if (winner == 1)
            {
                val winnerDBinding = WinnerDialougeBinding.inflate(layoutInflater)
                val dialog = Dialog(this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialog.setCancelable(false)
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
            }
            // Loser!
            else {

                val winnerDBinding = WinnerDialougeBinding.inflate(layoutInflater)
                val dialog = Dialog(this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialog.setCancelable(false)
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

            // 2 Player Not Winner
            if (winner == 1 || winner == 2){
                disableBtn()
            }

        }
        btnUsed = 0
        for (cellId in 0..9){
            if(player1.contains(cellId) || player2.contains(cellId)){
                btnUsed++
            }
        }
        if (btnUsed >= 8 && winner == -1){

            disableBtn()
            val winnerDBinding = WinnerDialougeBinding.inflate(layoutInflater)
            val dialog = Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(winnerDBinding.root)
            winnerDBinding.winnerResultTextView.text = "No one won the Game"
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

    private fun disableBtn(){
        binding.btn1.isEnabled = false
        binding.btn2.isEnabled = false
        binding.btn3.isEnabled = false
        binding.btn4.isEnabled = false
        binding.btn5.isEnabled = false
        binding.btn6.isEnabled = false
        binding.btn7.isEnabled = false
        binding.btn8.isEnabled = false
        binding.btn9.isEnabled = false
    }

    private fun autoPlay(){
        var emptyCells = ArrayList<Int>()
        for(cellID in 1..9){
            if(!((player1.contains(cellID)) || (player2.contains(cellID)))){
                emptyCells.add(cellID)
            }
        }
        val random = Random()
        val randIndex = random.nextInt(emptyCells.size-0)+0
        val cellID = emptyCells[randIndex]

        if(!(emptyCells.isEmpty())){
            val btnSelected: AppCompatButton
            when(cellID){
                1->btnSelected = binding.btn1
                2->btnSelected = binding.btn2
                3->btnSelected = binding.btn3
                4->btnSelected = binding.btn4
                5->btnSelected = binding.btn5
                6->btnSelected = binding.btn6
                7->btnSelected = binding.btn7
                8->btnSelected = binding.btn8
                9->btnSelected = binding.btn9
                else -> btnSelected = binding.btn1
            }
            playGame(cellID,btnSelected)
        }
    }
}