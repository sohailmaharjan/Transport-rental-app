package com.sohail.wheeler.bll;

import com.sohail.wheeler.api.UsersAPI;
import com.sohail.wheeler.serverresponse.SignUpResponse;
import com.sohail.wheeler.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

//function for checking if user is present in the system or not
public class LoginBll {
    boolean isSuccess = false;

    public boolean checkuser(String email, String password) {

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<SignUpResponse> usersCall = usersAPI.checkUser(email, password);

        try {
            Response<SignUpResponse> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Login success!")) {
                Url.token += loginResponse.body().getToken();
                // Url.Cookie = imageResponseResponse.headers().get("Set-Cookie");
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
