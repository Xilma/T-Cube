package com.example.android.tictactoe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import static com.example.android.tictactoe.ThreeBoardHumanActivity.MULTI_GAME_SCORES;

public class StatsMultiplayerActivity extends AppCompatActivity {

    private TextView games_played, games_won_three, games_lost_three, games_draw_three, games_won_five, games_lost_five, games_draw_five;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_multiplayer);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TextView back_button = findViewById(R.id.back);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent single_stats = new Intent(StatsMultiplayerActivity.this, StatsActivity.class);
                startActivity(single_stats);
                finish();
            }
        });

        games_played = findViewById(R.id.games_played);
        games_won_three = findViewById(R.id.games_won_three);
        games_lost_three = findViewById(R.id.games_lost_three);
        games_draw_three = findViewById(R.id.games_draw_three);
        games_won_five = findViewById(R.id.games_won_five);
        games_lost_five = findViewById(R.id.games_lost_five);
        games_draw_five = findViewById(R.id.games_drawn_five);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        mPreferences = getSharedPreferences(MULTI_GAME_SCORES, 0);
        mEditor = mPreferences.edit();

        setScoresThree();
        setScoresFive();
        setTotal();
    }

    public void resetStats(View view){
        games_played.setText(R.string.r_games_played);
        games_won_three.setText(R.string.r_player_one_win);
        games_lost_three.setText(R.string.r_player_two_win);
        games_draw_three.setText(R.string.r_multi_draw);
        games_won_five.setText(R.string.r_player_one_win);
        games_lost_five.setText(R.string.r_player_two_win);
        games_draw_five.setText(R.string.r_multi_draw);

        mEditor.putInt("TOTAL_MULTI_GAMES_PLAYED", 0);
        mEditor.putInt("MULTI_GAMES_PLAYED", 0);
        mEditor.putInt("PLAYER_ONE_WON", 0);
        mEditor.putInt("PLAYER_TWO_WON", 0);
        mEditor.putInt("MULTI_GAMES_DRAW", 0);
        mEditor.putInt("MULTI_GAMES_PLAYED_FIVE", 0);
        mEditor.putInt("PLAYER_ONE_WON_FIVE", 0);
        mEditor.putInt("PLAYER_TWO_WON_FIVE", 0);
        mEditor.putInt("MULTI_GAMES_DRAW_FIVE", 0);

        mEditor.apply();
    }

    public void setScoresThree(){
        int gw = mPreferences.getInt("PLAYER_ONE_WON", 0);
        int gl = mPreferences.getInt("PLAYER_TWO_WON", 0);
        int gd = mPreferences.getInt("MULTI_GAMES_DRAW", 0);

        games_won_three.setText(getString(R.string.player_one_win) + getString(R.string.blank) + gw);
        games_lost_three.setText(getString(R.string.player_two_win) + getString(R.string.blank) + gl);
        games_draw_three.setText(getString(R.string.multi_draw) + getString(R.string.blank) + gd);
    }

    public void setScoresFive(){
        int gwf = mPreferences.getInt("PLAYER_ONE_WON_FIVE", 0);
        int glf = mPreferences.getInt("PLAYER_TWO_WON_FIVE", 0);
        int gdf = mPreferences.getInt("MULTI_GAMES_DRAW_FIVE", 0);

        games_won_five.setText(getString(R.string.player_one_win) + getString(R.string.blank) + gwf);
        games_lost_five.setText(getString(R.string.player_two_win) + getString(R.string.blank) + glf);
        games_draw_five.setText(getString(R.string.multi_draw) + getString(R.string.blank) + gdf);
    }

    public void setTotal(){
        int gpt = mPreferences.getInt("MULTI_GAMES_PLAYED", 0);
        int gpf = mPreferences.getInt("MULTI_GAMES_PLAYED_FIVE", 0);
        int gamesPlayed = (gpt + gpf);
        mEditor.putInt("TOTAL_MULTI_GAMES_PLAYED", gamesPlayed);
        games_played.setText(getString(R.string.games_played) + getString(R.string.blank) + gamesPlayed);
        mEditor.apply();
    }


}
