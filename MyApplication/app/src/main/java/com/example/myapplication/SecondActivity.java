package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    CalculateHelper calculateHelper;

    boolean isBracket;
    boolean isPreview;

    TextView textView;
    TextView textView2;

    int size;
    String result;

    Button square;
    Button ln;
    Button euler;
    Button factorial;
    Button square_root;
    Button square2;
    Button square3;
    Button sin;
    Button cos;
    Button tan;
    Button add;
    Button sub;
    Button mul;
    Button log;
    Button clear;
    Button pi;
    Button nextpage;
    Button back;
    Button dot;
    Button equal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        calculateHelper = new CalculateHelper();
        size=0;
        setButton();
        nextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecondActivity.this , MainActivity.class);
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
        square = findViewById(R.id.square);
        ln = findViewById(R.id.ln);
        euler = findViewById(R.id.euler);
        factorial = findViewById(R.id.factorial);
        square_root = findViewById(R.id.square_root);
        square2 = findViewById(R.id.square2);
        square3 = findViewById(R.id.square3);
        sin = findViewById(R.id.sin);
        cos = findViewById(R.id.cos);
        tan = findViewById(R.id.tan);

        add = findViewById(R.id.add);
        sub = findViewById(R.id.sub);
        mul = findViewById(R.id.mul);
        log = findViewById(R.id.log);
        clear = findViewById(R.id.clear);
        pi = findViewById(R.id.pi);
        nextpage = findViewById(R.id.nextpage);
        back = findViewById(R.id.back);
        dot = findViewById(R.id.dot);
        equal = findViewById(R.id.equal);

        square_root.setOnClickListener(markClickListener);
        square.setOnClickListener(markClickListener);
        add.setOnClickListener(markClickListener);
        sub.setOnClickListener(markClickListener);
        mul.setOnClickListener(markClickListener);
        clear.setOnClickListener(markClickListener);
        nextpage.setOnClickListener(markClickListener);
        back.setOnClickListener(markClickListener);
        dot.setOnClickListener(markClickListener);
        equal.setOnClickListener(markClickListener);
        tan.setOnClickListener(markClickListener);
        sin.setOnClickListener(markClickListener);
        cos.setOnClickListener(markClickListener);
        ln.setOnClickListener(markClickListener);
        log.setOnClickListener(markClickListener);

        factorial.setOnClickListener(markClickListener2);
        square3.setOnClickListener(markClickListener2);
        square2.setOnClickListener(markClickListener2);
        pi.setOnClickListener(markClickListener2);
        euler.setOnClickListener(markClickListener2);
    }

    Button.OnClickListener markClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch (v.getId()) {
                case R.id.add:
                    textView.append(" + ");
                    isPreview = true;
                    break;
                case R.id.square:
                    textView.append(" ^ ");
                    isPreview=true;
                    break;

                case R.id.square_root:
                    textView.append(" √ ");
                    isPreview=true;
                    break;

                case R.id.sub:
                    textView.append(" - ");
                    isPreview = true;
                    break;
                case R.id.mul:
                    textView.append(" * ");
                    isPreview = true;
                    break;
                case R.id.clear:
                    textView.setText("");
                    textView2.setText("");
                    isBracket = false;
                    calculateHelper = new CalculateHelper();
                    isPreview = false;
                    break;
                case R.id.back:
                    size = textView.getText().length();
                    if (size != 0)
                        textView.setText(textView.getText().toString().substring(0, size - 1));

                    if (size > 1) {
                        if (calculateHelper.checkNumber(textView.getText().toString().substring(size - 2)))
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
                case R.id.log:
                    textView.append(" log ");
                    break;

                case R.id.ln:
                    textView.append(" ln ");
                    break;

                case R.id.sin:
                    textView.append(" sin ");
                    break;
                case R.id.cos:
                    textView.append(" cos ");
                    break;
                case R.id.tan:
                    textView.append(" tan ");
                    break;

            }
        }
    };

    Button.OnClickListener markClickListener2 = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch (v.getId()) {
                case R.id.pi:
                    textView.append("π");
                    isPreview=true;
                    break;
                case R.id.euler:
                    textView.append("e");
                    isPreview=true;
                    break;
                case R.id.square2:
                    textView.append(" ^2 ");
                    isPreview=true;
                    break;
                case R.id.square3:
                    textView.append(" ^3 ");
                    isPreview=true;
                    break;

                case R.id.factorial:
                    textView.append(" ! ");
                    isPreview=true;
                    break;

            }
            preview();
        }
    };

    private void preview(){
        if(isPreview){
            result = textView.getText().toString();
            double r = calculateHelper.process(result);
            textView2.setText(String.valueOf(r));
        }
    }

    private void setTextView(){
        textView = findViewById(R.id.first_textView);
        textView2 = findViewById(R.id.second_textView);
    }
}