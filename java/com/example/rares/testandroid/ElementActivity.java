package com.example.rares.testandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rares.testandroid.list.Element;
import com.example.rares.testandroid.list.ListService;

public class ElementActivity extends AppCompatActivity {

    Element element;
    ListService listService;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_element);
        Intent intent = this.getIntent();
        listService = ListService.getInstance();
        element = (Element)intent.getSerializableExtra("element");
        position = intent.getIntExtra("position", 0);
        ((TextView)findViewById(R.id.elTitle)).setText(element.getName());
        ((TextView)findViewById(R.id.elDescription)).setText(element.getDescription());
        if(element.isFavorite()) {
            ((ImageView)findViewById(R.id.elFavourite)).setImageResource(R.drawable.star);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.element_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.set_favorite) {
            element.setFavorite(true);
            listService.setFavourite(position, true);
            ((ImageView)findViewById(R.id.elFavourite)).setImageResource(R.drawable.star);
            return true;
        }
        if(id==R.id.remove_favorite) {
            element.setFavorite(false);
            listService.setFavourite(position ,false);
            ((ImageView)findViewById(R.id.elFavourite)).setImageDrawable(null);
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("position", position);
        intent.putExtra("favorite", element.isFavorite());
        System.out.println("is from element:" + element.isFavorite());
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
