package com.example.rares.testandroid;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rares.testandroid.chat.DisplayMessage;
import com.example.rares.testandroid.chat.Message;
import com.example.rares.testandroid.chat.Messages;
import com.example.rares.testandroid.chat.ReceivedMessage;
import com.example.rares.testandroid.chat.UserCredentials;
import com.example.rares.testandroid.chat.UserService;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatActivity extends AppCompatActivity {

    ChatAdapter adapter;
    Retrofit retrofit;
    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
        ((TextView)findViewById(R.id.welcomeMessage)).setText("Welcome " + UserCredentials.getInstance().getDisplay());
        ListView chatList = findViewById(R.id.chatList);
        adapter = new ChatAdapter(this, new ArrayList<DisplayMessage>());
        chatList.setAdapter(adapter);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://cgisdev.utcluj.ro/moodle/chat-piu/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userService = retrofit.create(UserService.class);
        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        System.out.println("tic tac");
                        try {
                            userService.readMessages("Bearer " + UserCredentials.getInstance().getToken()).enqueue( new Callback<Messages>() {

                                @Override
                                public void onResponse(Call<Messages> call, Response<Messages> response) {
                                    if(response.body()==null)
                                        return;
                                    List<ReceivedMessage> list = response.body().getMessages();
                                    System.out.println(response);
                                    if(list==null)
                                        return;
                                    for(ReceivedMessage received : list) {
                                        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                                        Date date = new Date();
                                        try {
                                            date = sf.parse(received.getTimeStamp());
                                        }catch(Exception e) {
                                            date = new Date();
                                        }
                                        adapter.list.add(new DisplayMessage(received.getSender(), received.getText(),date));
                                    }
                                    adapter.notifyDataSetChanged();
                                }

                                @Override
                                public void onFailure(Call<Messages> call, Throwable t) {
                                    System.out.println(call.toString() + t.toString());
                                }
                            });
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 5000); //execute in every 50000 ms
    }


    public void sendClick(View view) {
        String message = ((EditText)findViewById(R.id.editMessage)).getText().toString();
        Message mess = new Message();
        mess.setMesssage(message);
        userService.sendMessage("Bearer " + UserCredentials.getInstance().getToken(), mess).enqueue(new Callback<Void>() {
                                                                                                        @Override
                                                                                                        public void onResponse(Call<Void> call, Response<Void> response) {

                                                                                                        }

                                                                                                        @Override
                                                                                                        public void onFailure(Call<Void> call, Throwable t) {

                                                                                                        }
                                                                                                    }
        );
        DisplayMessage dM = new DisplayMessage(UserCredentials.getInstance().getDisplay(),message, new Date());
        adapter.list.add(dM);
        adapter.notifyDataSetChanged();

    }
}
