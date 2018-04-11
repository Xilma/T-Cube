package com.example.android.tictactoe;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ThreeBoardActivity extends AppCompatActivity{

    private Button [][] b;
    private int [][] c;
    private boolean playerToken;
    private int gamesPlayed, gamesWon, gamesLost, gamesDraw = 0;
    private int i, j = 0;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    public static final String GAME_SCORES = "TicTacToe_Scores";
    AI ai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_board);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TextView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent menu = new Intent(ThreeBoardActivity.this, MainActivity.class);
                startActivity(menu);
                finish();
            }
        });

        mPreferences = getSharedPreferences(GAME_SCORES, 0);
        mEditor = mPreferences.edit();

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        chooseToken();
    }

    public void chooseToken(){
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        @SuppressLint("InflateParams")
        View mView = getLayoutInflater().inflate(R.layout.dialog_choose_token, null);
        Button letterX = mView.findViewById(R.id.lx);
        Button letterO = mView.findViewById(R.id.lo);
        letterX.setEnabled(true);
        letterO.setEnabled(true);

        letterX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerToken = true;
                Toast.makeText(getApplicationContext(), "Click outside this dialog to play.", Toast.LENGTH_LONG).show();
                setBoard();
            }
        });

        letterO.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  playerToken = false;
                  Toast.makeText(getApplicationContext(), "Click outside this dialog to play.", Toast.LENGTH_LONG).show();
                  setBoard();
              }
        });

        alertDialogBuilder.setView(mView);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    //Reset the board
    public void resetGame(View view){
        for (int i = 0; i < 3; i++){
            for(int j=0; j<3; j++){
                String buttonID = "r" + i + "c" + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                b[i][j] = findViewById(resID);
                b[i][j].setText("");
            }
        }
        setBoard();
    }

    // Set up the game board.
    private void setBoard() {
        ai = new AI();
        b = new Button[4][4];
        c = new int[4][4];


        b[1][3] = findViewById(R.id.r0c0);
        b[1][2] = findViewById(R.id.r0c1);
        b[1][1] = findViewById(R.id.r0c2);


        b[2][3] = findViewById(R.id.r1c0);
        b[2][2] = findViewById(R.id.r1c1);
        b[2][1] = findViewById(R.id.r1c2);


        b[3][3] = findViewById(R.id.r2c0);
        b[3][2] = findViewById(R.id.r2c1);
        b[3][1] = findViewById(R.id.r2c2);

        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++)
                c[i][j] = 2;
        }

        // add the click listeners for each button
        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++) {
                b[i][j].setOnClickListener(new MyClickListener(i, j));
                if(!b[i][j].isEnabled()) {
                    b[i][j].setText(" ");
                    b[i][j].setEnabled(true);
                }
            }
        }

        Toast.makeText(getApplicationContext(), "Click a button to start.", Toast.LENGTH_LONG).show();

    }


    class MyClickListener implements View.OnClickListener {
        int x;
        int y;


        private MyClickListener(int x, int y) {
            this.x = x;
            this.y = y;
        }


        public void onClick(View view) {
            if (b[x][y].isEnabled()) {
                b[x][y].setEnabled(false);
                if(!playerToken){
                    b[x][y].setTextColor(Color.parseColor("#673ab7"));
                    b[x][y].setText(R.string.letter_o);}
                if(playerToken){
                    b[x][y].setTextColor(Color.parseColor("#673ab7"));
                    b[x][y].setText(R.string.letter_x);
                }

                c[x][y] = 0;

                if (!checkBoard()) {
                    ai.takeTurn();
                }
            }
        }
    }


    private class AI {
        public void takeTurn() {
            if(c[1][1]==2 &&
                    ((c[1][2]==0 && c[1][3]==0) ||
                            (c[2][2]==0 && c[3][3]==0) ||
                            (c[2][1]==0 && c[3][1]==0))) {
                markSquare(1,1);
            } else if (c[1][2]==2 &&
                    ((c[2][2]==0 && c[3][2]==0) ||
                            (c[1][1]==0 && c[1][3]==0))) {
                markSquare(1,2);
            } else if(c[1][3]==2 &&
                    ((c[1][1]==0 && c[1][2]==0) ||
                            (c[3][1]==0 && c[2][2]==0) ||
                            (c[2][3]==0 && c[3][3]==0))) {
                markSquare(1,3);
            } else if(c[2][1]==2 &&
                    ((c[2][2]==0 && c[2][3]==0) ||
                            (c[1][1]==0 && c[3][1]==0))){
                markSquare(2,1);
            } else if(c[2][2]==2 &&
                    ((c[1][1]==0 && c[3][3]==0) ||
                            (c[1][2]==0 && c[3][2]==0) ||
                            (c[3][1]==0 && c[1][3]==0) ||
                            (c[2][1]==0 && c[2][3]==0))) {
                markSquare(2,2);
            } else if(c[2][3]==2 &&
                    ((c[2][1]==0 && c[2][2]==0) ||
                            (c[1][3]==0 && c[3][3]==0))) {
                markSquare(2,3);
            } else if(c[3][1]==2 &&
                    ((c[1][1]==0 && c[2][1]==0) ||
                            (c[3][2]==0 && c[3][3]==0) ||
                            (c[2][2]==0 && c[1][3]==0))){
                markSquare(3,1);
            } else if(c[3][2]==2 &&
                    ((c[1][2]==0 && c[2][2]==0) ||
                            (c[3][1]==0 && c[3][3]==0))) {
                markSquare(3,2);
            }else if( c[3][3]==2 &&
                    ((c[1][1]==0 && c[2][2]==0) ||
                            (c[1][3]==0 && c[2][3]==0) ||
                            (c[3][1]==0 && c[3][2]==0))) {
                markSquare(3,3);
            } else {
                Random rand = new Random();

                int a = rand.nextInt(4);
                int b = rand.nextInt(4);
                while(a==0 || b==0 || c[a][b]!=2) {
                    a = rand.nextInt(4);
                    b = rand.nextInt(4);
                }
                markSquare(a,b);
            }
        }


        private void markSquare(int x, int y) {
            b[x][y].setEnabled(false);
            if(!playerToken){
                b[x][y].setTextColor(Color.parseColor("#ffffff"));
                b[x][y].setText(R.string.letter_x);}
            if(playerToken){
                b[x][y].setTextColor(Color.parseColor("#ffffff"));
                b[x][y].setText(R.string.letter_o);
            }
            c[x][y] = 1;
            checkBoard();
        }
    }


    // check the board to see if someone has won
    private boolean checkBoard() {
        boolean gameOver = false;
        if ((c[1][1] == 0 && c[2][2] == 0 && c[3][3] == 0)
                || (c[1][3] == 0 && c[2][2] == 0 && c[3][1] == 0)
                || (c[1][2] == 0 && c[2][2] == 0 && c[3][2] == 0)
                || (c[1][3] == 0 && c[2][3] == 0 && c[3][3] == 0)
                || (c[1][1] == 0 && c[1][2] == 0 && c[1][3] == 0)
                || (c[2][1] == 0 && c[2][2] == 0 && c[2][3] == 0)
                || (c[3][1] == 0 && c[3][2] == 0 && c[3][3] == 0)
                || (c[1][1] == 0 && c[2][1] == 0 && c[3][1] == 0)) {
            Toast.makeText(getApplicationContext(), "Game over. You win!", Toast.LENGTH_LONG).show();
            gameWin();
            gamePlayed();
            gameOver = true;
            disableButtons();
        } else if ((c[1][1] == 1 && c[2][2] == 1 && c[3][3] == 1)
                || (c[1][3] == 1 && c[2][2] == 1 && c[3][1] == 1)
                || (c[1][2] == 1 && c[2][2] == 1 && c[3][2] == 1)
                || (c[1][3] == 1 && c[2][3] == 1 && c[3][3] == 1)
                || (c[1][1] == 1 && c[1][2] == 1 && c[1][3] == 1)
                || (c[2][1] == 1 && c[2][2] == 1 && c[2][3] == 1)
                || (c[3][1] == 1 && c[3][2] == 1 && c[3][3] == 1)
                || (c[1][1] == 1 && c[2][1] == 1 && c[3][1] == 1)) {
            Toast.makeText(getApplicationContext(), "Game over. You lost!", Toast.LENGTH_LONG).show();
            gameLost();
            gamePlayed();
            gameOver = true;
            disableButtons();
        } else {
            boolean empty = false;
            for(i=1; i<=3; i++) {
                for(j=1; j<=3; j++) {
                    if(c[i][j]==2) {
                        empty = true;
                        break;
                    }
                }
            }
            if(!empty) {
                gameOver = true;
                Toast.makeText(getApplicationContext(), "Game over. It's a draw!", Toast.LENGTH_LONG).show();
                gameDraw();
                gamePlayed();
                disableButtons();
            }
        }

        return gameOver;
    }

    public void disableButtons(){
        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++) {
                b[i][j].setEnabled(false);
            }
        }
    }

    public void gameWin(){
        if(mPreferences.contains("GAMES_WON_THREE")) {
            int gw = mPreferences.getInt("GAMES_WON_THREE", 0);
            gamesWon = gw + 1;
            mEditor.putInt("GAMES_WON_THREE", gamesWon);
            mEditor.apply();
        }
    }

    public void gameLost(){
        if(mPreferences.contains("GAMES_LOST_THREE")) {
            int gl = mPreferences.getInt("GAMES_LOST_THREE", 0);
            gamesLost = gl + 1;
            mEditor.putInt("GAMES_LOST_THREE", gamesLost);
            mEditor.apply();
        }
    }

    public void gameDraw(){
        if(mPreferences.contains("GAMES_DRAW_THREE")) {
            int gd = mPreferences.getInt("GAMES_DRAW_THREE", 0);
            gamesDraw = gd + 1;
            mEditor.putInt("GAMES_DRAW_THREE", gamesDraw);
            mEditor.apply();
        }
    }

    public void gamePlayed(){
        if(mPreferences.contains("GAMES_PLAYED")) {
            int gw = mPreferences.getInt("GAMES_WON_THREE", 0);
            int gl = mPreferences.getInt("GAMES_LOST_THREE", 0);
            int gd = mPreferences.getInt("GAMES_DRAW_THREE", 0);
            gamesPlayed = (gw + gl + gd);
            mEditor.putInt("GAMES_PLAYED", gamesPlayed);
            mEditor.apply();
        }
    }

}




