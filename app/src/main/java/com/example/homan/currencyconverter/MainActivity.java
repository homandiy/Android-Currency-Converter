package com.example.homan.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //convert user input with amount and currency options
    public void convertMoney(View view) {
        EditText inputAmt = (EditText) findViewById(R.id.inputMoney);
        String money = inputAmt.getText().toString();
        TextView txtConvert = (TextView) findViewById(R.id.convertedResult);

        //initial all counting data to 0
        double digitMoney = 0, inputRate = 0, outputRate = 0, convertAmt = 0;

        if (money.matches("") ) {
            Toast.makeText(this, "Please enter amount of currency.", Toast.LENGTH_SHORT).show();
            return;
        } else {
            digitMoney = Double.parseDouble(money);
        }

        //abstract input currency option
        Spinner iSpinner = (Spinner) findViewById(R.id.pickCurrency1);
        String inputCurrency = iSpinner.getSelectedItem().toString();

        //abstract output currency option
        Spinner oSpinner = (Spinner) findViewById(R.id.pickCurrency2);
        String outputCurrency = oSpinner.getSelectedItem().toString();

        //deal with the input currency with value
        if (inputCurrency.matches("Select One")) {
            Toast.makeText(this, "Please pick an input currency.", Toast.LENGTH_SHORT).show();
            return;
        } else {
            String[] token = inputCurrency.split(" : ");
            inputRate = Double.parseDouble(token[1]);
            Log.i("Input Rate: ", String.valueOf(inputRate));
        }

        //deal with the output curreny with value
        if (outputCurrency.matches("Select One")) {
            Toast.makeText(this, "Please pick an output currency.", Toast.LENGTH_SHORT).show();
            return;
        } else {
            String[] token = outputCurrency.split(" : ");
            outputRate = Double.parseDouble(token[1]);
            Log.i("Output Rate: ", String.valueOf(outputRate));
        }

        if (inputCurrency.matches(outputCurrency)) {
            Toast.makeText(this, "Please pick different type of currency to convert!", Toast.LENGTH_SHORT).show();
            return;
        }


        Log.i("amount: ", money);
        if (digitMoney != 0 && inputRate != 0 && outputRate != 0) {
            convertAmt = digitMoney * outputRate / inputRate;

            //keep two decimal places
            txtConvert.setText(String.valueOf(String.format("%.2f",convertAmt) ) );
        }
    }

    //clear history
    public void clearInput(View view) {
        EditText inputAmt = (EditText) findViewById(R.id.inputMoney);
        TextView txtConvert = (TextView) findViewById(R.id.convertedResult);

        //abstract input currency option
        Spinner iSpinner = (Spinner) findViewById(R.id.pickCurrency1);

        //abstract output currency option
        Spinner oSpinner = (Spinner) findViewById(R.id.pickCurrency2);

        //clear everything
        iSpinner.setSelection(0);
        oSpinner.setSelection(0);
        inputAmt.setText("");
        txtConvert.setText("");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
