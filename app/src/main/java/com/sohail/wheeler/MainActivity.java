package com.sohail.wheeler;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.sohail.wheeler.api.UsersAPI;
import com.sohail.wheeler.modal.UserUpdateModel;
import com.sohail.wheeler.modal.Users;
import com.sohail.wheeler.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Users users;
    private EditText etfname, etlname, etemail, etpassword, etaddress, etnumber;
    private TextView txtshop;
    public String userID = "";
    Button btnlogins;

    LinearLayout ly_arch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ly_arch = findViewById(R.id.ly_tst);
        ly_arch.setVisibility(View.GONE);

        getWindow().setStatusBarColor(Color.parseColor("#ffffff"));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        etemail = findViewById(R.id.etremail);
        etfname = findViewById(R.id.etrfname);
        etlname = findViewById(R.id.etrlname);
        etpassword = findViewById(R.id.etrpwd);
        etaddress = findViewById(R.id.etaddress);
        etnumber = findViewById(R.id.etrnumber);
        txtshop = findViewById(R.id.txtshop);
        btnlogins = findViewById(R.id.btnVerify);

        btnlogins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(etemail.getText())) {
                    etemail.setError("Enter Email ");
                    ;
                    return;
                }
                if (TextUtils.isEmpty(etfname.getText())) {
                    etfname.setError("Enter first name ");
                    ;
                    return;
                }
                if (TextUtils.isEmpty(etlname.getText())) {
                    etlname.setError("Enter last name ");
                    ;
                    return;
                }

                if (TextUtils.isEmpty(etaddress.getText())) {
                    etaddress.setError("Enter address ");
                    ;
                    return;
                }

                if (TextUtils.isEmpty(etnumber.getText())) {
                    etnumber.setError("Enter number ");
                    ;
                    return;
                }
                update();
            }
        });
        loadCurrentUser();


        txtshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
    private void loadCurrentUser() {
        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<Users> userCall = usersAPI.getUserDetails(Url.token);
        userCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Error" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                users = response.body();
                userID = response.body().get_id();

                // set id and pass value in ProductActivity
                ProductdetailActivity.id = response.body().get_id();
                FavouriteActivity.id = response.body().get_id();
                RequestActivity.id=response.body().get_id();

//                Toast.makeText(MainActivity.this, "ID IS"+response.body().get_id(), Toast.LENGTH_SHORT).show();
                etfname.setText(response.body().getFname());
                etlname.setText(response.body().getLname());
                etemail.setText(response.body().getEmail());
                etaddress.setText(response.body().getAddress());
                etnumber.setText(response.body().getNumber());
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Log.d("My message", "onFaliure" + t.getLocalizedMessage());
                Toast.makeText(MainActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void update() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserUpdateModel usersCUD = new UserUpdateModel(userID,etfname.getText().toString(), etlname.getText().toString(),
                etemail.getText().toString(), etaddress.getText().toString(), etnumber.getText().toString());
        UsersAPI employeeApi = retrofit.create(UsersAPI.class);
        Call<UserUpdateModel> updateModelCall = employeeApi.edituser(Url.token, usersCUD);

        updateModelCall.enqueue(new Callback<UserUpdateModel>() {
            @Override
            public void onResponse(Call<UserUpdateModel> call, Response<UserUpdateModel> response) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setMessage("Updated Successfully");
                ly_arch.setVisibility(View.VISIBLE);
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }

            @Override
            public void onFailure(Call<UserUpdateModel> call, Throwable t) {

            }
        });

    }
}
