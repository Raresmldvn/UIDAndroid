package com.example.rares.testandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rares.testandroid.chat.ReceivedUser;
import com.example.rares.testandroid.chat.User;
import com.example.rares.testandroid.chat.UserCredentials;
import com.example.rares.testandroid.chat.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class MainActivity extends AppCompatActivity {

    Retrofit retrofit;
    UserService userService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://cgisdev.utcluj.ro/moodle/chat-piu/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userService = retrofit.create(UserService.class);
    }

    public void LogInClick(android.view.View view)
    {
        ((TextView)findViewById(R.id.usernameError)).setText("");
        ((TextView)findViewById(R.id.passwordError)).setText("");
        String username = ((EditText)findViewById(R.id.usernameEditText)).getText().toString();
        String password = ((EditText)findViewById(R.id.passwordEditText)).getText().toString();
        boolean foundError = false;
        if(username.length()<3) {
            ((TextView)findViewById(R.id.usernameError)).setText("Username must be an email!");
            foundError = true;
        }
        if(password.length()<6) {
            ((TextView)findViewById(R.id.passwordError)).setText("Password must have at leat 6 characters!");
            foundError = true;
        }

        if(foundError) return;
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        /**
        userService.logIn(user).enqueue( new Callback<ReceivedUser>() {

            @Override
            public void onResponse(Call<ReceivedUser> call, Response<ReceivedUser> response) {
                if(response.isSuccessful()) {
                    UserCredentials.getInstance().setToken(response.body().getToken());
                    UserCredentials.getInstance().setDisplay(response.body().getName());
                    ((TextView) findViewById(R.id.logInResult)).setText("Succesfully logged in!");
                    Intent intent = new Intent(MainActivity.this, ListActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ReceivedUser> call, Throwable t) {
                ((TextView)findViewById(R.id.logInResult)).setText("Wrong credentials!");
            }
        });*/
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        startActivity(intent);
    }
}
