package com.waylonbrown.stockpileapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.waylonbrown.stockpile.StockPile;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //needs to be called before using StockPile
        StockPile.initialize(this);

        //storing various types of values
        StockPile.store("key1", "value");
        StockPile.store("key2", true);
        StockPile.store("key3", 10.5f);
        StockPile.store("key4", 5);
        StockPile.store("key5", 2453L);

        String stringVal = StockPile.getString("key1", "");
        boolean boolVal = StockPile.getBoolean("key2"); //2nd parameter (default vals) can be left out
        float floatVal = StockPile.getFloat("key3", 0F);
        int intVal = StockPile.getInt("key4", 0);
        long longVal = StockPile.getLong("key5", 0L);

        Log.d("wb", stringVal);
        Log.d("wb", String.valueOf(boolVal));
        Log.d("wb", String.valueOf(floatVal));
        Log.d("wb", String.valueOf(intVal));
        Log.d("wb", String.valueOf(longVal));
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
