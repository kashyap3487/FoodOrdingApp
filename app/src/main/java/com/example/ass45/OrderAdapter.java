package com.example.ass45;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class OrderAdapter extends ArrayAdapter {
    private Context context;
    private int layoutRes;
    private List<Order> orderList;
    private SQLiteDatabase sqLiteDatabase;
    private DBHelper dbHelper;
    public OrderAdapter(@NonNull Context context, int resource, @NonNull List<Order> orderList, DBHelper dbHelper) {
        super(context, resource,orderList);
        this.context = context;
        this.layoutRes = resource;
        this.orderList =orderList;
        this.dbHelper = dbHelper;
    }
    @Override
    public int getCount() {
        return orderList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = convertView;
        if (v == null) v = inflater.inflate(layoutRes, null);
        TextView nameMeal = v.findViewById(R.id.meal_name);
        TextView totalPrice = v.findViewById(R.id.total_price);
        TextView qty= v.findViewById(R.id.qty);
        TextView tip = v.findViewById(R.id.row_tip);
        TextView price = v.findViewById(R.id.row_price);
        ImageView img=v.findViewById(R.id.img3);

        nameMeal.setText(orderList.get(position).getMeals());
        totalPrice.setText(String.valueOf(orderList.get(position).getTotal_price()));
        qty.setText(String.valueOf(orderList.get(position).getQty()));
        tip.setText(orderList.get(position).getTip());
        price.setText(String.valueOf(orderList.get(position).getPrice()));
        int img_src=orderList.get(position).getImg();
        img.setImageResource(img_src);

        v.findViewById(R.id.btn_delete).setOnClickListener(view -> {
            dbHelper.deleteOrder(orderList.get(position).getId());
            orderList.remove(position);
            notifyDataSetChanged();
        });

        return v;
    }

}
