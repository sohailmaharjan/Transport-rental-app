package com.sohail.wheeler;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sohail.wheeler.api.UsersAPI;
import com.sohail.wheeler.serverresponse.OrderResponse;
import com.sohail.wheeler.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckOrderActivity extends AppCompatActivity {

    Button btncheckorder;
    EditText etcheckproductid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_order);
        btncheckorder = findViewById(R.id.btncheckorder);
        etcheckproductid = findViewById(R.id.etcheckproductid);


        btncheckorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkorder();
            }
        });
    }


    public void checkorder() {
        String orderid = etcheckproductid.getText().toString();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UsersAPI employeeApi = retrofit.create(UsersAPI.class);
        Call<OrderResponse> voidCall = employeeApi.getorderstatus(orderid);

        voidCall.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                Toast.makeText(CheckOrderActivity.this, "Not reserved yet...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {

            }
        });


    }

}
