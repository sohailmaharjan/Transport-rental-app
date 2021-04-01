package com.sohail.wheeler;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.sohail.wheeler.api.UsersAPI;
import com.sohail.wheeler.modal.Order;
import com.sohail.wheeler.url.Url;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestActivity extends AppCompatActivity {

    TextView tvname, tvprice;
    EditText etaddress, etnumber, quantity;
    Button ordernow;
   public static String id = null;
    Integer quan = null;
    int ordernumber = 0;
    NumberPicker np;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        tvname = findViewById(R.id.productname);
        tvprice = findViewById(R.id.productprice);
        etaddress = findViewById(R.id.productlocation);
        etnumber = findViewById(R.id.productnumber);
        ordernow = findViewById(R.id.productorder);
        np = findViewById(R.id.numberPicker);
        np.setMinValue(1);
        np.setMaxValue(5);
        np.setOnValueChangedListener(onValueChangeListener);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            tvname.setText(bundle.getString("name"));
            tvprice.setText(bundle.getString("price"));
        }

        ordernow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(etaddress.getText())) {
                    etaddress.setError("Enter Address ");
                    return;
                }
                if (TextUtils.isEmpty(etnumber.getText())) {
                    etnumber.setError("Enter Number ");
                    return;
                }
                Order();
                showdialog();
            }
        });
    }

    private void Order() {
        String userid = id;
        Random random = new Random();
        int randomNumber = random.nextInt(80 - 65) + 65;
        ordernumber = (randomNumber);
        quan = np.getValue();
        String name = tvname.getText().toString();
        String price = tvprice.getText().toString();
        String address = etaddress.getText().toString();
        String number = etnumber.getText().toString();
        Order order = new Order(name, userid, price, address, number, ordernumber, quan);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UsersAPI employeeApi = retrofit.create(UsersAPI.class);
        Call<Void> voidCall = employeeApi.orderitem(order);
        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(RequestActivity.this, "Your reservation is sent. Wait for our call", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RequestActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(RequestActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void showdialog() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(RequestActivity.this);
        builder1.setMessage("Your reservation id is " + ordernumber);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    NumberPicker.OnValueChangeListener onValueChangeListener =
            new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    Toast.makeText(RequestActivity.this,
                            "selected number " + numberPicker.getValue(), Toast.LENGTH_SHORT);
                }
            };

}
