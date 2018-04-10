package com.example.android.tictactoe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import static com.example.android.tictactoe.ThreeBoardActivity.GAME_SCORES;

public class StatsActivity extends AppCompatActivity {

    private TextView back_button, games_played, next_button;
    private TextView games_won_three, games_lost_three, games_draw_three, games_won_five, games_lost_five, games_draw_five;
    private View decorView;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        back_button = findViewById(R.id.back);
        next_button = findViewById(R.id.next);
        games_played = findViewById(R.id.games_played);
        games_won_three = findViewById(R.id.games_won_three);
        games_lost_three = findViewById(R.id.games_lost_three);
        games_draw_three = findViewById(R.id.games_draw_three);
        games_won_five = findViewById(R.id.games_won_five);
        games_lost_five = findViewById(R.id.games_lost_five);
        games_draw_five = findViewById(R.id.games_drawn_five);

        back_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent menu = new Intent (StatsActivity.this, MainActivity.class);
                startActivity(menu);
                finish();
            }
        });

        next_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent multiplayer = new Intent (StatsActivity.this, StatsMultiplayerActivity.class);
                startActivity(multiplayer);
                finish();
            }
        });

        decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        mPreferences = getSharedPreferences(GAME_SCORES, 0);
        mEditor = mPreferences.edit();

        setScoresThree();
        setScoresFive();
        setTotal();
    }

    public void resetStats(View view){
        games_played.setText(R.string.r_games_played);
        games_won_three.setText(R.string.r_games_won);
        games_lost_three.setText(R.string.r_games_lost);
        games_draw_three.setText(R.string.r_games_draw);
        games_won_five.setText(R.string.r_games_won);
        games_lost_five.setText(R.string.r_games_lost);
        games_draw_five.setText(R.string.r_games_draw);

        mEditor.putInt("TOTAL_GAMES_PLAYED", 0);
        mEditor.putInt("GAMES_PLAYED", 0);
        mEditor.putInt("GAMES_LOST_THREE", 0);
        mEditor.putInt("GAMES_WON_THREE", 0);
        mEditor.putInt("GAMES_DRAW_THREE", 0);
        mEditor.putInt("GAMES_PLAYED_FIVE", 0);
        mEditor.putInt("GAMES_LOST_FIVE", 0);
        mEditor.putInt("GAMES_WON_FIVE", 0);
        mEditor.putInt("GAMES_DRAW_FIVE", 0);

        mEditor.apply();
    }

    public void setScoresThree(){
        int gw = mPreferences.getInt("GAMES_WON_THREE", 0);
        int gl = mPreferences.getInt("GAMES_LOST_THREE", 0);
        int gd = mPreferences.getInt("GAMES_DRAW_THREE", 0);

        games_won_three.setText(getString(R.string.games_won) + " " + gw);
        games_lost_three.setText(getString(R.string.games_lost) + " " + gl);
        games_draw_three.setText(getString(R.string.games_draw) + " " + gd);
    }

    public void setScoresFive(){
        int gwf = mPreferences.getInt("GAMES_WON_FIVE", 0);
        int glf = mPreferences.getInt("GAMES_LOST_FIVE", 0);
        int gdf = mPreferences.getInt("GAMES_DRAW_FIVE", 0);

        games_won_five.setText(getString(R.string.games_won) + " " + gwf);
        games_lost_five.setText(getString(R.string.games_lost) + " " + glf);
        games_draw_five.setText(getString(R.string.games_draw) + " " + gdf);
    }

    public void setTotal(){
        int gpt = mPreferences.getInt("GAMES_PLAYED", 0);
        int gpf = mPreferences.getInt("GAMES_PLAYED_FIVE", 0);
        int gamesPlayed = (gpt + gpf);
        mEditor.putInt("TOTAL_GAMES_PLAYED", gamesPlayed);
        games_played.setText(getString(R.string.games_played) + " " + gamesPlayed);
        mEditor.apply();
    }

}
