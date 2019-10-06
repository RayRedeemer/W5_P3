package com.example.w5_p3;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class game_layout extends Fragment {

    // An array with every character, this allows us to assign a specific letter to each button when a new game
    // is generated

    final Character[] allLetters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z'};

    // An array that contains all of the vowels. We do not need to keep an array with the consonants, because
    // when we check every letter we can simply loop through the word and if the current letter is not in
    // the vowels array then it is a consonant

    final Character[] vowels = {'A', 'I', 'E', 'O', 'U'};


    // This hashmap contains every button position ([1, 2, 3, 4] and then a new row that begins with 5,
    // and so forth) as the key and the the other button positions that a user is able to click as the value
    // This will serve as a way to keep track of what button a user is on and what buttons they are allowed to press

    private Map<Integer, Integer[]> clickableBtnPositions = new HashMap<Integer, Integer[]>();

    // Every button is named after its position

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btn10;
    private Button btn11;
    private Button btn12;
    private Button btn13;
    private Button btn14;
    private Button btn15;
    private Button btn16;

    // A button variable named currentBtn to keep track of which button the user has most
    // recently clicked

    private Button currentBtn;

    // A hashmap that associates every button in the game with its associated position

    private Map<Button, Integer> btnsAndTheirPosition = new HashMap<Button, Integer>();

    // A randomly generated index that will be used to assign a letter to every button
    private Random randInt = new Random();

    // A variable that will keep track of the user's score
    private int score;

    public game_layout() {
        // Required empty public constructor
    }

    public interface ControlFragmentListener {
        public void sendScore(int score);
    }

    ControlFragmentListener CFL;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        CFL = (ControlFragmentListener) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_layout, container, false);


        // Instantiate every button
        btn1 = view.findViewById(R.id.btn1);
        btn2 = view.findViewById(R.id.btn2);
        btn3 = view.findViewById(R.id.btn3);
        btn4 = view.findViewById(R.id.btn4);
        btn5 = view.findViewById(R.id.btn5);
        btn6 = view.findViewById(R.id.btn6);
        btn7 = view.findViewById(R.id.btn7);
        btn8 = view.findViewById(R.id.btn8);
        btn9 = view.findViewById(R.id.btn9);
        btn10 = view.findViewById(R.id.btn10);
        btn11 = view.findViewById(R.id.btn11);
        btn12 = view.findViewById(R.id.btn12);
        btn13 = view.findViewById(R.id.btn13);
        btn14 = view.findViewById(R.id.btn14);
        btn15 = view.findViewById(R.id.btn15);
        btn16 = view.findViewById(R.id.btn16);


        // Associate every button with their corresponding position
        btnsAndTheirPosition.put(btn1, 1);
        btnsAndTheirPosition.put(btn2, 2);
        btnsAndTheirPosition.put(btn3, 3);
        btnsAndTheirPosition.put(btn4, 4);
        btnsAndTheirPosition.put(btn5, 5);
        btnsAndTheirPosition.put(btn6, 6);
        btnsAndTheirPosition.put(btn7, 7);
        btnsAndTheirPosition.put(btn8, 8);
        btnsAndTheirPosition.put(btn9, 9);
        btnsAndTheirPosition.put(btn10, 10);
        btnsAndTheirPosition.put(btn11, 11);
        btnsAndTheirPosition.put(btn12, 12);
        btnsAndTheirPosition.put(btn13, 13);
        btnsAndTheirPosition.put(btn14, 14);
        btnsAndTheirPosition.put(btn15, 15);
        btnsAndTheirPosition.put(btn16, 16);


        // Every button position and the corresponding buttons a user can click, i.e if
        // a user is on buttom 1, then they can ONLY click on 2 and 5 because diagonal movement
        // is prohibited

        clickableBtnPositions.put(1, new Integer[]{2, 5});
        clickableBtnPositions.put(2, new Integer[]{1, 6, 3});
        clickableBtnPositions.put(3, new Integer[]{2, 7, 4});
        clickableBtnPositions.put(4, new Integer[]{3, 8});
        clickableBtnPositions.put(5, new Integer[]{1, 9, 6});
        clickableBtnPositions.put(6, new Integer[]{2, 5, 7, 10});
        clickableBtnPositions.put(7, new Integer[]{3, 6, 8, 11});
        clickableBtnPositions.put(8, new Integer[]{4, 7, 12});
        clickableBtnPositions.put(9, new Integer[]{5, 10, 13});
        clickableBtnPositions.put(10, new Integer[]{6, 9, 11, 14});
        clickableBtnPositions.put(11, new Integer[]{7, 10, 12, 15});
        clickableBtnPositions.put(12, new Integer[]{8, 11, 16});
        clickableBtnPositions.put(13, new Integer[]{9, 14});
        clickableBtnPositions.put(14, new Integer[]{13, 10, 15});
        clickableBtnPositions.put(15, new Integer[]{14, 11, 16});
        clickableBtnPositions.put(16, new Integer[]{12, 15});

        //A method that will give every button a letter

        for (Map.Entry<Button, Integer> btn : btnsAndTheirPosition.entrySet()) {
            int randIndex = randInt.nextInt(23);
            btn.getKey().setText(allLetters[randIndex].toString());
        }

        // After every button has been assigned a letter, make each button visible

        for (Map.Entry<Button, Integer> btn : btnsAndTheirPosition.entrySet()) {
            btn.getKey().setVisibility(View.VISIBLE);
        }

        return view;

    }

}
