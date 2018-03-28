package com.example.android.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StatsActivity extends AppCompatActivity {

    private Scores scores;
    private TextView back_button, high_score, games_played, playtime;
    private TextView games_won_three, games_lost_three, games_won_five, games_lost_five;
    private Button reset_stats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        back_button = findViewById(R.id.back);
        high_score = findViewById(R.id.high_score);
        games_played = findViewById(R.id.games_played);
        playtime = findViewById(R.id.playtime);
        games_won_three = findViewById(R.id.games_won_three);
        games_lost_three = findViewById(R.id.games_lost_three);
        games_won_five = findViewById(R.id.games_won_five);
        games_lost_five = findViewById(R.id.games_lost_five);
        reset_stats = findViewById(R.id.reset_stats);

        back_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent menu = new Intent (StatsActivity.this, MainActivity.class);
                startActivity(menu);
                finish();
            }
        });

        reset_stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetStats();
            }
        });
    }

    public void resetStats(){


    }
}
