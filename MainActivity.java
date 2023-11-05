package com.example.currencyconvertorbidirectional;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Spinner dropDown;
    private EditText textBox;
    private Button btnConvert;
    private TextView result;
    private String selectedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dropDown = (Spinner) findViewById(R.id.currencySpinner);
        textBox = (EditText) findViewById(R.id.inputValue);
        btnConvert = (Button) findViewById(R.id.btnConvert);
        result = (TextView) findViewById(R.id.result);

        // Create an ArrayAdapter using a string array and a default spinner layout



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.currency_array, android.R.layout.simple_spinner_item);
        
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dropDown.setAdapter(adapter);

        dropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textBox.setText(""); // delete the entry kept in textBox
                result.setText(""); // delete previous response
                selectedText = parent.getItemAtPosition(position).toString();
                //Toast.makeText(MainActivity.this, "Selected: " + "'"+ selectedText+"'", Toast.LENGTH_SHORT).show();
                //result.setText(selectedText);
                if (selectedText.equals("Pounds to Dollars")){
                    textBox.setHint("Enter currency in Pounds");
                }else{
                    textBox.setHint("Enter currency in Dollars");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case when nothing is selected
            }
        });

        btnConvert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                try {
                    double input = Double.parseDouble(String.valueOf(textBox.getText()));
                    if(selectedText.equals("Pounds to Dollars")){
                        result.setText("The currency in Dollars: $"+Double.toString(input*1.22));
                    }else{
                        result.setText("The currency in Pounds: "+Double.toString(input/1.22)+" Pounds");
                    }
                }catch (Exception e){
                    result.setText("Invalid input");
                }

            }
        });
    }
}