package com.example.android.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StatsActivity extends AppCompatActivity {

    private Scores scores;
    private TextView back_button, games_played;
    private TextView games_won_three, games_lost_three, games_draw_three, games_won_five, games_lost_five, games_draw_five;
    private Button reset_stats;
    private View decorView;
    private ThreeBoardActivity tba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        back_button = findViewById(R.id.back);
        games_played = findViewById(R.id.games_played);
        games_won_three = findViewById(R.id.games_won_three);
        games_lost_three = findViewById(R.id.games_lost_three);
        games_draw_three = findViewById(R.id.games_draw_three);
        games_won_five = findViewById(R.id.games_won_five);
        games_lost_five = findViewById(R.id.games_lost_five);
        games_draw_five = findViewById(R.id.games_drawn_five);
        reset_stats = findViewById(R.id.reset_stats);

        back_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent menu = new Intent (StatsActivity.this, MainActivity.class);
                startActivity(menu);
                finish();
            }
        });

        decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        setScoresThree();
    }

    public void resetStats(View view){
        games_played.setText(R.string.r_games_played);
        games_won_three.setText(R.string.r_games_won);
        games_lost_three.setText(R.string.r_games_lost);
        games_draw_three.setText(R.string.r_games_draw);
        games_won_five.setText(R.string.r_games_won);
        games_lost_five.setText(R.string.r_games_lost);
        games_draw_five.setText(R.string.r_games_draw);

    }

    public void setScoresThree(){
        tba = new ThreeBoardActivity();
        String gp = "Games Played: ";
        String gw = "Games Won: ";
        String gl = "Games Lost: ";
        String gd = "Games Draw: ";

        games_played.setText(gp);
        games_won_three.setText(gw);
        games_lost_three.setText(gl);
        games_draw_three.setText(gd);
    }

}
