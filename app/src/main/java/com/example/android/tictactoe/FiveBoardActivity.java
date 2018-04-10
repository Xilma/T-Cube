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

import java.util.Random;

import static com.example.android.tictactoe.ThreeBoardActivity.GAME_SCORES;

public class FiveBoardActivity extends AppCompatActivity {

    private TextView back;
    private View decorView;
    private Button[][] b;
    private int [][] c;
    private boolean playerToken;
    private int gamesPlayed, gamesWon, gamesLost, gamesDraw = 0;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private int i, j = 0;
    AI ai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five_board);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent menu = new Intent(FiveBoardActivity.this, MainActivity.class);
                startActivity(menu);
                finish();
            }
        });

        decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        mPreferences = getSharedPreferences(GAME_SCORES, 0);
        mEditor = mPreferences.edit();

        chooseToken();
    }
    //Reset the board
    public void resetGameFive(View view){
        for (int i = 1; i < 6; i++){
            for(int j = 1; j < 6; j++){
                String buttonID = "r" + i + "c" + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                b[i][j] = findViewById(resID);
                b[i][j].setText("");
            }
        }
        setBoard();
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

    // Set up the game board.
    private void setBoard() {
        ai = new AI();
        b = new Button[6][6];
        c = new int[6][6];

        //Row 1
        b[1][1] = (Button) findViewById(R.id.r1c1);
        b[1][2] = (Button) findViewById(R.id.r1c2);
        b[1][3] = (Button) findViewById(R.id.r1c3);
        b[1][4] = (Button) findViewById(R.id.r1c4);
        b[1][5] = (Button) findViewById(R.id.r1c5);
        //Row 2
        b[2][1] = (Button) findViewById(R.id.r2c1);
        b[2][2] = (Button) findViewById(R.id.r2c2);
        b[2][3] = (Button) findViewById(R.id.r2c3);
        b[2][4] = (Button) findViewById(R.id.r2c4);
        b[2][5] = (Button) findViewById(R.id.r2c5);
        //Row 3
        b[3][1] = (Button) findViewById(R.id.r3c1);
        b[3][2] = (Button) findViewById(R.id.r3c2);
        b[3][3] = (Button) findViewById(R.id.r3c3);
        b[3][4] = (Button) findViewById(R.id.r3c4);
        b[3][5] = (Button) findViewById(R.id.r3c5);
        //Row 4
        b[4][1] = (Button) findViewById(R.id.r4c1);
        b[4][2] = (Button) findViewById(R.id.r4c2);
        b[4][3] = (Button) findViewById(R.id.r4c3);
        b[4][4] = (Button) findViewById(R.id.r4c4);
        b[4][5] = (Button) findViewById(R.id.r4c5);
        //Row 5
        b[5][1] = (Button) findViewById(R.id.r5c1);
        b[5][2] = (Button) findViewById(R.id.r5c2);
        b[5][3] = (Button) findViewById(R.id.r5c3);
        b[5][4] = (Button) findViewById(R.id.r5c4);
        b[5][5] = (Button) findViewById(R.id.r5c5);

        for (i = 1; i < 6; i++) {
            for (j = 1; j < 6; j++)
                c[i][j] = 2;
        }

        // add the click listeners for each button
        for (i = 1; i < 6; i++) {
            for (j = 1; j < 6; j++) {
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


        public MyClickListener(int x, int y) {
            this.x = x;
            this.y = y;
        }


        public void onClick(View view) {
            if (b[x][y].isEnabled()) {
                b[x][y].setEnabled(false);
                if(playerToken == false) {
                    b[x][y].setTextColor(Color.parseColor("#673ab7"));
                    b[x][y].setText(R.string.letter_o);
                }
                if(playerToken == true){
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
            if(c[1][1]==2 && ( (c[1][2]==0 && c[1][3]==0 && c[1][4]==0 && c[1][5]==0) ||
                            (c[2][2]==0 && c[3][3]==0 && c[4][4]==0 && c[5][5]==0) ||
                            (c[2][1]==0 && c[3][1]==0 && c[4][1]==0 && c[5][1]==0)
            )) {
                markSquare(1,1);
            } else if (c[1][2]==2 && ( (c[2][2]==0 && c[3][2]==0 && c[4][2]==0 && c[5][2]==0) ||
                            (c[1][1]==0 && c[1][3]==0 && c[1][4]==0 && c[1][5]==0)
            )) {
                markSquare(1,2);
            } else if(c[1][3]==2 && ( (c[1][1]==0 && c[1][2]==0 && c[1][4]==0 && c[1][5]==0) ||
                            (c[2][3]==0 && c[3][3]==0 && c[4][3]==0 && c[5][3]==0)
            )) {
                markSquare(1,3);
            } else if(c[1][4]==2 && ( (c[1][1]==0 && c[1][2]==0 && c[1][3]==0 && c[1][5]==0) ||
                            (c[2][4]==0 && c[3][4]==0 && c[4][4]==0 && c[5][4]==0)
            )) {
                markSquare(1,4);
            } else if(c[1][5]==2 && ( (c[1][1]==0 && c[1][2]==0 && c[1][3]==0 && c[1][4]==0) ||
                            (c[2][5]==0 && c[3][5]==0 && c[4][5]==0 && c[5][5]==0) ||
                            (c[2][4]==0 && c[3][3]==0 && c[4][2]==0 && c[5][1]==0)
            )) {
                markSquare(1,5);
            } else if(c[2][1]==2 && ( (c[2][2]==0 && c[2][3]==0 && c[2][4]==0 && c[2][5]==0) ||
                        (c[1][1]==0 && c[3][1]==0 && c[4][1]==0 && c[5][1]==0)
            )) {
                markSquare(2,1);
            }else if(c[2][2]==2 &&  ( (c[2][1]==0 && c[2][3]==0 && c[2][4]==0 && c[2][5]==0) ||
                        (c[1][2]==0 && c[3][2]==0 && c[4][2]==0 && c[5][2]==0)
            )) {
                markSquare(2,2);
            }else if(c[2][3]==2 &&  ( (c[2][1]==0 && c[2][2]==0 && c[2][4]==0 && c[2][5]==0) ||
                        (c[1][3]==0 && c[3][3]==0 && c[4][3]==0 && c[5][3]==0)
            )) {
                markSquare(2,3);
            }else if(c[2][4]==2 && ( (c[2][1]==0 && c[2][2]==0 && c[2][3]==0 && c[2][5]==0) ||
                        (c[1][4]==0 && c[3][4]==0 && c[4][4]==0 && c[5][4]==0)
            )) {
                markSquare(2,4);
            }else if(c[2][5]==2 && ( (c[2][2]==0 && c[2][3]==0 && c[2][4]==0 && c[2][1]==0) ||
                        (c[1][5]==0 && c[3][5]==0 && c[4][5]==0 && c[5][5]==0)
            )){
                markSquare(2,5);
            }else if(c[3][1]==2 && ( (c[3][2]==0 && c[3][3]==0 && c[3][4]==0 && c[3][5]==0) ||
                        (c[1][1]==0 && c[2][1]==0 && c[4][1]==0 && c[5][1]==0)
            )) {
                markSquare(3,1);
            }else if(c[3][2]==2 && ( (c[3][1]==0 && c[3][3]==0 && c[3][4]==0 && c[3][5]==0) ||
                        (c[1][2]==0 && c[2][2]==0 && c[4][2]==0 && c[5][2]==0)
            )) {
                markSquare(3,2);
            }else if(c[3][3]==2 && ( (c[3][1]==0 && c[3][2]==0 && c[3][4]==0 && c[3][5]==0) ||
                        (c[1][3]==0 && c[2][3]==0 && c[4][3]==0 && c[5][3]==0)
            )) {
                markSquare(3,3);
            }else if(c[3][4]==2 && ( (c[3][1]==0 && c[3][2]==0 && c[3][3]==0 && c[3][5]==0) ||
                        (c[1][4]==0 && c[2][4]==0 && c[4][4]==0 && c[5][4]==0)
            )) {
                markSquare(3,4);
            }else if(c[3][5]==2 && ( (c[3][1]==0 && c[3][2]==0 && c[3][3]==0 && c[3][4]==0) ||
                        (c[1][5]==0 && c[2][5]==0 && c[4][5]==0 && c[5][5]==0)
            )){
                markSquare(3,5);
            }else if(c[4][1]==2 && ( (c[4][2]==0 && c[4][3]==0 && c[4][4]==0 && c[4][5]==0) ||
                        (c[1][1]==0 && c[2][1]==0 && c[3][1]==0 && c[5][1]==0)
            )) {
                markSquare(4,1);
            }else if(c[4][2]==2 && ( (c[4][1]==0 && c[4][3]==0 && c[4][4]==0 && c[4][5]==0) ||
                        (c[1][2]==0 && c[2][2]==0 && c[3][2]==0 && c[5][2]==0)
            )) {
                markSquare(4,2);
            }else if(c[4][3]==2 && ( (c[4][1]==0 && c[4][2]==0 && c[4][4]==0 && c[4][5]==0) ||
                        (c[1][3]==0 && c[2][3]==0 && c[3][3]==0 && c[5][3]==0)
            )) {
                markSquare(4,3);
            }else if(c[4][4]==2 && ( (c[4][1]==0 && c[4][2]==0 && c[4][3]==0 && c[4][5]==0) ||
                        (c[1][4]==0 && c[2][4]==0 && c[3][4]==0 && c[5][4]==0)
            )) {
                markSquare(4,4);
            }else if(c[4][5]==2 && ( (c[4][1]==0 && c[4][2]==0 && c[4][3]==0 && c[4][4]==0) ||
                        (c[1][5]==0 && c[2][5]==0 && c[3][5]==0 && c[5][5]==0)
            )){
                markSquare(4,5);
            }else if(c[5][1]==2 && ( (c[5][2]==0 && c[5][3]==0 && c[5][4]==0 && c[5][5]==0) ||
                            (c[4][2]==0 && c[3][3]==0 && c[2][4]==0 && c[1][5]==0) ||
                            (c[4][1]==0 && c[3][1]==0 && c[2][1]==0 && c[1][1]==0)
            )) {
                markSquare(5,1);
            }else if(c[5][2]==2 && ( (c[5][1]==0 && c[5][3]==0 && c[5][4]==0 && c[5][5]==0) ||
                    (c[1][2]==0 && c[2][2]==0 && c[3][2]==0 && c[4][2]==0)
            )) {
                markSquare(5,2);
            }else if(c[5][3]==2 && ( (c[5][1]==0 && c[5][2]==0 && c[5][4]==0 && c[5][5]==0) ||
                    (c[1][3]==0 && c[2][3]==0 && c[3][3]==0 && c[4][3]==0)
            )) {
                markSquare(5,3);
            }else if(c[5][4]==2 && ( (c[5][1]==0 && c[5][2]==0 && c[5][3]==0 && c[5][5]==0) ||
                    (c[1][4]==0 && c[2][4]==0 && c[3][4]==0 && c[4][4]==0)
            )) {
                markSquare(5,4);
            }else if(c[5][5]==2 && ( (c[5][1]==0 && c[5][2]==0 && c[5][3]==0 && c[5][4]==0) ||
                            (c[4][4]==0 && c[3][3]==0 && c[2][2]==0 && c[1][1]==0) ||
                            (c[4][5]==0 && c[3][5]==0 && c[2][5]==0 && c[1][5]==0)
            )) {
                markSquare(5,5);
            } else {
                Random rand = new Random();

                int a = rand.nextInt(6);
                int b = rand.nextInt(6);
                while(a==0 || b==0 || c[a][b]!=2) {
                    a = rand.nextInt(6);
                    b = rand.nextInt(6);
                }
                markSquare(a,b);
            }
        }


        private void markSquare(int x, int y) {
            b[x][y].setEnabled(false);
            if(playerToken == false){
                b[x][y].setTextColor(Color.parseColor("#ffffff"));
                b[x][y].setText(R.string.letter_x);}
            if(playerToken == true){
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
        if ((c[1][1] == 0 && c[2][2] == 0 && c[3][3] == 0 && c[4][4] == 0 && c[5][5] == 0)
                || (c[1][5] == 0 && c[2][4] == 0 && c[3][3] == 0 && c[4][2] == 0 && c[5][1] == 0)
                || (c[1][1] == 0 && c[1][2] == 0 && c[1][3] == 0 && c[1][4] == 0 && c[1][5] == 0)
                || (c[2][1] == 0 && c[2][2] == 0 && c[2][3] == 0 && c[2][4] == 0 && c[2][5] == 0)
                || (c[3][1] == 0 && c[3][2] == 0 && c[3][3] == 0 && c[3][4] == 0 && c[3][5] == 0)
                || (c[4][1] == 0 && c[4][2] == 0 && c[4][3] == 0 && c[4][4] == 0 && c[4][5] == 0)
                || (c[5][1] == 0 && c[5][2] == 0 && c[5][3] == 0 && c[5][4] == 0 && c[5][5] == 0)
                || (c[1][1] == 0 && c[2][1] == 0 && c[3][1] == 0 && c[4][1] == 0 && c[5][1] == 0)
                || (c[1][2] == 0 && c[2][2] == 0 && c[3][2] == 0 && c[4][2] == 0 && c[5][2] == 0)
                || (c[1][3] == 0 && c[2][3] == 0 && c[3][3] == 0 && c[4][3] == 0 && c[5][3] == 0)
                || (c[1][4] == 0 && c[2][4] == 0 && c[3][4] == 0 && c[4][4] == 0 && c[5][4] == 0)
                || (c[1][5] == 0 && c[2][5] == 0 && c[3][5] == 0 && c[4][5] == 0 && c[5][5] == 0)) {
            Toast.makeText(getApplicationContext(), "Game over. You win!", Toast.LENGTH_LONG).show();
            gameWin();
            gamePlayed();
            gameOver = true;
            disableButtons();
        } else if ((c[1][1] == 1 && c[2][2] == 1 && c[3][3] == 1 && c[4][4] == 1 && c[5][5] == 1)
                || (c[1][5] == 1 && c[2][4] == 1 && c[3][3] == 1 && c[4][2] == 1 && c[5][1] == 1)
                || (c[1][1] == 1 && c[1][2] == 1 && c[1][3] == 1 && c[1][4] == 1 && c[1][5] == 1)
                || (c[2][1] == 1 && c[2][2] == 1 && c[2][3] == 1 && c[2][4] == 1 && c[2][5] == 1)
                || (c[3][1] == 1 && c[3][2] == 1 && c[3][3] == 1 && c[3][4] == 1 && c[3][5] == 1)
                || (c[4][1] == 1 && c[4][2] == 1 && c[4][3] == 1 && c[4][4] == 1 && c[4][5] == 1)
                || (c[5][1] == 1 && c[5][2] == 1 && c[5][3] == 1 && c[5][4] == 1 && c[5][5] == 1)
                || (c[1][1] == 1 && c[2][1] == 1 && c[3][1] == 1 && c[4][1] == 1 && c[5][1] == 1)
                || (c[1][2] == 1 && c[2][2] == 1 && c[3][2] == 1 && c[4][2] == 1 && c[5][2] == 1)
                || (c[1][3] == 1 && c[2][3] == 1 && c[3][3] == 1 && c[4][3] == 1 && c[5][3] == 1)
                || (c[1][4] == 1 && c[2][4] == 1 && c[3][4] == 1 && c[4][4] == 1 && c[5][4] == 1)
                || (c[1][5] == 1 && c[2][5] == 1 && c[3][5] == 1 && c[4][5] == 1 && c[5][5] == 1)) {
            Toast.makeText(getApplicationContext(), "Game over. You lost!", Toast.LENGTH_LONG).show();
            gameLost();
            gamePlayed();
            gameOver = true;
            disableButtons();
        } else {
            boolean empty = false;
            for(i=1; i<=5; i++) {
                for(j=1; j<=5; j++) {
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
        for (i = 1; i <= 5; i++) {
            for (j = 1; j <= 5; j++) {
                b[i][j].setEnabled(false);
            }
        }
    }

    public void gameWin(){
        if(mPreferences.contains("GAMES_WON_FIVE")) {
            int gw = mPreferences.getInt("GAMES_WON_FIVE", 0);
            gamesWon = gw + 1;
            mEditor.putInt("GAMES_WON_FIVE", gamesWon);
            mEditor.apply();
        }
    }

    public void gameLost(){
        if(mPreferences.contains("GAMES_LOST_FIVE")) {
            int gl = mPreferences.getInt("GAMES_LOST_FIVE", 0);
            gamesLost = gl + 1;
            mEditor.putInt("GAMES_LOST_FIVE", gamesLost);
            mEditor.apply();
        }
    }

    public void gameDraw(){
        if(mPreferences.contains("GAMES_DRAW_FIVE")) {
            int gd = mPreferences.getInt("GAMES_DRAW_FIVE", 0);
            gamesDraw = gd + 1;
            mEditor.putInt("GAMES_DRAW_FIVE", gamesDraw);
            mEditor.apply();
        }
    }

    public void gamePlayed(){
        if(mPreferences.contains("GAMES_PLAYED_FIVE")) {
            int gw = mPreferences.getInt("GAMES_WON_FIVE", 0);
            int gl = mPreferences.getInt("GAMES_LOST_FIVE", 0);
            int gd = mPreferences.getInt("GAMES_DRAW_FIVE", 0);
            gamesPlayed = (gw + gl + gd);
            mEditor.putInt("GAMES_PLAYED_FIVE", gamesPlayed);
            mEditor.apply();
        }
    }
}

