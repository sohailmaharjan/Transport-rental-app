package com.sohail.wheeler.bll;

import com.sohail.wheeler.api.UsersAPI;
import com.sohail.wheeler.serverresponse.OrderResponse;
import com.sohail.wheeler.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class OrderBLL {

    boolean isSuccess = false;

    public boolean checkorder(String productid) {

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<OrderResponse> usersCall = usersAPI.getorderstatus(productid);
        try {
            Response<OrderResponse> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Successfully Dispatched")) {
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}

