package com.programmer.soli.calculator;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn_0;
    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_5;
    Button btn_6;
    Button btn_7;
    Button btn_8;
    Button btn_9;
    Button btn_division;
    Button btn_back;
    Button btn_clear;
    Button btn_clearAll;
    Button btn_multiply;
    Button btn_minus;
    Button btn_plus;
    Button btn_dot;
    Button btn_equal;
    Button btn_sign;
    TextView txt_result;
    TextView txt_result2;


    boolean mustReset = false;
    float curentResult = 0;
    String operand = "";

    @SuppressLint("SetTextI18n")
    public void compute(String nextOperand) {
        String resultValue = txt_result.getText().toString();
        float resultNumbers = Float.parseFloat(resultValue);
        if (operand.equals("+")) {
            curentResult += resultNumbers;
        } else if (operand.equals("-")) {
            curentResult -= resultNumbers;
        } else if (operand.equals("*")) {
            curentResult *= resultNumbers;
        } else if (operand.equals("/")) {
            curentResult /= resultNumbers;
        } else if (operand.equals("")) {
            curentResult = resultNumbers;
        }
        String oldHistory = txt_result2.getText().toString();
        txt_result2.setText(oldHistory + " " + resultNumbers + " " + nextOperand);
        txt_result.setText("" + curentResult);
        operand = nextOperand;
        mustReset = true;
    }

    public void processEqual() {
        compute("");
        txt_result2.setText("");
        operand = "";
    }

    public void doOprand(String nextOperand) {
        compute(nextOperand);
    }

    @SuppressLint("SetTextI18n")
    public void appendNumber(int num) {
        if (mustReset) {
            txt_result.setText("");
            mustReset = false;
        }
        String oldValue = txt_result.getText().toString();

        if (oldValue.length() > 10) {
            return;
        }
        if (oldValue.equals("0")) {
            if (num == 0) {
                return;
            } else {
                oldValue = "";
            }
        }
        txt_result.setText(oldValue + num);
    }

    public void backOneLetter() {
        String value = txt_result.getText().toString();
        if (value.length() != 0) {
            String back = value.substring(0, value.length() - 1);
            txt_result.setText(back);
            if (back.length() == 0) {
                txt_result.setText("0");
            }
        }
    }

    @SuppressLint("SetTextI18n")
    public void addPoint() {
        if (mustReset) {
            txt_result.setText("0");
            mustReset = false;
        }
        String oldValue = txt_result.getText().toString();
        if (oldValue.contains(".")) {
            return;
        }

        if (oldValue.length() > 14) {
            return;
        }
        txt_result.setText(oldValue + ".");
    }

    public void toggleSing() {
        String value = txt_result.getText().toString();
        if (value.contains("-")) {
            value = value.replace("-", "");
        } else {
            value = "-" + value;
        }
        txt_result.setText(value);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_division = (Button) findViewById(R.id.btn_division);
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        btn_clearAll = (Button) findViewById(R.id.btn_clearAll);
        btn_multiply = (Button) findViewById(R.id.btn_multiply);
        btn_minus = (Button) findViewById(R.id.btn_minus);
        btn_plus = (Button) findViewById(R.id.btn_plus);
        btn_dot = (Button) findViewById(R.id.btn_dot);
        btn_equal = (Button) findViewById(R.id.btn_equal);
        btn_sign = (Button) findViewById(R.id.btn_sign);
        txt_result = (TextView) findViewById(R.id.txt_result);
        txt_result2 = (TextView) findViewById(R.id.txt_result2);


        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = Integer.parseInt(view.getTag().toString());
                appendNumber(index);
            }
        };

        btn_0.setOnClickListener(numberListener);
        btn_1.setOnClickListener(numberListener);
        btn_2.setOnClickListener(numberListener);
        btn_3.setOnClickListener(numberListener);
        btn_4.setOnClickListener(numberListener);
        btn_5.setOnClickListener(numberListener);
        btn_6.setOnClickListener(numberListener);
        btn_7.setOnClickListener(numberListener);
        btn_8.setOnClickListener(numberListener);
        btn_9.setOnClickListener(numberListener);

        View.OnClickListener operandListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doOprand(view.getTag().toString());
            }
        };

        btn_plus.setOnClickListener(operandListener);
        btn_minus.setOnClickListener(operandListener);
        btn_division.setOnClickListener(operandListener);
        btn_multiply.setOnClickListener(operandListener);

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_result.setText("0");
            }
        });

        btn_clearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_result.setText("0");
                txt_result2.setText("");
                curentResult = 0;
                mustReset = false;
            }
        });

        btn_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processEqual();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backOneLetter();
            }
        });

        btn_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPoint();
            }
        });

        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSing();
            }
        });

    }
}
