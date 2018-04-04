package com.example.android.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThreeBoardActivity extends AppCompatActivity {

    private TextView back;
    private View decorView;
    private Button reset, b1, b2, b3, b4, b5, b6, b7, b8, b9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_board);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent menu = new Intent(ThreeBoardActivity.this, MainActivity.class);
                startActivity(menu);
                finish();
            }
        });

        decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        b1 = findViewById(R.id.r0c0);
        b2 = findViewById(R.id.r0c1);
        b3 = findViewById(R.id.r0c2);
        b4 = findViewById(R.id.r1c0);
        b5 = findViewById(R.id.r1c1);
        b6 = findViewById(R.id.r1c2);
        b7 = findViewById(R.id.r2c0);
        b8 = findViewById(R.id.r2c1);
        b9 = findViewById(R.id.r2c2);
    }

    public void gameLogic(View view){
        Button tappedView = (Button) view;
        tappedView.setTranslationY(-1000f);
        tappedView.setText(R.string.letter_o);
        tappedView.animate().translationYBy(1000f).setDuration(5);
    }

    public void resetGame(View view){
        b1.setText("");
        b2.setText("");
        b3.setText("");
        b4.setText("");
        b5.setText("");
        b6.setText("");
        b7.setText("");
        b8.setText("");
        b9.setText("");
    }
}
