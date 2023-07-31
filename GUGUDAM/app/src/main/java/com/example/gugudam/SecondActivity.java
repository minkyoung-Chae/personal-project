package com.example.gugudam;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class SecondActivity extends AppCompatActivity {
    private static final Random RANDOM = new Random();
    int i1= (int)((Math.random()*10000)%10);
    int i2= (int)((Math.random()*10000)%10);
    String s1 = String.valueOf(i1);
    String s2 = String.valueOf(i2);
    int cnt=0;
    int time=60;
    Button num1;
    Button num2;
    Button num3;
    Button num4;
    Button num5;
    Button num6;
    Button num7;
    Button num8;
    Button num9;
    Button num0;
    Button cancel;
    Button enter;
    TextView textView;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textview1;
    TextView textview2;
    TextView textview3;
    TextView textview4;
    TextView textview5;
    TextView textview6;
    BackThread mThread;
    int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setButton();
        setTextView();

        setTextView2();
        Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 60) {
                    textView4.setText(
                            "Time : " + msg.arg1);
                    time=msg.arg1;
                    if(time==0){
                        Intent i = new Intent(SecondActivity.this , MainActivity.class);
                        String textview1 = textView3.getText().toString();
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
                    Intent intent= getIntent();
                    String textview22 = intent.getStringExtra("textview1");
                    textview2.setText(textview22);
                    String textview33 = intent.getStringExtra("textview2");
                    textview3.setText(textview33);
                    String textview44 = intent.getStringExtra("textview3");
                    textview4.setText(textview44);
                    String textview55 = intent.getStringExtra("textview4");
                    textview5.setText(textview55);
                    String textview66 = intent.getStringExtra("textview5");
                    textview6.setText(textview66);
//                    String textview77 = intent.getStringExtra("textview6");
//                    textview3.setText(textview77);
                }
            }
        };

        textView4 = (TextView) findViewById(R.id.fourth_textView);
        mThread = new BackThread(mHandler);
        mThread.setDaemon(true);
        mThread.start();



    }

    private void setButton(){
        num1=findViewById(R.id.num1);
        num2=findViewById(R.id.num2);
        num3=findViewById(R.id.num3);
        num4=findViewById(R.id.num4);
        num5=findViewById(R.id.num5);
        num6=findViewById(R.id.num6);
        num7=findViewById(R.id.num7);
        num8=findViewById(R.id.num8);
        num9=findViewById(R.id.num9);
        num0=findViewById(R.id.num0);
        cancel=findViewById(R.id.cancel);
        enter=findViewById(R.id.enter);
        num1.setOnClickListener(numClickListener);
        num2.setOnClickListener(numClickListener);
        num3.setOnClickListener(numClickListener);
        num4.setOnClickListener(numClickListener);
        num5.setOnClickListener(numClickListener);
        num6.setOnClickListener(numClickListener);
        num7.setOnClickListener(numClickListener);
        num8.setOnClickListener(numClickListener);
        num9.setOnClickListener(numClickListener);
        num0.setOnClickListener(numClickListener);
        cancel.setOnClickListener(numClickListener);
        enter.setOnClickListener(numClickListener);

    }
    Button.OnClickListener numClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.num0:
                    textView.append("0");
                    break;
                case R.id.num1:
                    textView.append("1");
                    break;
                case R.id.num2:
                    textView.append("2");
                    break;
                case R.id.num3:
                    textView.append("3");
                    break;
                case R.id.num4:
                    textView.append("4");
                    break;
                case R.id.num5:
                    textView.append("5");
                    break;
                case R.id.num6:
                    textView.append("6");
                    break;
                case R.id.num7:
                    textView.append("7");
                    break;
                case R.id.num8:
                    textView.append("8");
                    break;
                case R.id.num9:
                    textView.append("9");
                    break;
                case R.id.cancel:
                    size = textView.getText().length();
                    if (size != 0)
                        textView.setText(textView.getText().toString().substring(0, size - 1));
                    break;
                case R.id.enter:
                    if(Integer.parseInt(String.valueOf(textView.getText()))==i1*i2){
                        cnt++;
                        i1= (int)((Math.random()*10000)%10);
                        i2= (int)((Math.random()*10000)%10);
                        s1 = String.valueOf(i1);
                        s2 = String.valueOf(i2);
                    }
                    setTextView2();
                    textView.setText("");
                    System.out.println(time);
                    break;
            }
        }

    };

    private void setTextView(){
        textView2= findViewById(R.id.first_textView);
        textView = findViewById(R.id.second_textView);
        textView3=findViewById(R.id.third_textView);
        textView4=findViewById(R.id.fourth_textView);
        textview1=findViewById(R.id.textview1);
        textview2=findViewById(R.id.textview2);
        textview3=findViewById(R.id.textview3);
        textview4=findViewById(R.id.textview4);
        textview5=findViewById(R.id.textview5);
        textview6=findViewById(R.id.textview6);
    }
    private void setTextView2(){
        textView2.setText(s2+" * "+s1);
        textView3.setText(String.valueOf(cnt));
        textView4 = (TextView)findViewById(R.id.fourth_textView);
    }
}
class BackThread extends Thread {
    int mBackValue = 60;
    Handler bHandler;
    BackThread(Handler handler) {
    bHandler = handler;
    }
    public void run() {
        while (mBackValue!=0) {
            mBackValue--;
            Message msg = new Message();
            msg.what = 60;
            msg.arg1 = mBackValue;
            bHandler.sendMessage(msg);
            try { Thread.sleep(1000); }
            catch (InterruptedException e) {;}
        }
    }
}