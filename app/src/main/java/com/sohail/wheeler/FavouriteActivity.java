package com.sohail.wheeler;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sohail.wheeler.adapter.FavouriteAdapter;
import com.sohail.wheeler.api.VehicleAPI;
import com.sohail.wheeler.modal.Cart;
import com.sohail.wheeler.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouriteActivity extends AppCompatActivity {
    RecyclerView recyclerViewcart;
    public static String id = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        recyclerViewcart = findViewById(R.id.rvcart);
        getProduct();
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        SensorEventListener grrplistener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[1] < 0) {
//                    Intent intent=new Intent(CartActivity.this,LandingActivity.class);
//                    startActivity(intent);
//                    finish();
                }
                else if (event.values[1] > 0)
                {
                    // Intent intent=new Intent(CartActivity.this,.class);
                    //startActivity(intent);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        if (sensor != null) {
            sensorManager.registerListener(grrplistener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Toast.makeText(getApplicationContext(), "No sensor found", Toast.LENGTH_SHORT).show();
        }
    }

    public void getProduct() {
        String userid = id;
        VehicleAPI retrofitProductAPI = Url.getInstance().create(VehicleAPI.class);
        Call<List<Cart>> ProductsCall = retrofitProductAPI.getcart(userid);
        ProductsCall.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                // Toast.makeText(getActivity(), "RESPONSE" + response.body(), Toast.LENGTH_SHORT).show();
                FavouriteAdapter recyclerviewAdapter = new FavouriteAdapter(response.body(), getApplicationContext());
                RecyclerView.LayoutManager mlayoutManager = new GridLayoutManager(getApplicationContext(), 1);
                recyclerViewcart.setLayoutManager(mlayoutManager);
                recyclerViewcart.setHasFixedSize(true);
                recyclerViewcart.setAdapter(recyclerviewAdapter);
                recyclerviewAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {
            }
        });


    }
}
