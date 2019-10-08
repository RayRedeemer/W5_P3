package com.example.w5_p3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.net.*;
import java.io.*;


public class MainActivity extends AppCompatActivity implements game_layout.ControlFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String dict = "";
//        try {
//            InputStream is = getAssets().open("words.txt");
//            int size = is.available();
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            dict = new String(buffer);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        String lines[] = dict.split("\\r?\\n");
//        Toast.makeText(this, lines[0], Toast.LENGTH_LONG).show();

    }


    @Override
    public void sendScore(int score) {
        bottomFragment receivingFragment = (bottomFragment)getSupportFragmentManager().findFragmentById(R.id.bottomFragment);
        receivingFragment.setGameScore(score);
    }
}
