package com.arghya.expensetrackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView totalBalance, totalIncome, totalExpense;
    RelativeLayout showAllIncome, showAllExpense;
    LinearLayout addIncome, addExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalBalance = findViewById(R.id.totalBalance);
        totalIncome = findViewById(R.id.totalIncome);
        totalExpense = findViewById(R.id.totalExpense);
        showAllIncome = findViewById(R.id.showAllIncome);
        showAllExpense = findViewById(R.id.showAllExpense);
        addIncome = findViewById(R.id.addIncome);
        addExpense = findViewById(R.id.addExpense);

        addIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Add_Amount.class));
            }
        });

        showAllIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShowAllData.class));
            }
        });

    }


}