package com.arghya.expensetrackerapp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class ShowAllData extends AppCompatActivity {

    TextView showAllDataTitle;
    ListView listView;
    DatabaseHelper dbHelper;

    ArrayList<HashMap<String, String>> arrayList;
    HashMap<String, String> hashMap;

    public static boolean Expense = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_all_data);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        showAllDataTitle = findViewById(R.id.showAllDataTitle);
        listView = findViewById(R.id.listView);
        dbHelper = new DatabaseHelper(this);


        if (Expense) {
            showAllDataTitle.setText("Expense Overview");
        } else {
            showAllDataTitle.setText("Income Overview");

        }

        loadData();
    }

    public void loadData() {
        Cursor cursor = null;

        if (Expense) {
            cursor = dbHelper.getAllExpenseData();
        } else {
            cursor = dbHelper.getAllIncomeData();
        }

        if (cursor != null && cursor.getCount() > 0) {
            arrayList = new ArrayList<>();

            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                double amount = cursor.getDouble(1);
                String reason = cursor.getString(2);
                double time = cursor.getDouble(3);

                hashMap = new HashMap<>();
                hashMap.put("id", "" + id);
                hashMap.put("amount", "" + amount);
                hashMap.put("reason", "" + reason);
                hashMap.put("time", "" + time);
                arrayList.add(hashMap);
            }

            MyAdapter myAdapter = new MyAdapter();
            listView.setAdapter(myAdapter);

        } else {
            showAllDataTitle.append("\n\n No Data Found");
        }


    }


    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();

            View view;

            if (convertView == null) {
                if (Expense) {
                    view = inflater.inflate(R.layout.expense_card_ui, null);
                } else {
                    view = inflater.inflate(R.layout.income_card_ui, null);
                }
            } else {
                view = convertView;
            }


            TextView spendingTitle, spendingAmount, timeAndDate;

            spendingTitle = view.findViewById(R.id.spendingTitle);
            spendingAmount = view.findViewById(R.id.spendingAmount);
            timeAndDate = view.findViewById(R.id.timeAndDate);

            hashMap = arrayList.get(position);
            String id = hashMap.get("id");
            String amount = hashMap.get("amount");
            String reason = hashMap.get("reason");
            double time = Double.parseDouble(hashMap.get("time"));


            spendingTitle.setText(reason);
            if (Expense){
                spendingAmount.setText("-"+ amount);

            }else {
                spendingAmount.setText("+"+ amount);
            }

            // Convert timestamp to human-readable date and time
            Date date = new Date((long) time);
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy , hh:mm a", Locale.getDefault());
            String formattedDate = sdf.format(date);

            // Display formatted date and time
            timeAndDate.setText(formattedDate);

            return view;
        }
    }


}