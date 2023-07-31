package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CalculateHelper calculateHelper;

    boolean isBracket;
    boolean isPreview;

    TextView textView;
    TextView textView2;

    int size;
    String result;

    Button num0;
    Button num1;
    Button num2;
    Button num3;
    Button num4;
    Button num5;
    Button num6;
    Button num7;
    Button num8;
    Button num9;
    Button add;
    Button sub;
    Button mul;
    Button div;
    Button clear;
    Button bracket;
    Button nextpage;
    Button back;
    Button dot;
    Button equal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        calculateHelper = new CalculateHelper();

        size=0;
        setButton();
        nextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this , SecondActivity.class);
                String textview = textView.getText().toString();
                String textview2 = textView2.getText().toString();
                i.putExtra("textview", textview);
                i.putExtra("textview2", textview2);
                startActivity(i);
                finish();
            }
        });
        setTextView();
        Intent intent= getIntent();
        String textview = intent.getStringExtra("textview");
        String textview2 = intent.getStringExtra("textview2");
        textView.setText(textview);
        textView2.setText(textview2);
    }


    private void setButton(){
        num0 = findViewById(R.id.num0);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        num3 = findViewById(R.id.num3);
        num4 = findViewById(R.id.num4);
        num5 = findViewById(R.id.num5);
        num6 = findViewById(R.id.num6);
        num7 = findViewById(R.id.num7);
        num8 = findViewById(R.id.num8);
        num9 = findViewById(R.id.num9);

        add = findViewById(R.id.add);
        sub = findViewById(R.id.sub);
        mul = findViewById(R.id.mul);
        div = findViewById(R.id.div);
        clear = findViewById(R.id.clear);
        bracket = findViewById(R.id.bracket);
        nextpage = findViewById(R.id.nextpage);
        back = findViewById(R.id.back);
        dot = findViewById(R.id.dot);
        equal = findViewById(R.id.equal);

        num0.setOnClickListener(numClickListener);
        num1.setOnClickListener(numClickListener);
        num2.setOnClickListener(numClickListener);
        num3.setOnClickListener(numClickListener);
        num4.setOnClickListener(numClickListener);
        num5.setOnClickListener(numClickListener);
        num6.setOnClickListener(numClickListener);
        num7.setOnClickListener(numClickListener);
        num8.setOnClickListener(numClickListener);
        num9.setOnClickListener(numClickListener);

        add.setOnClickListener(markClickListener);
        sub.setOnClickListener(markClickListener);
        mul.setOnClickListener(markClickListener);
        div.setOnClickListener(markClickListener);
        clear.setOnClickListener(markClickListener);
        bracket.setOnClickListener(markClickListener);
        nextpage.setOnClickListener(markClickListener);
        back.setOnClickListener(markClickListener);
        dot.setOnClickListener(markClickListener);
        equal.setOnClickListener(markClickListener);
    }

    Button.OnClickListener numClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.num0:textView.append("0");break;
                case R.id.num1:textView.append("1");break;
                case R.id.num2:textView.append("2");break;
                case R.id.num3:textView.append("3");break;
                case R.id.num4:textView.append("4");break;
                case R.id.num5:textView.append("5");break;
                case R.id.num6:textView.append("6");break;
                case R.id.num7:textView.append("7");break;
                case R.id.num8:textView.append("8");break;
                case R.id.num9:textView.append("9");break;
            }
            preview();
        }
    };


    Button.OnClickListener markClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch (v.getId()) {
                case R.id.add:
                    textView.append(" + ");
                    isPreview = true;
                    break;
                case R.id.sub:
                    textView.append(" - ");
                    isPreview = true;
                    break;
                case R.id.mul:
                    textView.append(" * ");
                    isPreview = true;
                    break;
                case R.id.div:
                    textView.append(" / ");
                    isPreview = true;
                    break;
                case R.id.nextpage:
                    Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                    startActivity(intent);
                    break;
                case R.id.clear:
                    textView.setText("");
                    textView2.setText("");
                    isBracket = false;
                    calculateHelper = new CalculateHelper();
                    isPreview = false;
                    break;
                case R.id.bracket:
                    if (!isBracket) {
                        textView.append("( ");
                        isBracket = true;
                    } else {
                        textView.append(" )");
                        isBracket = false;
                    }
                    isPreview = true;
                    break;
                case R.id.back:
                    size = textView.getText().length();

                    if (size != 0)
                        textView.setText(textView.getText().toString().substring(0, size - 1));

                    if (size > 1) {
                        if (textView.getText().toString().substring(size - 2).equals("(")){
                            isBracket=false;
                        }
                        else if (calculateHelper.checkNumber(textView.getText().toString().substring(size - 2)))
                            preview();
                        else {
                            isPreview = false;
                            textView2.setText("");
                        }
                    }
                    break;
                case R.id.dot:
                    textView.append(".");
                    break;
                case R.id.equal:
                    result = textView.getText().toString();
                    double r = calculateHelper.process(result);
                    textView.setText(String.valueOf(r));
                    textView2.setText("");
                    isPreview = false;
                    break;
            }
        }
    };

    private void preview(){
        if(isPreview){
            result = textView.getText().toString();
            double r = calculateHelper.process(result);
            textView2.setText(String.valueOf(r));
        }}

    private void setTextView(){
        textView = findViewById(R.id.first_textView);
        textView2 = findViewById(R.id.second_textView);
    }
}

