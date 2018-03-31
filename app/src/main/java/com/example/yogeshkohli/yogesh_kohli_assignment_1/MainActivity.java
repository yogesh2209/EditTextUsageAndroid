package com.example.yogeshkohli.yogesh_kohli_assignment_1;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;
import android.view.View.OnTouchListener;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    //ON START METHOD
    @Override
    protected void onStart() {
        super.onStart();
        setupTouchListenerForINREditText();
        setupTouchListenerForUSDEditText();
    }

    //Convert Button Action
    public void convertButtonClicked(View button) {
       checkingEditText();
    }
    //Toast Method
    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    //Get EditText Values
    public String getUSDEditText(){
        EditText usdEditText = (EditText) findViewById(R.id.usdEditText);
        String  usdString = usdEditText.getText().toString().trim();
        return usdString;
    }
    public String getINREditText(){
        EditText inrEditText = (EditText) findViewById(R.id.inrEditText);
        String  inrString = inrEditText.getText().toString().trim();
        return inrString;
    }
    //checking validation of edit text
    public void checkingEditText() {
        if (getUSDEditText().length() == 0 && getINREditText().trim().length() == 0) {
            showToast("Please enter any one amount!");
        }
        else if (getUSDEditText().length() != 0 && getINREditText().length() != 0) {
            showToast("Please enter only one amount!");
        }
        else if (getUSDEditText().matches(".") || getINREditText().matches(".")) {
            showToast("Please enter valid amount!");
        }
        else{
             convertCurrency(getUSDEditText(),getINREditText());
        }
    }
    //To convert usd to inr
    public String usdToInr(String usd) {
        double oneUSD = 63.89;
        double usdAmount = Double.parseDouble(usd);
        return String.format("%.2f", usdAmount*oneUSD);
    }
    //To convert inr to usd
    public String inrToUsd(String inr) {
        double oneINR = 0.016;
        double inrAmount = Double.parseDouble(inr);
        return String.format("%.2f", inrAmount*oneINR);
    }
    //Converting and displaying it on screen
    public void convertCurrency(String usd, String inr) {
        String finalAmount = "";
        //User has entered USD, so i need to show answer in INR
        if (usd.trim().length() != 0) {
            finalAmount = usdToInr(usd);
            //update UI
            EditText inrEditText = (EditText) findViewById(R.id.inrEditText);
            inrEditText.setText(finalAmount, TextView.BufferType.EDITABLE);
        }
        //User has entered INR, so answer should be in USD
        else{
            finalAmount = inrToUsd(inr);
            EditText usdEditText = (EditText) findViewById(R.id.usdEditText);
            usdEditText.setText(finalAmount, TextView.BufferType.EDITABLE);
        }
    }
    //TouchListener Event
    @SuppressLint("ClickableViewAccessibility")
    private void setupTouchListenerForUSDEditText() {
        final EditText usdEditText = (EditText) findViewById(R.id.usdEditText);
        usdEditText.setOnTouchListener(new OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event){
                EditText inrEditText = (EditText) findViewById(R.id.inrEditText);
                String  inrString = inrEditText.getText().toString();
                if (!inrString.matches("")){
                    ((EditText) findViewById(R.id.inrEditText)).getText().clear();
                }
                return false;
            }
        });
    }
    @SuppressLint("ClickableViewAccessibility")
    private void setupTouchListenerForINREditText() {
        final EditText inrEditText = (EditText) findViewById(R.id.inrEditText);
        inrEditText.setOnTouchListener(new OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event){
                EditText usdEditText = (EditText) findViewById(R.id.usdEditText);
                String  inrString = usdEditText.getText().toString();
                if (!inrString.matches("")){
                    ((EditText) findViewById(R.id.usdEditText)).getText().clear();
                }
                return false;
            }
        });
    }
}
