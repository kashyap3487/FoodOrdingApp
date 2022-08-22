package com.example.ass45;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.ass45.databinding.ActivityOrderBinding;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private List<Order> orderList;
    private ActivityOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        dbHelper = new DBHelper(this);
        loadOrders();
    }

    private void loadOrders() {
        orderList=new ArrayList<>();
        Cursor cursor = dbHelper.getAllOrders();
        if (cursor.moveToFirst()) {
            do {
                // fetch items of each column
                Log.d("DEBUG", "loadEmployees: " + cursor.getString(1));
                orderList.add(
                        new Order(
                                cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getDouble(2),
                                cursor.getInt(5),
                                cursor.getString(6),
                                cursor.getDouble(4),
                                cursor.getInt(3)
                        )
                );
            } while (cursor.moveToNext());
            cursor.close();
            OrderAdapter adapter = new OrderAdapter(this,
                    R.layout.list_order_layout,
                    orderList,
                    dbHelper);
            binding.lvOrders.setAdapter(adapter);


        }
    }
}