package com.sohail.wheeler.api;

import com.sohail.wheeler.modal.Notification;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NotificationAPI {

    @GET("notification/notifications")
    Call<List<Notification>> getAllNotifications();

}
