package com.example.rares.testandroid;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.rares.testandroid.chat.UserCredentials;
import com.example.rares.testandroid.chat.UserService;
import com.example.rares.testandroid.list.Element;
import com.example.rares.testandroid.list.ListService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListActivity extends AppCompatActivity  implements DialogInterface.OnClickListener {

    ListService listService;
    ListAdapter listAdapter;
    ListView listView;

    Retrofit retrofit;
    UserService userService;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        listService = ListService.getInstance();
        listAdapter = new ListAdapter(this, listService.getList());
        listView = (ListView) findViewById(R.id.listElement);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg) {
                Intent intent = new Intent(ListActivity.this, ElementActivity.class);
                intent.putExtra("element", listAdapter.getItem(position));
                intent.putExtra("position", position);
                startActivityForResult(intent, 1);
            }
        });
        listView.setAdapter(listAdapter);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://cgisdev.utcluj.ro/moodle/chat-piu/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userService = retrofit.create(UserService.class);
        registerForContextMenu(listView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.listmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.logout) {
            AlertDialog.Builder	logoutConfirmation =	new	AlertDialog.Builder(this);
            logoutConfirmation
                    .setTitle("Log out")
                    .setMessage("Do you want to log out?")
                    .setPositiveButton("Yes", this)
                    .setNegativeButton("No",	this)
                    .show();
            return true;
        }
        if(id==R.id.reset) {
            listAdapter.clear();
            listAdapter.addAll(listService.getList());
            listAdapter.notifyDataSetChanged();
            return true;
        }

        if(id==R.id.chatWithUs) {
            Intent intent = new Intent(this, ChatActivity.class);
            startActivity(intent);
        }
        if(id==R.id.fruits) {
            Intent intent = new Intent(this, FruitActivity.class);
            startActivity(intent);
        }
        listAdapter.clear();
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v.getId()==R.id.listElement) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
            menu.setHeaderTitle(listAdapter.getItem(info.position).getName());
            getMenuInflater().inflate(R.menu.contextmenu, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo	info	=
                (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int id = item.getItemId();
        if(id==R.id.addmovie) {
            listAdapter.list.add(info.position, new Element(0, "New movie", "Basic description indicating a new movie."));
            listAdapter.notifyDataSetChanged();
            return true;
        }
        if(id==R.id.removiemovie) {
            listAdapter.list.remove(info.position);
            listAdapter.notifyDataSetChanged();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder	logoutConfirmation =	new	AlertDialog.Builder(this);
        logoutConfirmation
                .setTitle("Log out")
                .setMessage("Do you want to log out?")
                .setPositiveButton("Yes", this)
                .setNegativeButton("No",	this)
                .show();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if(i==AlertDialog.BUTTON_POSITIVE) {
            userService.logOut("Bearer " + UserCredentials.getInstance().getToken()).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if(response.isSuccessful()) {
                        System.out.println(response.toString());
                    } else {
                        System.out.println("FAIL" + response.toString());
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    System.out.println("WHAT A FAIL!");
                }
            });
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1) {
            if(resultCode==Activity.RESULT_OK) {
                int position = data.getIntExtra("position", 0);
                boolean favorite = data.getBooleanExtra("favorite", false);
                System.out.print("HELO: " + favorite);
                Element element = listAdapter.list.get(position);
                element.setFavorite(favorite);
                listAdapter.list.set(position, element);
                listAdapter.notifyDataSetChanged();
            }
        }
    }
}
