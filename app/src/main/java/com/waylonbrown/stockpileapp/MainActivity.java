package com.waylonbrown.stockpileapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.waylonbrown.stockpile.StockPile;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //needs to be called once before using the library
        StockPile.initialize(this);

        //storing various types of values
        StockPile.store("key1", "value");
        StockPile.store("key2", true);
        StockPile.store("key3", 10.5f, true); //optionally add the third parameter to flag whether to store asynchronously (faster) or not
        boolean success = StockPile.store("key4", 5);   //if not storing asynchronously, it returns whether the storage was successful or not, just like SharedPrefs
        StockPile.store("key5", 2453L);

        String stringVal = StockPile.getString("key1", "");
        boolean boolVal = StockPile.getBoolean("key2"); //2nd parameter (default value) can be left out
        float floatVal = StockPile.getFloat("key3", 0F);
        int intVal = StockPile.getInt("key4", 0);
        long longVal = StockPile.getLong("key5", 0L);

        List<String> purchases = new ArrayList<>();
        purchases.add("purchase1");
        purchases.add("purchase2");
        purchases.add("purchase3");
        purchases.add("purchase4");
        purchases.add("purchase5");
        CustomerObject customer = new CustomerObject("Example Name", 10, 25, purchases);

        StockPile.store("key6", customer);
        CustomerObject storedCustomer = (CustomerObject)StockPile.getObject("key6", CustomerObject.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
