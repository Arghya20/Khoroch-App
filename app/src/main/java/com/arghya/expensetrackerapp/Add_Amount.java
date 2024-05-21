package com.arghya.expensetrackerapp;

import android.os.Bundle;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import kotlin.jvm.internal.markers.KMutableSet;

public class Add_Amount extends AppCompatActivity {


    TextView addTextTv;
    TextInputEditText amountEditText, reasonEditText;
    TextInputLayout amountInputLayout;
    AppCompatButton btnAdd;
    DatabaseHelper dbHelper;

    public static boolean isExpense = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.add_amount);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        addTextTv = findViewById(R.id.addTextTV);
        amountEditText = findViewById(R.id.amountEditText);
        amountInputLayout = findViewById(R.id.amountInputLayout);
        reasonEditText = findViewById(R.id.reasonEditText);
        btnAdd = findViewById(R.id.btnAdd);
        dbHelper = new DatabaseHelper(this);


        if (isExpense) {
            addTextTv.setText("Add Your Expense");
            btnAdd.setText("Add Expense");
        } else {
            addTextTv.setText("Add Your Income");
            btnAdd.setText("Add Income");

        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the values of amount and reason directly
                String amount = amountEditText.getText().toString();
                String reason = reasonEditText.getText().toString();


                if (!amount.isEmpty()) {
                    try {
                        double doubleAmount = Double.parseDouble(amount);
                        amountInputLayout.setError(null);

                        if (isExpense) {
                            dbHelper.addExpense(doubleAmount, reason);
                        } else {
                            dbHelper.addIncome(doubleAmount, reason);
                        }

                        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                        if (vibrator != null) {
                            vibrator.vibrate(20);
                        }

                        Toast.makeText(Add_Amount.this, amount+" Added ", Toast.LENGTH_SHORT)
                                .show();

                        amountEditText.setText("");
                        reasonEditText.setText("");
                    } catch (NumberFormatException e) {
                        Toast.makeText(Add_Amount.this, "Invalid amount format", Toast.LENGTH_SHORT)
                                .show();
                    }
                } else {
                    amountInputLayout.setError("Enter Amount");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}