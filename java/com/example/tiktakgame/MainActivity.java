package com.example.tiktakgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.graphics.Color;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button play;
    EditText player1_edt,player2_edt;
    String player1,player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        play = findViewById(R.id.playButton);
        player1_edt=findViewById(R.id.player1Name);
        player2_edt=findViewById(R.id.player2Name);


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String player1 = player1_edt.getText().toString();
                String player2 = player2_edt.getText().toString();

                if (player1.equals("")) {
                    player1_edt.setHintTextColor(Color.RED);
                    player1_edt.setHint("Please enter your name");
                } else if (player2.equals("")) {
                    player2_edt.setHintTextColor(Color.RED);
                    player2_edt.setHint("Please enter your friend's name");
                } else if (player1.equals(player2)) {

                    player2_edt.setHintTextColor(Color.RED);
                    player2_edt.setText("");
                    player2_edt.setHint("Name should not be same.change the name or add number at the end\nexample: name1 ");
                }else if(isContainAnyNumber(player1)){
                    player1_edt.setText("");
                    player1_edt.setHintTextColor(Color.RED);
                    player1_edt.setHint(" is " + player1 + " your actual name registered in your adhar card? \uD83D\uDE00");

                }
                else if(isContainAnyNumber(player2)){
                    player2_edt.setText("");
                    player2_edt.setHintTextColor(Color.RED);
                    player2_edt.setHint(" is " + player2 + " your actual name registered in your adhar card? \uD83D\uDE00");

                }
                else {
                    Intent next_activity = new Intent(MainActivity.this, PlayActivity.class);
                    next_activity.putExtra("player1", player1);
                    next_activity.putExtra("player2", player2);
                    startActivity(next_activity);
                }
            }
        });


    }
    private  boolean isContainAnyNumber(String name){
        String check_string=name.substring(0,name.length()-1);
        for(int check_number=0;check_number<=9;check_number++){
            if (check_string.contains(String.valueOf(check_number))){
                return true;
            }
        }
        return false;
    }
}