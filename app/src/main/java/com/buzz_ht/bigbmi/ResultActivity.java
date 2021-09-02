package com.buzz_ht.bigbmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

public class ResultActivity extends AppCompatActivity {


    TextView textviewresulttitle,textviewresultbmi,textviewresultltinformation,textviewrange,textviewcatogery;
    String BmiS,lowerlimitS,upperlimitS;
    MaterialCardView cardview;
    Button buttonknowmore;
    private final int ORANGE= 0xFF9933;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        init();
        if(savedInstanceState==null){
            Bundle bundle= getIntent().getExtras();
            if(bundle==null){
                BmiS=null;
            }else{
                BmiS=bundle.getString("bmi");
                upperlimitS=bundle.getString("upperlimit");
                lowerlimitS=bundle.getString("lowerlimit");
                calculate();
                textviewrange.setText("Depending on your body type your ideal weight range for the current height is " + lowerlimitS +" - " + upperlimitS);
            }
        }else {
            BmiS=(String) savedInstanceState.getSerializable("bmi");
        }

        buttonknowmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3= new Intent(ResultActivity.this,KnowMore.class);
                startActivity(intent3);
            }
        });

        }

    private void calculate() {
        double d= Double.parseDouble(BmiS);

        if(d<18.5){
            textviewresultbmi.setText(BmiS);
           // textviewresultbmi.setBackgroundColor(Color.MAGENTA);
            textviewcatogery.setText("Underweight");
        }

        if(d>=18.5 && d<= 25){
            textviewresultbmi.setText(BmiS);
           // textviewresultbmi.setBackgroundColor(Color.GREEN);
            textviewcatogery.setText("Healthy Weight");
        }

        if(d>25 && d<=30){
            textviewresultbmi.setText(BmiS);
            //textviewresultbmi.setBackgroundColor(Color.MAGENTA);
            textviewcatogery.setText("Overweight");
        }

        if(d>30){
            textviewresultbmi.setText(BmiS);
            //textviewresultbmi.setBackgroundColor(Color.RED);
            textviewcatogery.setText("Obese");
        }




    }


    private void init() {

        textviewresulttitle = findViewById(R.id.textviewresulttitle);
        textviewresultbmi=findViewById(R.id.textviewresultbmi);
        textviewresultltinformation =findViewById(R.id.textviewresultinformation);
        cardview=findViewById(R.id.cardview);
        textviewcatogery=findViewById(R.id.textviewcatogery);
        textviewrange=findViewById(R.id.textviewrange);
        buttonknowmore=findViewById(R.id.buttonknowmore);


    }
}