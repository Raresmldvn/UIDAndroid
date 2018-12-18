package com.example.rares.testandroid.chat;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserService {

    @POST("authenticate.php")
    @Headers("Content-Type: application/json")
    Call<ReceivedUser> logIn(@Body User user);

    @DELETE("logout.php")
    @Headers("Content-Type: application/json")
    Call<Void> logOut(@Header("Authorization") String bearer);

    @PUT("sendmessage.php")
    @Headers("Content-Type: application/json")
    Call<Void> sendMessage(@Header("Authorization") String bearer, @Body Message message);

    @GET("readmessages.php")
    @Headers("Content-Type: application/json")
    Call<Messages> readMessages(@Header("Authorization") String bearer);

}
