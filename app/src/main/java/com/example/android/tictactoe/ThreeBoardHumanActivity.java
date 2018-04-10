package com.example.android.tictactoe;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ThreeBoardHumanActivity extends AppCompatActivity{

    private boolean playerTurn = true;
    private Button [][] b;
    private int [][] c;
    private boolean playerToken;
    private int gamesPlayed, gamesWon, gamesLost, gamesDraw = 0;
    private int i, j = 0;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    public static final String MULTI_GAME_SCORES = "TicTacToe_MultiScores";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_board_human);

        TextView back = findViewById(R.id.back);
        View decorView = getWindow().getDecorView();

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent menu = new Intent(ThreeBoardHumanActivity.this, MainActivity.class);
                startActivity(menu);
                finish();
            }
        });

        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        mPreferences = getSharedPreferences(MULTI_GAME_SCORES, 0);
        mEditor = mPreferences.edit();

        chooseToken();
    }

    public void chooseToken(){
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
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
        playerTurn = true;
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
            if (b[x][y].isEnabled() && playerTurn) {
                b[x][y].setEnabled(false);
                if(!playerToken){
                    b[x][y].setTextColor(Color.parseColor("#673ab7"));
                    b[x][y].setText(R.string.letter_o);}
                if(playerToken){
                    b[x][y].setTextColor(Color.parseColor("#673ab7"));
                    b[x][y].setText(R.string.letter_x);
                }

                c[x][y] = 0;
                playerTurn = false;
                if(!checkBoard())
                    Toast.makeText(getApplicationContext(), "Player 2 turn", Toast.LENGTH_SHORT).show();


            } else if(!playerTurn && !checkBoard()){
                b[x][y].setEnabled(false);
                if(!playerToken){
                    b[x][y].setTextColor(Color.parseColor("#ffffff"));
                    b[x][y].setText(R.string.letter_x);}
                if(playerToken){
                    b[x][y].setTextColor(Color.parseColor("#ffffff"));
                    b[x][y].setText(R.string.letter_o);
                }
                c[x][y] = 1;

                if(!checkBoard()){
                    playerTurn = true;
                    Toast.makeText(getApplicationContext(), "Player 1 turn", Toast.LENGTH_SHORT).show();
                }
                else {
                       playerTurn = false;
                }

            }else {
                checkBoard();
            }
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
            Toast.makeText(getApplicationContext(), "Game over. Player 1 wins!", Toast.LENGTH_LONG).show();
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
            Toast.makeText(getApplicationContext(), "Game over. Player 2 wins!", Toast.LENGTH_LONG).show();
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
        if(mPreferences.contains("PLAYER_ONE_WON")) {
            int gw = mPreferences.getInt("PLAYER_ONE_WON", 0);
            gamesWon = gw + 1;
            mEditor.putInt("PLAYER_ONE_WON", gamesWon);
            mEditor.apply();
        }
    }

    public void gameLost(){
       if(mPreferences.contains("PLAYER_TWO_WON")) {
            int gl = mPreferences.getInt("PLAYER_TWO_WON", 0);
            gamesLost = gl + 1;
            mEditor.putInt("PLAYER_TWO_WON", gamesLost);
            mEditor.apply();
        }
    }

    public void gameDraw(){
        if(mPreferences.contains("MULTI_GAMES_DRAW")) {
            int gd = mPreferences.getInt("MULTI_GAMES_DRAW", 0);
            gamesDraw = gd + 1;
            mEditor.putInt("MULTI_GAMES_DRAW", gamesDraw);
            mEditor.apply();
        }
    }

    public void gamePlayed(){
        if(mPreferences.contains("MULTI_GAMES_PLAYED")) {
            int gw = mPreferences.getInt("PLAYER_ONE_WON", 0);
            int gl = mPreferences.getInt("PLAYER_TWO_WON", 0);
            int gd = mPreferences.getInt("MULTI_GAMES_DRAW", 0);
            gamesPlayed = (gw + gl + gd);
            mEditor.putInt("MULTI_GAMES_PLAYED", gamesPlayed);
            mEditor.apply();
        }
    }
}




