package com.example.tiktakgame;

import android.view.KeyEvent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Color;
import android.util.TypedValue;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.LayoutInflater;
import android.window.OnBackInvokedDispatcher;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PlayActivity extends AppCompatActivity {
    TextView player1, player2;
    Button button1, button2, button3, button4, button5, button6, button7, button8, button9;
    String btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    String winner = "", player1Name, player2Name;
    int player1_win=0,player2_win=0,winx;

    int flag = 0, step = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_play);
        //initialization of views
        player1 = findViewById(R.id.player1TextView);
        player2 = findViewById(R.id.player2TextView);

        init();


        //set players names
        Intent forPlayers = getIntent();
        if (forPlayers != null) {
            player1Name = forPlayers.getStringExtra("player1");

            player2Name = forPlayers.getStringExtra("player2");

            // Set the player names in the TextViews
            if (player1Name != null) {
                player1.setText(player1Name);
            }
            if (player2Name != null) {
                player2.setText(player2Name);
            }
        }




    }

    private void init() {
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);


    }

    private void getButtonValue() {
        btn1 = button1.getText().toString();
        btn2 = button2.getText().toString();
        btn3 = button3.getText().toString();
        btn4 = button4.getText().toString();
        btn5 = button5.getText().toString();
        btn6 = button6.getText().toString();
        btn7 = button7.getText().toString();
        btn8 = button8.getText().toString();
        btn9 = button9.getText().toString();
    }

    private void setButtonValue() {
        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");
        button9.setText("");


    }

    private boolean isAllbuttonValueNotEmpty(){
        if (!btn1.equals("") && !btn2.equals("") && !btn3.equals("") && !btn4.equals("") && !btn5.equals("") && !btn6.equals("") && !btn7.equals("") && !btn8.equals("") && !btn9.equals("")) {
            return true;
        }


        return false;
    }


    private void delayExecution() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setButtonValue();
                flag = 0;
            }
        }, 500); // 3 seconds delay
    }


    private void message(String player,int winx,Boolean draw) {
        // Create a new dialog with the custom layout
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.win_message_dialog, null);
        dialogView.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_dialog_background));
        dialogBuilder.setView(dialogView);
        if(draw==true){
            TextView result=dialogView.findViewById(R.id.result);
            result.setText("DRAW");
        }
        else{
            TextView win_times=dialogView.findViewById((R.id.win_times));
            win_times.setText("Has won "+winx+" times");
        }

        // Get the TextView from the custom layout
        TextView winner_name = dialogView.findViewById(R.id.winner_name);
        winner_name.setText(player);

        Button play_again = dialogView.findViewById(R.id.play_again_button);
        Button end_game = dialogView.findViewById(R.id.exit_button);

        // Create the AlertDialog and show it
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.setCanceledOnTouchOutside(false);


        // Set click listeners or perform any other operations you need on buttons
        play_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                alertDialog.dismiss(); // Close dialog after button click
            }
        });

        end_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();

            }
        });


        alertDialog.show();

    }

    public void play(View button) {
        Button clickedButton = (Button) button;
        step++;
        if (clickedButton.getText().toString().equals("")) {
            if (flag == 0) {
                clickedButton.setTextColor(Color.RED);
                clickedButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 44);
                clickedButton.setText("X");
                flag = 1;
            } else {
                clickedButton.setTextColor(Color.GREEN);
                clickedButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 44);
                clickedButton.setText("O");
                flag = 0;
            }

            getButtonValue();
            String winner_times="";
            //winning conditions
            if (btn1.equals(btn2) && btn2.equals(btn3) && !btn1.equals("")) {
                if (btn1.equals("X")) {
                    winx = ++player1_win;
                    winner = player1Name;
                } else if (btn1.equals("O")) {
                    winx = ++player2_win;
                    winner = player2Name;
                }

                message(winner, winx, false);
                delayExecution();

            } else if (btn4.equals(btn5) && btn5.equals(btn6) && !btn4.equals("")) {
                if (btn4.equals("X")) {
                    winx = ++player1_win;
                    winner = player1Name;
                } else if (btn4.equals("O")) {
                    winx = ++player2_win;
                    winner = player2Name;
                }

                message(winner, winx, false);
                delayExecution();

            } else if (btn7.equals(btn8) && btn8.equals(btn9) && !btn7.equals("")) {
                if (btn7.equals("X")) {
                    winx = ++player1_win;
                    winner = player1Name;
                } else if (btn7.equals("O")) {
                    winx = ++player2_win;
                    winner = player2Name;
                }

                message(winner, winx, false);
                delayExecution();

            } else if (btn1.equals(btn4) && btn4.equals(btn7) && !btn1.equals("")) {
                if (btn1.equals("X")) {
                    winx = ++player1_win;
                    winner = player1Name;
                } else if (btn1.equals("O")) {
                    winx = ++player2_win;
                    winner = player2Name;
                }

                message(winner, winx, false);
                delayExecution();

            } else if (btn2.equals(btn5) && btn5.equals(btn8) && !btn2.equals("")) {
                if (btn2.equals("X")) {
                    winx = ++player1_win;
                    winner = player1Name;
                } else if (btn2.equals("O")) {
                    winx = ++player2_win;
                    winner = player2Name;
                }

                message(winner, winx, false);
                delayExecution();

            } else if (btn3.equals(btn6) && btn6.equals(btn9) && !btn3.equals("")) {
                if (btn3.equals("X")) {
                    winx = ++player1_win;
                    winner = player1Name;
                } else if (btn3.equals("O")) {
                    winx = ++player2_win;
                    winner = player2Name;
                }

                message(winner, winx, false);
                delayExecution();

            } else if (btn1.equals(btn5) && btn5.equals(btn9) && !btn1.equals("")) {
                if (btn1.equals("X")) {
                    winx = ++player1_win;
                    winner = player1Name;
                } else if (btn1.equals("O")) {
                    winx = ++player2_win;
                    winner = player2Name;
                }

                message(winner, winx, false);
                delayExecution();

            } else if (btn3.equals(btn5) && btn5.equals(btn7) && !btn3.equals("")) {
                if (btn3.equals("X")) {
                    winx = ++player1_win;
                    winner = player1Name;
                } else if (btn3.equals("O")) {
                    winx = ++player2_win;
                    winner = player2Name;
                }

                message(winner, winx, false);
                delayExecution();
            }

            else{
                if(isAllbuttonValueNotEmpty()==true) {
                    winner = "No one is the winner";

                    message(winner, 0,true);
                    delayExecution();
                }
            }
        }


    }


}


