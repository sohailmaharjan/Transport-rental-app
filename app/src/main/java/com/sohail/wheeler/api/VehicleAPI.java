package com.sohail.wheeler.api;

import com.sohail.wheeler.modal.Cart;
import com.sohail.wheeler.modal.ExploreViewModal;
import com.sohail.wheeler.modal.SpecialViewModal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface VehicleAPI {

    //    special api GET
    @GET("offer/getspecialvehicle")
    Call<List<SpecialViewModal>> getSpecialVehicle();

    //    view all vehicle
    @GET("vehicle/getVehicle")
    Call<List<ExploreViewModal>> getallVehicle();

    //get all favourite vehicle
    @GET("cart/check/{userid}")
    Call<List<Cart>> getcart(@Path("userid") String userid);

}