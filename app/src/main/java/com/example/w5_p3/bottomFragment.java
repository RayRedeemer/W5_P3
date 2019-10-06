package com.example.w5_p3;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class bottomFragment extends Fragment {

    private TextView scoreText;
    private Button newGamebtn;


    public bottomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bottom, container, false);
        scoreText = view.findViewById(R.id.scoreTextBox);
        newGamebtn = view.findViewById(R.id.newGameBtn);
        return view;
    }

    public void setGameScore(int score){
        scoreText.setText(String.valueOf(score));
    }

}
