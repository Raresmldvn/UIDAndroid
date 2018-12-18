package com.example.rares.testandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rares.testandroid.chat.UserService;
import com.example.rares.testandroid.fruit.Fruit;
import com.example.rares.testandroid.fruit.FruitService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FruitActivity extends AppCompatActivity {

    FruitService fruitService;
    FruitAdapter fruitAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fruit);
        fruitService = FruitService.getInstance();
        fruitAdapter = new FruitAdapter(this, fruitService.getFruits());

        ListView fruitList = (ListView)findViewById(R.id.fruitList);
        fruitList.setAdapter(fruitAdapter);
        fruitList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg) {
                String value = ((EditText)v.findViewById(R.id.quantity)).getText().toString();
                Fruit fruit = fruitAdapter.list.get(position);
                fruit.setChecked(!fruit.isChecked());
                fruit.setQuantity(Integer.parseInt(value));
                fruitAdapter.list.set(position, fruit);
                System.out.println("HERE" + value);
                fruitAdapter.notifyDataSetChanged();
            }
        });
    }

    public void computeTotal(View view) {

        float total = 0;
        for(Fruit fruit: fruitAdapter.list) {
            if(fruit.isChecked()) {
                total+= fruit.getPrice()*fruit.getQuantity();
            }
        }
        ((TextView)findViewById(R.id.total)).setText("Total is" + total);
    }
}