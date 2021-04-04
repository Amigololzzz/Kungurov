package com.example.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Окно
 */
public class MainActivity extends AppCompatActivity {


    // Кнопки
    Button mBtn0;
    Button mBtn1;
    Button mBtn2;
    Button mBtn3;
    Button mBtn4;
    Button mBtn5;
    Button mBtn6;
    Button mBtn7;
    Button mBtn8;
    Button mBtn9;

    TextView mDisplay;

    Button mBtnBack;
    Button mBtnClear;
    Button mBtnZap;
    Button mBtnComma;


    Button mBtnPlus;
    Button mBtnMin;
    Button mBtnPrz;
    Button mBtnMul;
    Button mBtnResult;

    //Состояние калькулятора
    float mValue = 0;
    String mOperator = "";
    boolean isWriteStopped = false;
    String divZero = "Делить на 0 нельзя!";
    boolean divOnZero = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mBtn0 = findViewById(R.id.btn0);
        mBtn1 = findViewById(R.id.btn1);
        mBtn2 = findViewById(R.id.btn2);
        mBtn3 = findViewById(R.id.btn3);
        mBtn4 = findViewById(R.id.btn4);
        mBtn5 = findViewById(R.id.btn5);
        mBtn6 = findViewById(R.id.btn6);
        mBtn7 = findViewById(R.id.btn7);
        mBtn8 = findViewById(R.id.btn8);
        mBtn9 = findViewById(R.id.btn9);

        mDisplay = findViewById(R.id.Display);

        mBtnBack = findViewById(R.id.btnback);
        mBtnClear = findViewById(R.id.btnClear);
        mBtnComma = findViewById(R.id.btnComma);

        mBtnMul = findViewById(R.id.btnMul);

        mBtnPlus = findViewById(R.id.btnplus);
        mBtnMin = findViewById(R.id.btnmin);
        mBtnPrz = findViewById(R.id.btnprz);
        mBtnZap = findViewById(R.id.btnzap);

        mBtnResult = findViewById(R.id.btnResult);

        View.OnClickListener operatorListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperatorListener(v);
            }
        };
        mBtnPlus.setOnClickListener(operatorListener);
        mBtnMin.setOnClickListener(operatorListener);
        mBtnZap.setOnClickListener(operatorListener);
        mBtnPrz.setOnClickListener(operatorListener);

        View.OnClickListener resultListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResultListener(v);
            }
        };

        mBtnResult.setOnClickListener(resultListener);

        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClick(v);
            }

        };
        mBtn0.setOnClickListener(numberListener);
        mBtn1.setOnClickListener(numberListener);
        mBtn2.setOnClickListener(numberListener);
        mBtn3.setOnClickListener(numberListener);
        mBtn4.setOnClickListener(numberListener);
        mBtn5.setOnClickListener(numberListener);
        mBtn6.setOnClickListener(numberListener);
        mBtn7.setOnClickListener(numberListener);
        mBtn8.setOnClickListener(numberListener);
        mBtn9.setOnClickListener(numberListener);

        View.OnClickListener clearListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClearListener(v);
            }
        };
        mBtnClear.setOnClickListener(clearListener);

        View.OnClickListener backListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackListener(v);
            }
        };

        mBtnBack.setOnClickListener(backListener);

        View.OnClickListener ZapListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onZapListener(v);
            }
        };
        mBtnZap.setOnClickListener(ZapListener);

        View.OnClickListener mulListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMulListener(v);
            }
        };

        mBtnMul.setOnClickListener(mulListener);
    }


    public void onNumberClick(View button) {
        String number = ((Button) button).getText().toString();
        String display = mDisplay.getText().toString();

        if (display.equals("0"))
            display = number;
        else
            display += number;
        mDisplay.setText(display);
    }

    public void onOperatorListener(View button) {
        //1
        String operator = ((Button) button).getText().toString();
        mOperator = operator;
        //2
        String display = mDisplay.getText().toString();
        mValue = Float.parseFloat(display);
        //3
        mDisplay.setText("0");
    }

    public void onResultListener(View button) {
        //1
        String display = mDisplay.getText().toString();
        float value = Float.parseFloat(display);

        //2
        float result = value;

        //3
        switch (mOperator) {
            case "+": {
                result = value + mValue;
                break;
            }
            case "-": {
                result = mValue - value;
                break;
            }
            case "*": {
                result = mValue * value;
                break;
            }
            case "/": {
                if(value!=0)
                    result = mValue / value;
                else
                    divOnZero = true;
                break;
            }

        }
        //4
        DecimalFormat format = new DecimalFormat("0.######");
        format.setRoundingMode(RoundingMode.DOWN);
        String resultText = format.format(value);

        //5
        mDisplay.setText(resultText);

        //6
        mValue = result;
        mOperator = "";
        isWriteStopped = true;
        divOnZero = false;
    }

    public void onClearListener(View button) {
        mDisplay.setText("0");
        mValue = 0;
        mOperator = "";
    }

    public void onMulListener(View button) {

        float value = Float.parseFloat(mDisplay.getText().toString());
        value = value * -1;

        DecimalFormat format = new DecimalFormat("0.######");
        //format.setRoundingMode(RoundingMode.DOWN);

        String resValue = format.format(value).replace(",", ".");

        mDisplay.setText(String.valueOf(resValue));
    }
    public void onBackListener(View button){
        String text = mDisplay.getText().toString().replace(",", ".");
        System.out.println(text);
        System.out.println(isWriteStopped);
        if(isWriteStopped==false && text.length()>1)
            mDisplay.setText(removeLastChar(text));
        else if(isWriteStopped==false && !text.equals("0"))
            mDisplay.setText("0");
    }
    public static String removeLastChar(String str) {
        return removeLastChars(str, 1);
    }

    public static String removeLastChars(String str, int chars) {
        return str.substring(0, str.length() - chars);
    }
    public void onZapListener(View button){
        String text = mDisplay.getText().toString();

        String lastSymbol = Character.toString(text.charAt(text.length()-1));
        String addZap = text + ".";

        boolean isHasZap = false;
        for(int i = 0; i < text.length(); i++)
            if(Character.toString(text.charAt(i)).equals("."))
                isHasZap = true;

        if (isHasZap == false)
            mDisplay.setText(addZap);
    }
}