package com.example.ass45;
import java.text.DecimalFormat;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;


import com.example.ass45.databinding.ActivityMainBinding;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityMainBinding binding;
    private static final String DB_NAME = "MealsDB";
    private DBHelper dbHelper;
    private Double setPrice;
    private int qty;
    private Double total;
    private String choice;
    private String temp;
    private boolean radioIsChecked=false;
    private int img_src;
    private SQLiteDatabase sqLiteDatabase;
    private static final DecimalFormat df= new DecimalFormat("0.00");
    private List<Order> new_order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        sqLiteDatabase = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        dbHelper = new DBHelper(this);
        new_order = OrderList.getInstance();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.meal, android.R.layout.simple_dropdown_item_1line);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        binding.spinner.setAdapter(adapter);
        binding.btnOrder.setOnClickListener(this);
        binding.tvDisplay.setOnClickListener(this);
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choice = parent.getItemAtPosition(position).toString();
                switch(choice){
                    case "--select meals--":
                        setPrice=0.0;
                        binding.mealPrice.setText("$" + setPrice);
                        binding.imageView2.setImageResource(R.drawable.white);
                        binding.totalPrice.setText("$ " + 0.0);
                        img_src=R.drawable.white;
                        break;
                    case "Chicken Curry":
                        setPrice = 16.99;
                        binding.mealPrice.setText("$" + setPrice);
                        binding.imageView2.setImageResource(R.drawable.chicken_curry);
                        img_src=R.drawable.chicken_curry;
                        break;
                    case "Chicken Wings":
                        setPrice = 10.00;
                        binding.mealPrice.setText("$" + setPrice);
                        binding.imageView2.setImageResource(R.drawable.chicken_wings);
                        img_src=R.drawable.chicken_wings;
                        break;
                    case "Chicken Biryani":
                        setPrice = 20.50;
                        binding.mealPrice.setText("$" + setPrice);
                        binding.imageView2.setImageResource(R.drawable.chickenbiryani);
                        img_src=R.drawable.chickenbiryani;
                        break;
                    case "Burger":
                        setPrice = 8.99;
                        binding.mealPrice.setText("$" + setPrice);
                        binding.imageView2.setImageResource(R.drawable.burger);
                        img_src=R.drawable.burger;
                        break;
                    case "Fried Calamaries":
                        setPrice = 12.60;
                        binding.mealPrice.setText("$" + setPrice);
                        binding.imageView2.setImageResource(R.drawable.fried_calamarie);
                        img_src=R.drawable.fried_calamarie;
                        break;
                    case "Paneer Tikka":
                        setPrice = 17.30;
                        binding.mealPrice.setText("$" + setPrice);
                        binding.imageView2.setImageResource(R.drawable.paneer_tikka);
                        img_src=R.drawable.paneer_tikka;
                        break;
                    case "Pasta":
                        setPrice = 9.99;
                        binding.mealPrice.setText("$" + setPrice);
                        binding.imageView2.setImageResource(R.drawable.pasta);
                        img_src=R.drawable.pasta;
                        break;
                    case "Pizza":
                        setPrice = 19.90;
                        binding.mealPrice.setText("$" + setPrice);
                        binding.imageView2.setImageResource(R.drawable.pizza);
                        img_src=R.drawable.pizza;
                        break;
                    case "Poutine":
                        setPrice = 7.88;
                        binding.mealPrice.setText("$" + setPrice);
                        binding.imageView2.setImageResource(R.drawable.poutine);
                        img_src=R.drawable.poutine;
                        break;
                    case "Sandwitch":
                        setPrice = 8.99;
                        binding.mealPrice.setText("$" + setPrice);
                        binding.imageView2.setImageResource(R.drawable.sandwitch);
                        img_src=R.drawable.sandwitch;
                        break;

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                qty = progress;
                binding.quantity.setText(String.valueOf(progress));
               // Toast.makeText(MainActivity.this, progress, Toast.LENGTH_SHORT).show()
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        binding.radioGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioIsChecked=true;
                RadioButton rb=(RadioButton)findViewById(checkedId);
                temp=rb.getText().toString();
                Double tax = (setPrice * qty) * 0.13;
                if(rb.getText().equals("10%")){
                    Double tip = (setPrice * qty) * 0.10;
                    total = (setPrice * qty) + tax + tip;
                    binding.totalPrice.setText("$ " + df.format(total));
                }
                else if(rb.getText().equals("15%")){
                    Double tip = (setPrice * qty) * 0.15;
                    total = (setPrice * qty) + tax + tip;
                    binding.totalPrice.setText("$ " + df.format(total));
                }
                else if(rb.getText().equals("20%")){
                    Double tip = (setPrice * qty) * 0.20;
                    total = (setPrice * qty) + tax + tip;
                    binding.totalPrice.setText("$ " + df.format(total));
                }
            }
        });
    }
    private void addOrder(){
        double t_price=Double.parseDouble(df.format(total));
        String choice1=choice;
        Double setp=setPrice;
        int q=qty;
        String tip=temp;
        Order order=new Order(choice1,setp,q,tip,Double.parseDouble(df.format(total)),img_src);
        if(dbHelper.AddOrder(order)){
            Toast.makeText(this, "Your Order has been added", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Error Adding your Order", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_order) {
            if(choice.equals("--select meals--")){
                Toast.makeText(MainActivity.this, "Please Select Meal", Toast.LENGTH_SHORT).show();
            }
            else if(qty==0){
                Toast.makeText(MainActivity.this, "Please Select Quantity", Toast.LENGTH_SHORT).show();
            }
            else if(!radioIsChecked){
                Toast.makeText(this, "Please Check tip ", Toast.LENGTH_SHORT).show();
            }
            else if(binding.checkBox.isChecked()){
                addOrder();
                binding.mealPrice.setText("$" + 0.0);
                binding.imageView2.setImageResource(R.drawable.white);
                binding.totalPrice.setText("$ " + 0.0);
            }
            else{
                Toast.makeText(MainActivity.this, "Please Check Checkbox", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.tv_display) {
            Intent intent = new Intent(this, OrderActivity.class);
            startActivity(intent);
        }
    }
}