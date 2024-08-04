package com.arghya.expensetrackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
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

    double prevTotalIncome = 0;
    double prevTotalExpense = 0;
    double prevTotalBalance = 0;

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
        double newTotalIncome = dbHelper.getTotalIncome();
        totalIncome.setText("₹" + Math.round(newTotalIncome));
        prevTotalIncome = newTotalIncome;
    }

    public void TotalExpense() {
        double newTotalExpense = dbHelper.getTotalExpense();
        totalExpense.setText("₹" + Math.round(newTotalExpense));
        prevTotalExpense = newTotalExpense;
    }

    public void TotalBalance() {
        double newTotalIncome = dbHelper.getTotalIncome();
        double newTotalExpense = dbHelper.getTotalExpense();
        double newTotalBalance = newTotalIncome - newTotalExpense;

        totalBalance.setText("₹" + Math.round(newTotalBalance));

        // Animate the balance change
        countAnim((int) prevTotalBalance, (int) newTotalBalance);

        // Update previous total balance
        prevTotalBalance = newTotalBalance;
    }

    private void countAnim(int fromValue, int toValue) {
        ValueAnimator animator = ValueAnimator.ofInt(fromValue, toValue);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                totalBalance.setText("₹" + animation.getAnimatedValue().toString());
            }
        });
        animator.setDuration(1000); // duration of the animation
        animator.start();
    }
    @Override
    protected void onResume() {
        super.onPostResume();
        TotalIncome();
        TotalExpense();
        TotalBalance();
    }
}