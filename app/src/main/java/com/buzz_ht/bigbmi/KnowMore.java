package com.buzz_ht.bigbmi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class KnowMore extends AppCompatActivity {

    TextView textviewknowmore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_know_more);
        init();

    }

    private void init() {
        textviewknowmore=findViewById(R.id.textviewknowmore);
    }
}