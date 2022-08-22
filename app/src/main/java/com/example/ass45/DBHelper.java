package com.example.ass45;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper  extends SQLiteOpenHelper {
    private static final String DB_NAME = "MealsDB";
    private static final String TABLE_NAME = "Meals";
    private static final int VERSION = 1;

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_TOTAL_PRICE = "total_price";
    private static final String COLUMN_IMAGE_SRC = "img_src";
    private static final String COLUMN_QUANTITY = "quantity";
    private static final String COLUMN_TIP_VALUE = "tip_value";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }
    private String TAG = "DEBUG";

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create tables
        Log.d(TAG, "onCreate: Creating Database");

        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +  " (" +
                COLUMN_ID + " INTEGER NOT NULL CONSTRAINT meals_pk PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " VARCHAR(50) NOT NULL, " +
                COLUMN_PRICE + " DOUBLE(10) NOT NULL, " +
                COLUMN_IMAGE_SRC + " VARCHAR(100) NOT NULL, " +
                COLUMN_TOTAL_PRICE + " DOUBLE(10) NOT NULL, " +
                COLUMN_QUANTITY + " INTEGER(10) NOT NULL, " +
                COLUMN_TIP_VALUE + " STRING(10) NOT NULL)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        db.execSQL(sql);
        onCreate(db);
    }

    // add Employee
    public Boolean AddOrder(Order order){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, order.getMeals());
        cv.put(COLUMN_PRICE, order.getPrice());
        cv.put(COLUMN_IMAGE_SRC, order.getImg());
        cv.put(COLUMN_TOTAL_PRICE, order.getTotal_price());
        cv.put(COLUMN_QUANTITY, order.getQty());
        cv.put(COLUMN_TIP_VALUE, order.getTip());
        return db.insert(TABLE_NAME, null, cv) != -1;
    }

    // delete Employee
    public boolean deleteOrder(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_ID+"=?", new String[]{String.valueOf(id)}) > 0;
    }

    // get employees
    public Cursor getAllOrders() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + ";";
        return db.rawQuery(sql, null);
    }

}
