package com.example.gugudam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button start;
    TextView textview;
    TextView textview2;
    TextView textview3;
    TextView textview4;
    TextView textview5;
    TextView textview6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setButton();
        setText();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this , SecondActivity.class);
                String textview1 = textview.getText().toString();
                i.putExtra("textview1", textview1);
                String textview22 = textview2.getText().toString();
                i.putExtra("textview2", textview22);
                String textview33 = textview3.getText().toString();
                i.putExtra("textview3", textview33);
                String textview44 = textview4.getText().toString();
                i.putExtra("textview4", textview44);
                String textview55 = textview5.getText().toString();
                i.putExtra("textview5", textview55);
                String textview66 = textview6.getText().toString();
                i.putExtra("textview6", textview66);
                startActivity(i);
                finish();
            }
        });
    }

    private void setButton(){
        start=findViewById(R.id.start);
        textview=findViewById(R.id.textView);
        textview2=findViewById(R.id.textView2);
        textview3=findViewById(R.id.textView3);
        textview4=findViewById(R.id.textView4);
        textview5=findViewById(R.id.textView5);
        textview6=findViewById(R.id.textView6);
    }
    private void setText(){
        Intent intent= getIntent();
        String textview1 = intent.getStringExtra("textview1");
        String textview22 = intent.getStringExtra("textview2");
        String textview33 = intent.getStringExtra("textview3");
        String textview44 = intent.getStringExtra("textview4");
        String textview55 = intent.getStringExtra("textview5");
        String textview66 = intent.getStringExtra("textview6");
        textview6.setText(textview66);
        textview5.setText(textview55);
        textview4.setText(textview44);
        textview3.setText(textview33);
        textview2.setText(textview22);
        textview.setText(textview1);
    }
}
