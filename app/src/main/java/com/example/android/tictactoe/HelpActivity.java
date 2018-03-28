package com.example.android.tictactoe;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.help_container, new PageOneHelpFragment());
        ft.commit();
    }
}
