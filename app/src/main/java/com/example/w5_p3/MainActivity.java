package com.example.w5_p3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class MainActivity extends AppCompatActivity implements game_layout.ControlFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void sendScore(int score) {
        bottomFragment receivingFragment = (bottomFragment)getSupportFragmentManager().findFragmentById(R.id.bottomFragment);
        receivingFragment.setGameScore(score);

    }
}
