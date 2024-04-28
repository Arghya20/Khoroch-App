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

    TextView addTextTV;
    TextInputEditText amountEditText, reasonEditText;
    TextInputLayout amountInputLayout;
    AppCompatButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.add_amount);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        addTextTV = findViewById(R.id.addTextTV);
        amountEditText = findViewById(R.id.amountEditText);
        amountInputLayout = findViewById(R.id.amountInputLayout);
        reasonEditText = findViewById(R.id.reasonEditText);
        btnAdd = findViewById(R.id.btnAdd);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the values of amount and reason directly
                String amount = amountEditText.getText().toString();
                String reason = reasonEditText.getText().toString();


                if (!amount.isEmpty()) {
                    amountInputLayout.setError(null);


                    // Vibrate the phone
                    Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                    if (vibrator != null) {
                        vibrator.vibrate(20); // Vibrate for 100 milliseconds
                    }

                    // Show toast with amount and reason
                    Toast.makeText(Add_Amount.this, " Amount is - " + amount + " & reason is - " + reason, Toast.LENGTH_SHORT)
                            .show();

                    amountEditText.setText(null);
                   reasonEditText.setText(null);

                } else {
                    amountInputLayout.setError("Enter Amount");

                }


            }
        });


    }
}