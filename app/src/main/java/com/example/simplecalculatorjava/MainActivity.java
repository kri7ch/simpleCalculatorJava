package com.example.simplecalculatorjava;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText display_input;
    private TextView firstValue;
    private boolean isEql = false;
    private double operand1;
    private double operand2;
    private char operator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        display_input = findViewById(R.id.display_input);
        firstValue = findViewById(R.id.firstValue);
    }

    public void click_Num(View view){
        isEql = false;
        if(display_input.getText().toString().equals("0")){
            display_input.setText("");
        }
        display_input.append(((Button) view).getText());
    }

    @SuppressLint("SetTextI18n")
    public void clickOperation(View view){
        if (display_input.getText().toString().isEmpty()){
            return;
        }
        if(!Double.isNaN(operand1)){
            click_result(view);
        }
        operand1 = Double.parseDouble(display_input.getText().toString());
        operator = ((Button) view).getText().charAt(0);
        firstValue.setText(formatNumber(operand1)+ " " + operator);
        display_input.setText("");
    }

    @SuppressLint("SetTextI18n")
    public void clickDot(View view){

        if(display_input.getText().toString().contains(".")){
            return;
        }
        if(display_input.getText().toString().isEmpty()){
            display_input.setText("0");
        }
        display_input.setText(display_input.getText().toString()+".");
    }

    public void click_C(View view){
        display_input.setText("0");
        operand1 = Double.NaN;
        firstValue.setText("");
        operand2 = 0;
        operator = ' ';
    }

    public void click_Ce(View view) {
        if (Double.isNaN(operand1)) {
            return;
        }
        String currentText = display_input.getText().toString();
        if (!currentText.isEmpty()) {
            display_input.setText(currentText.substring(0, currentText.length() - 1));
        }
        if (display_input.getText().toString().isEmpty()) {
            display_input.setText("0");
        }
    }


    private String formatNumber(double number) {
        if (number == (int) number) {
            return String.valueOf((int) number);
        }
        return String.valueOf(number);
    }

    @SuppressLint("SetTextI18n")
    public void click_result(View view) {
        if (Double.isNaN(operand1) || display_input.getText().toString().isEmpty()) {
            return;
        }

        if (!isEql) {
            operand2 = Double.parseDouble(display_input.getText().toString());
        }
        isEql = true;

        switch (operator) {
            case '+':
                display_input.setText(formatNumber(operand1 + operand2));
                break;
            case '-':
                display_input.setText(formatNumber(operand1 - operand2));
                break;
            case '*':
                display_input.setText(formatNumber(operand1 * operand2));
                break;
            case '/':
                if (operand2 == 0) {
                    display_input.setText("0");
                } else {
                    display_input.setText(formatNumber(operand1 / operand2));
                }
                break;
        }

        firstValue.setText(formatNumber(operand1) + " " + operator + " " + formatNumber(operand2));
        operand1 = Double.parseDouble(display_input.getText().toString());
    }

}