package com.example.w5_p3;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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

    private Map<Integer, Button[]> clickableBtnPositions = new HashMap<>();

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

    // Clear & Submit Button

    private Button clearBtn;
    private Button submitBtn;

    // Arraylist with every button
    ArrayList<Button> allButtons = new ArrayList<Button>();

    // arraylist that contains the used buttons

    ArrayList<Button> usedButtons = new ArrayList<Button>();

    // A hashmap that associates every button in the game with its associated position

    private Map<Button, Integer> btnsAndTheirPosition = new HashMap<Button, Integer>();

    // A randomly generated index that will be used to assign a letter to every button
    private Random randInt = new Random();

    // A variable that will keep track of the user's score
    private int score;

    // A currentWord variable that will append the letters a user clicks on

    private String currentWord = "";

    // A variable to track if the user is currently on their first move or not. This will be used
    // to track what buttons a user can click

    private boolean firstMove = true;

    // instantiate an arraylist of buttons that a user can click on

    private ArrayList<Button> clickable;

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

        // add every button to an arraylist
        allButtons.add(btn1);
        allButtons.add(btn2);
        allButtons.add(btn3);
        allButtons.add(btn4);
        allButtons.add(btn5);
        allButtons.add(btn6);
        allButtons.add(btn7);
        allButtons.add(btn8);
        allButtons.add(btn9);
        allButtons.add(btn10);
        allButtons.add(btn11);
        allButtons.add(btn12);
        allButtons.add(btn13);
        allButtons.add(btn14);
        allButtons.add(btn15);
        allButtons.add(btn16);

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

        clickableBtnPositions.put(1, new Button[]{btn2, btn5});
        clickableBtnPositions.put(2, new Button[]{btn1, btn6, btn3});
        clickableBtnPositions.put(3, new Button[]{btn2, btn7, btn4});
        clickableBtnPositions.put(4, new Button[]{btn3, btn8});
        clickableBtnPositions.put(5, new Button[]{btn1, btn9, btn6});
        clickableBtnPositions.put(6, new Button[]{btn2, btn5, btn7, btn10});
        clickableBtnPositions.put(7, new Button[]{btn3, btn6, btn8, btn11});
        clickableBtnPositions.put(8, new Button[]{btn4, btn7, btn12});
        clickableBtnPositions.put(9, new Button[]{btn5, btn10, btn13});
        clickableBtnPositions.put(10, new Button[]{btn6, btn9, btn11, btn14});
        clickableBtnPositions.put(11, new Button[]{btn7, btn10, btn12, btn15});
        clickableBtnPositions.put(12, new Button[]{btn8, btn11, btn16});
        clickableBtnPositions.put(13, new Button[]{btn9, btn14});
        clickableBtnPositions.put(14, new Button[]{btn13, btn10, btn15});
        clickableBtnPositions.put(15, new Button[]{btn14, btn11, btn16});
        clickableBtnPositions.put(16, new Button[]{btn12, btn15});

        //A method that will give every button a letter

        for (Map.Entry<Button, Integer> btn : btnsAndTheirPosition.entrySet()) {
            int randIndex = randInt.nextInt(23);
            btn.getKey().setText(allLetters[randIndex].toString());
        }

        // After every button has been assigned a letter, make each button visible

        for (Map.Entry<Button, Integer> btn : btnsAndTheirPosition.entrySet()) {
            btn.getKey().setVisibility(View.VISIBLE);
        }

        // Set an onClicklistener for every button so that when a user clicks on the button,
        // the corresponding letter is appended to the currentWord string

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstMove) {
                    currentWord += btn1.getText().toString();
                    Log.i("word", currentWord);
                    btn1.setClickable(false);
                    usedButtons.add(btn1);
                    clickable = new ArrayList<>(Arrays.asList(clickableBtnPositions.get(1)));
                    firstMove = false;
                    ArrayList<Button> validPositions = new ArrayList<Button>(Arrays.asList(clickableBtnPositions.get(1)));
                    for(Button btn : allButtons){
                        if (!validPositions.contains(btn)){
                            btn.setClickable(false);
                        }
                    }
                }

                else {
                    ArrayList<Button> validPositions = new ArrayList<Button>(Arrays.asList(clickableBtnPositions.get(2)));
                    usedButtons.add(btn2);
                    for (Button btn : allButtons){
                        if (!usedButtons.contains(btn) && validPositions.contains(btn)){
                            btn.setClickable(true);
                        }
                    }
                    currentWord += btn2.getText().toString();
                    Log.i("word", currentWord);
                    btn2.setClickable(false);
                    usedButtons.add(btn2);
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstMove) {
                    currentWord += btn2.getText().toString();
                    Log.i("word", currentWord);
                    btn2.setClickable(false);
                    usedButtons.add(btn2);
                    clickable = new ArrayList<>(Arrays.asList(clickableBtnPositions.get(2)));
                    firstMove = false;
                    ArrayList<Button> validPositions = new ArrayList<Button>(Arrays.asList(clickableBtnPositions.get(2)));
                    for(Button btn : allButtons){
                        if (!validPositions.contains(btn)){
                            btn.setClickable(false);
                        }
                    }
                }

                else {
                    ArrayList<Button> validPositions = new ArrayList<Button>(Arrays.asList(clickableBtnPositions.get(2)));
                    usedButtons.add(btn2);
                    for (Button btn : allButtons){
                        if (!usedButtons.contains(btn) && validPositions.contains(btn)){
                            btn.setClickable(true);
                        }
                    }
                    currentWord += btn2.getText().toString();
                    Log.i("word", currentWord);
                    btn2.setClickable(false);
                    usedButtons.add(btn2);
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstMove) {
                    currentWord += btn3.getText().toString();
                    Log.i("word", currentWord);
                    btn3.setClickable(false);
                    usedButtons.add(btn3);
                    clickable = new ArrayList<>(Arrays.asList(clickableBtnPositions.get(3)));
                    firstMove = false;
                    ArrayList<Button> validPositions = new ArrayList<Button>(Arrays.asList(clickableBtnPositions.get(3)));
                    for(Button btn : allButtons){
                        if (!validPositions.contains(btn)){
                            btn.setClickable(false);
                        }
                    }
                }

                else {
                    ArrayList<Button> validPositions = new ArrayList<Button>(Arrays.asList(clickableBtnPositions.get(3)));
                    usedButtons.add(btn3);
                    for (Button btn : allButtons){
                        if (!usedButtons.contains(btn) && validPositions.contains(btn)){
                            btn.setClickable(true);
                        }
                    }
                    currentWord += btn3.getText().toString();
                    Log.i("word", currentWord);
                    btn3.setClickable(false);
                    usedButtons.add(btn3);
                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstMove) {
                    //Log.i("first move", "first move");
                    currentWord += btn4.getText().toString();
                    Log.i("word", currentWord);
                    btn4.setClickable(false);
                    usedButtons.add(btn4);
                    clickable = new ArrayList<>(Arrays.asList(clickableBtnPositions.get(4)));
                    firstMove = false;
                    ArrayList<Button> validPositions = new ArrayList<Button>(Arrays.asList(clickableBtnPositions.get(4)));
                    for(Button btn : allButtons){
                        if (!validPositions.contains(btn)){
                            btn.setClickable(false);
                        }
                    }
                }

                else {
                    ArrayList<Button> validPositions = new ArrayList<Button>(Arrays.asList(clickableBtnPositions.get(4)));
                    usedButtons.add(btn4);
                    for (Button btn : allButtons){
                        if (!usedButtons.contains(btn) && validPositions.contains(btn)){
                            btn.setClickable(true);
                        }
                    }
                    currentWord += btn4.getText().toString();
                    Log.i("word", currentWord);
                    btn4.setClickable(false);
                    usedButtons.add(btn4);
                }
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstMove) {
                    //Log.i("first move", "first move");
                    currentWord += btn5.getText().toString();
                    Log.i("word", currentWord);
                    btn5.setClickable(false);
                    usedButtons.add(btn4);
                    clickable = new ArrayList<>(Arrays.asList(clickableBtnPositions.get(5)));
                    firstMove = false;
                    ArrayList<Button> validPositions = new ArrayList<Button>(Arrays.asList(clickableBtnPositions.get(5)));
                    for(Button btn : allButtons){
                        if (!validPositions.contains(btn)){
                            btn.setClickable(false);
                        }
                    }
                }

                else {
                    ArrayList<Button> validPositions = new ArrayList<Button>(Arrays.asList(clickableBtnPositions.get(5)));
                    usedButtons.add(btn5);
                    for (Button btn : allButtons){
                        if (!usedButtons.contains(btn) && validPositions.contains(btn)){
                            btn.setClickable(true);
                        }
                    }
                    currentWord += btn5.getText().toString();
                    Log.i("word", currentWord);
                    btn5.setClickable(false);
                    usedButtons.add(btn5);
                }
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstMove) {
                    //Log.i("first move", "first move");
                    currentWord += btn6.getText().toString();
                    Log.i("word", currentWord);
                    btn6.setClickable(false);
                    usedButtons.add(btn6);
                    clickable = new ArrayList<>(Arrays.asList(clickableBtnPositions.get(6)));
                    firstMove = false;
                    ArrayList<Button> validPositions = new ArrayList<Button>(Arrays.asList(clickableBtnPositions.get(6)));
                    for(Button btn : allButtons){
                        if (!validPositions.contains(btn)){
                            btn.setClickable(false);
                        }
                    }
                }

                else {
                    ArrayList<Button> validPositions = new ArrayList<Button>(Arrays.asList(clickableBtnPositions.get(6)));
                    usedButtons.add(btn6);
                    for (Button btn : allButtons){
                        if (!usedButtons.contains(btn) && validPositions.contains(btn)){
                            btn.setClickable(true);
                        }
                    }
                    currentWord += btn6.getText().toString();
                    Log.i("word", currentWord);
                    btn6.setClickable(false);
                    usedButtons.add(btn6);
                }
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstMove) {
                    //Log.i("first move", "first move");
                    currentWord += btn7.getText().toString();
                    Log.i("word", currentWord);
                    btn7.setClickable(false);
                    usedButtons.add(btn7);
                    clickable = new ArrayList<>(Arrays.asList(clickableBtnPositions.get(7)));
                    firstMove = false;
                    ArrayList<Button> validPositions = new ArrayList<Button>(Arrays.asList(clickableBtnPositions.get(7)));
                    for(Button btn : allButtons){
                        if (!validPositions.contains(btn)){
                            btn.setClickable(false);
                        }
                    }
                }

                else {
                    ArrayList<Button> validPositions = new ArrayList<Button>(Arrays.asList(clickableBtnPositions.get(7)));
                    usedButtons.add(btn7);
                    for (Button btn : allButtons){
                        if (!usedButtons.contains(btn) && validPositions.contains(btn)){
                            btn.setClickable(true);
                        }
                    }
                    currentWord += btn7.getText().toString();
                    Log.i("word", currentWord);
                    btn7.setClickable(false);
                    usedButtons.add(btn7);
                }
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstMove) {
                    //Log.i("first move", "first move");
                    currentWord += btn8.getText().toString();
                    Log.i("word", currentWord);
                    btn8.setClickable(false);
                    usedButtons.add(btn8);
                    clickable = new ArrayList<>(Arrays.asList(clickableBtnPositions.get(8)));
                    firstMove = false;
                    ArrayList<Button> validPositions = new ArrayList<Button>(Arrays.asList(clickableBtnPositions.get(8)));
                    for(Button btn : allButtons){
                        if (!validPositions.contains(btn)){
                            btn.setClickable(false);
                        }
                    }
                }

                else {
                    ArrayList<Button> validPositions = new ArrayList<Button>(Arrays.asList(clickableBtnPositions.get(8)));
                    usedButtons.add(btn8);
                    for (Button btn : allButtons){
                        if (!usedButtons.contains(btn) && validPositions.contains(btn)){
                            btn.setClickable(true);
                        }
                    }
                    currentWord += btn8.getText().toString();
                    Log.i("word", currentWord);
                    btn8.setClickable(false);
                    usedButtons.add(btn8);
                }
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstMove) {
                    //Log.i("first move", "first move");
                    currentWord += btn9.getText().toString();
                    Log.i("word", currentWord);
                    btn9.setClickable(false);
                    usedButtons.add(btn9);
                    clickable = new ArrayList<>(Arrays.asList(clickableBtnPositions.get(9)));
                    firstMove = false;
                    ArrayList<Button> validPositions = new ArrayList<Button>(Arrays.asList(clickableBtnPositions.get(9)));
                    for(Button btn : allButtons){
                        if (!validPositions.contains(btn)){
                            btn.setClickable(false);
                        }
                    }
                }

                else {
                    ArrayList<Button> validPositions = new ArrayList<Button>(Arrays.asList(clickableBtnPositions.get(9)));
                    usedButtons.add(btn9);
                    for (Button btn : allButtons){
                        if (!usedButtons.contains(btn) && validPositions.contains(btn)){
                            btn.setClickable(true);
                        }
                    }
                    currentWord += btn9.getText().toString();
                    Log.i("word", currentWord);
                    btn9.setClickable(false);
                    usedButtons.add(btn9);
                }
            }
        });

        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstMove) {
                    //Log.i("first move", "first move");
                    currentWord += btn10.getText().toString();
                    Log.i("word", currentWord);
                    btn10.setClickable(false);
                    usedButtons.add(btn10);
                    clickable = new ArrayList<>(Arrays.asList(clickableBtnPositions.get(10)));
                    firstMove = false;
                    ArrayList<Button> validPositions = new ArrayList<Button>(Arrays.asList(clickableBtnPositions.get(10)));
                    for(Button btn : allButtons){
                        if (!validPositions.contains(btn)){
                            btn.setClickable(false);
                        }
                    }
                }

                else {
                    ArrayList<Button> validPositions = new ArrayList<Button>(Arrays.asList(clickableBtnPositions.get(10)));
                    usedButtons.add(btn10);
                    for (Button btn : allButtons){
                        if (!usedButtons.contains(btn) && validPositions.contains(btn)){
                            btn.setClickable(true);
                        }
                    }
                    currentWord += btn10.getText().toString();
                    Log.i("word", currentWord);
                    btn10.setClickable(false);
                    usedButtons.add(btn10);
                }
            }
        });

        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstMove) {
                    //Log.i("first move", "first move");
                    currentWord += btn11.getText().toString();
                    Log.i("word", currentWord);
                    btn11.setClickable(false);
                    usedButtons.add(btn11);
                    clickable = new ArrayList<>(Arrays.asList(clickableBtnPositions.get(11)));
                    firstMove = false;
                    ArrayList<Button> validPositions = new ArrayList<Button>(Arrays.asList(clickableBtnPositions.get(11)));
                    for(Button btn : allButtons){
                        if (!validPositions.contains(btn)){
                            btn.setClickable(false);
                        }
                    }
                }

                else {
                    ArrayList<Button> validPositions = new ArrayList<Button>(Arrays.asList(clickableBtnPositions.get(11)));
                    usedButtons.add(btn11);
                    for (Button btn : allButtons){
                        if (!usedButtons.contains(btn) && validPositions.contains(btn)){
                            btn.setClickable(true);
                        }
                    }
                    currentWord += btn11.getText().toString();
                    Log.i("word", currentWord);
                    btn11.setClickable(false);
                    usedButtons.add(btn11);
                }
            }
        });

        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstMove) {
                    //Log.i("first move", "first move");
                    currentWord += btn12.getText().toString();
                    Log.i("word", currentWord);
                    btn12.setClickable(false);
                    usedButtons.add(btn12);
                    clickable = new ArrayList<>(Arrays.asList(clickableBtnPositions.get(12)));
                    firstMove = false;
                    ArrayList<Button> validPositions = new ArrayList<Button>(Arrays.asList(clickableBtnPositions.get(12)));
                    for(Button btn : allButtons){
                        if (!validPositions.contains(btn)){
                            btn.setClickable(false);
                        }
                    }
                }

                else {
                    ArrayList<Button> validPositions = new ArrayList<Button>(Arrays.asList(clickableBtnPositions.get(12)));
                    usedButtons.add(btn12);
                    for (Button btn : allButtons){
                        if (!usedButtons.contains(btn) && validPositions.contains(btn)){
                            btn.setClickable(true);
                        }
                    }
                    currentWord += btn12.getText().toString();
                    Log.i("word", currentWord);
                    btn12.setClickable(false);
                    usedButtons.add(btn12);
                }
            }
        });

        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstMove) {
                    //Log.i("first move", "first move");
                    currentWord += btn13.getText().toString();
                    Log.i("word", currentWord);
                    btn13.setClickable(false);
                    usedButtons.add(btn13);
                    clickable = new ArrayList<>(Arrays.asList(clickableBtnPositions.get(13)));
                    firstMove = false;
                    ArrayList<Button> validPositions = new ArrayList<Button>(Arrays.asList(clickableBtnPositions.get(13)));
                    for(Button btn : allButtons){
                        if (!validPositions.contains(btn)){
                            btn.setClickable(false);
                        }
                    }
                }

                else {
                    ArrayList<Button> validPositions = new ArrayList<Button>(Arrays.asList(clickableBtnPositions.get(13)));
                    usedButtons.add(btn13);
                    for (Button btn : allButtons){
                        if (!usedButtons.contains(btn) && validPositions.contains(btn)){
                            btn.setClickable(true);
                        }
                    }
                    currentWord += btn13.getText().toString();
                    Log.i("word", currentWord);
                    btn13.setClickable(false);
                    usedButtons.add(btn13);
                }
            }
        });

        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstMove) {
                    //Log.i("first move", "first move");
                    currentWord += btn14.getText().toString();
                    Log.i("word", currentWord);
                    btn14.setClickable(false);
                    usedButtons.add(btn4);
                    clickable = new ArrayList<>(Arrays.asList(clickableBtnPositions.get(14)));
                    firstMove = false;
                    ArrayList<Button> validPositions = new ArrayList<Button>(Arrays.asList(clickableBtnPositions.get(14)));
                    for(Button btn : allButtons){
                        if (!validPositions.contains(btn)){
                            btn.setClickable(false);
                        }
                    }
                }

                else {
                    ArrayList<Button> validPositions = new ArrayList<Button>(Arrays.asList(clickableBtnPositions.get(14)));
                    usedButtons.add(btn14);
                    for (Button btn : allButtons){
                        if (!usedButtons.contains(btn) && validPositions.contains(btn)){
                            btn.setClickable(true);
                        }
                    }
                    currentWord += btn14.getText().toString();
                    Log.i("word", currentWord);
                    btn14.setClickable(false);
                    usedButtons.add(btn14);
                }
            }
        });

        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstMove) {
                    //Log.i("first move", "first move");
                    currentWord += btn15.getText().toString();
                    Log.i("word", currentWord);
                    btn5.setClickable(false);
                    usedButtons.add(btn15);
                    clickable = new ArrayList<>(Arrays.asList(clickableBtnPositions.get(15)));
                    firstMove = false;
                    ArrayList<Button> validPositions = new ArrayList<Button>(Arrays.asList(clickableBtnPositions.get(15)));
                    for(Button btn : allButtons){
                        if (!validPositions.contains(btn)){
                            btn.setClickable(false);
                        }
                    }
                }

                else {
                    ArrayList<Button> validPositions = new ArrayList<Button>(Arrays.asList(clickableBtnPositions.get(15)));
                    usedButtons.add(btn15);
                    for (Button btn : allButtons){
                        if (!usedButtons.contains(btn) && validPositions.contains(btn)){
                            btn.setClickable(true);
                        }
                    }
                    currentWord += btn15.getText().toString();
                    Log.i("word", currentWord);
                    btn15.setClickable(false);
                    usedButtons.add(btn15);
                }
            }
        });

        btn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstMove) {
                    //Log.i("first move", "first move");
                    currentWord += btn16.getText().toString();
                    Log.i("word", currentWord);
                    btn16.setClickable(false);
                    usedButtons.add(btn4);
                    clickable = new ArrayList<>(Arrays.asList(clickableBtnPositions.get(16)));
                    firstMove = false;
                    ArrayList<Button> validPositions = new ArrayList<Button>(Arrays.asList(clickableBtnPositions.get(16)));
                    for(Button btn : allButtons){
                        if (!validPositions.contains(btn)){
                            btn.setClickable(false);
                        }
                    }
                }

                else {
                    ArrayList<Button> validPositions = new ArrayList<Button>(Arrays.asList(clickableBtnPositions.get(16)));
                    usedButtons.add(btn16);
                    for (Button btn : allButtons){
                        if (!usedButtons.contains(btn) && validPositions.contains(btn)){
                            btn.setClickable(true);
                        }
                    }
                    currentWord += btn16.getText().toString();
                    Log.i("word", currentWord);
                    btn16.setClickable(false);
                    usedButtons.add(btn16);
                }
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentWord = "";
                firstMove = true;
            }
        });

        return view;

    }



}
