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
    DatabaseHelper dbHelper;

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
        dbHelper = new DatabaseHelper(this);

        addIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add_Amount.isExpense = false;
                startActivity(new Intent(MainActivity.this, Add_Amount.class));
            }
        });

        showAllIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAllData.Expense = false;
                startActivity(new Intent(MainActivity.this, ShowAllData.class));
            }
        });

        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add_Amount.isExpense = true;
                startActivity(new Intent(MainActivity.this, Add_Amount.class));
            }
        });

        showAllExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAllData.Expense = true;
                startActivity(new Intent(MainActivity.this, ShowAllData.class));
            }
        });

        TotalIncome();
        TotalExpense();
        TotalBalance();

    }


    public void TotalIncome() {
        totalIncome.setText("₹" + Math.round(dbHelper.getTotalIncome()));
    }

    public void TotalExpense() {
        totalExpense.setText("₹" + Math.round(dbHelper.getTotalExpense()));
    }

    public void TotalBalance() {
        double totalAmount = dbHelper.getTotalIncome() - dbHelper.getTotalExpense();
        totalBalance.setText("₹" + Math.round(totalAmount));
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        TotalIncome();
        TotalExpense();
        TotalBalance();
    }
}