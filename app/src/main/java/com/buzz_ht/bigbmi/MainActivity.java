package com.buzz_ht.bigbmi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{


    TextView textWarningWeight,textWarningHeight;
    EditText editTextWeight,editTextHeight,editTextInches;
    Spinner spinnerWeight,spinnerHeight;
    Button buttonCalculate,buttonknowmore;
    String editTextWeightS,editTextHeightS,editTextInchesS;
    double editTextWeightD,editTextHeightD,editTextHeightID,Bmi,upperlimit,lowerlimit;
    int posWeight,posHeight;
    String BmiS,lowerlimitS,upperlimitS;
   // BottomNavigationView bottomnavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setSpinner();

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBmi();

            }
        });

        buttonknowmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this,KnowMore.class);
                startActivity(intent2);
            }
        });
    }

    private void setSpinner() {

        ArrayList<String> listWeight = new ArrayList<>();
        listWeight.add("kg");
        listWeight.add("lbs");

        ArrayAdapter<String> adapterWeight = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,listWeight);
        spinnerWeight.setAdapter(adapterWeight);

        ArrayList<String> listHeight = new ArrayList<>();
        listHeight.add("cm");
        listHeight.add("feet");

        ArrayAdapter<String> adapterHeight = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,listHeight);
        spinnerHeight.setAdapter(adapterHeight);


        spinnerHeight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){

                    case 0:
                        editTextInches.setVisibility(View.INVISIBLE);
                        editTextHeight.setHint("cm");
                        posHeight=0;
                        break;
                    case 1:
                        editTextInches.setVisibility(View.VISIBLE);
                        editTextHeight.setHint("feet");
                        posHeight=1;
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerWeight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        editTextWeight.setHint("kg");
                        posWeight=0;
                        break;
                    case 1:
                        editTextWeight.setHint("lbs");
                        posWeight=1;
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
    private void init() {

        // bottomNavigationView= findViewById(R.id.bottom_navigation);
       // textWarningAge= findViewById(R.id.textwarningage);
        textWarningWeight= findViewById(R.id.textwarningweight);
        textWarningHeight= findViewById(R.id.textwarningheight);
       // textViewGender= findViewById(R.id.textviewgender);
      //  textViewAge= findViewById(R.id.textviewage);
       // textViewWeight= findViewById(R.id.textviewweight);
      //  textViewHeight= findViewById(R.id.textviewheight);
      //  imageView1= findViewById(R.id.imageview1);
      //  radioGroupGender= findViewById(R.id.radiogroupgender);
      //  editTextAge = findViewById(R.id.edittextage);
        editTextWeight = findViewById(R.id.edittextweight);
        editTextHeight = findViewById(R.id.edittextheight);
        editTextInches=findViewById(R.id.edittextinches);
        spinnerWeight= findViewById(R.id.spinnerweight);
        spinnerHeight= findViewById(R.id.spinnerheight);
        buttonCalculate=findViewById(R.id.buttoncalculate);
        buttonknowmore=findViewById(R.id.buttonknowmore);
    }
    private void calculateBmi() {

        if(checkValidity()){
            Bmi= editTextWeightD/(editTextHeightD*editTextHeightD);
            DecimalFormat myFormat = new DecimalFormat("0.0");
            BmiS = myFormat.format(Bmi);
            limit();
            Intent intent1= new Intent(MainActivity.this,ResultActivity.class);
            intent1.putExtra("bmi",BmiS);
            if(posWeight==0){
                intent1.putExtra("lowerlimit",lowerlimitS + " kg");
                intent1.putExtra("upperlimit",upperlimitS + " kg");
            }else {
                intent1.putExtra("lowerlimit",lowerlimitS + " pounds");
                intent1.putExtra("upperlimit",upperlimitS + " pounds");
            }
            startActivity(intent1);

        }
    }

    private void limit() {
        DecimalFormat myFormat = new DecimalFormat("0.0");
        lowerlimit=18.5*(editTextHeightD*editTextHeightD);
        upperlimit=24.9*(editTextHeightD*editTextHeightD);
        if (posWeight != 0) {
            lowerlimit = lowerlimit * 2.20;
            upperlimit = upperlimit * 2.20;
        }
        lowerlimitS=myFormat.format(lowerlimit) ;
        upperlimitS=myFormat.format(upperlimit);


    }

    private boolean checkValidity() {

        if(checkWeight() & checkHeight()) {
            return true;
        }
        return false;
    }
    boolean checkWeight(){
        if(editTextWeight.getText().toString().isEmpty()){
            textWarningWeight.setText("Weight cannot be blank");
           // textWarningWeight.setTextColor(Color.RED);
            textWarningWeight.setVisibility(View.VISIBLE);
        }else {
            editTextWeightS=editTextWeight.getText().toString();
            editTextWeightD=Double.parseDouble(editTextWeightS);
            System.out.println(editTextWeightD);
            if(editTextWeightD==0){
                textWarningWeight.setText("Weight cannot be Zero");
              //  textWarningWeight.setTextColor(Color.RED);
                textWarningWeight.setVisibility(View.VISIBLE);
                return false;
            }else {
                textWarningWeight.setVisibility(View.INVISIBLE);
                switch(posWeight){
                    case 0:
                        System.out.println(editTextWeightD);
                        return true;

                    case 1:
                        editTextWeightD=editTextWeightD*0.453;
                        System.out.println(editTextWeightD);
                        return true;
                }
            }
        }
        System.out.println(editTextWeightD);
        return false;
    }
    boolean checkHeight(){
        if(editTextHeight.getText().toString().isEmpty()){

            textWarningHeight.setText("Height cannot be blank");
           // textWarningHeight.setTextColor(Color.RED);
            textWarningHeight.setVisibility(View.VISIBLE);

        }else {
            editTextHeightS= editTextHeight.getText().toString();
            editTextHeightD=Double.parseDouble(editTextHeightS);
            if(editTextHeightD==0){
                textWarningHeight.setText("Height cannot be Zero");
               // textWarningHeight.setTextColor(Color.RED);
                textWarningHeight.setVisibility(View.VISIBLE);
                return false;
            }else {
                textWarningHeight.setVisibility(View.INVISIBLE);
                switch(posHeight){
                    case 0:
                        editTextHeightD=editTextHeightD/100;
                        System.out.println(editTextHeightD);
                        return true;
                    case 1:
                        if(editTextInches.getText().toString().isEmpty()){
                            editTextHeightID=0.00;
                        }else {
                            editTextInchesS=editTextInches.getText().toString();
                            editTextHeightID=Double.parseDouble(editTextInchesS);
                        }
                        editTextHeightID= (editTextHeightD*12)+editTextHeightID;
                        editTextHeightD=editTextHeightID*0.0254;
                        return true;

                }

            }

        }
        return false;
    }
}