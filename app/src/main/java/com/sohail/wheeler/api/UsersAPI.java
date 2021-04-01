package com.sohail.wheeler.api;

import com.sohail.wheeler.modal.Cart;
import com.sohail.wheeler.modal.Order;
import com.sohail.wheeler.modal.UserUpdateModel;
import com.sohail.wheeler.modal.Users;
import com.sohail.wheeler.modal.UsersCUD;
import com.sohail.wheeler.serverresponse.CartResponse;
import com.sohail.wheeler.serverresponse.OrderResponse;
import com.sohail.wheeler.serverresponse.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UsersAPI {
    //register employee
    @POST("register/register_user")
    Call<Void> registerEmployee(@Body UsersCUD usersCUD);

    @POST("order/addorder")
    Call<Void> orderitem(@Body Order order);

    @FormUrlEncoded
    @POST("cart/checkcart")
    Call<CartResponse> checkcart(@Field("productid") String productid, @Field("userid") String userid);

    @POST("cart/addcart")
    Call<Void> addcart(@Body Cart cart);

    //for logging into the system
    @FormUrlEncoded
    @POST("register/login_user")
    Call<SignUpResponse> checkUser(@Field("email") String email, @Field("password") String password);

    //me
    @GET("register/me")
    Call<Users> getUserDetails(@Header("Authorization") String token);

    //forupdatinguser
    @PUT("register/me")
    Call<UserUpdateModel> edituser(@Header("Authorization") String token, @Body UserUpdateModel userUpdateModel);

    //
    @GET("order/orderget/{id}")
    Call<OrderResponse> getorderstatus(@Path("id") String id);
}

