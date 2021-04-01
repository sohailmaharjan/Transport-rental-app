package com.sohail.wheeler;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.sohail.wheeler.api.UsersAPI;
import com.sohail.wheeler.bll.CartBll;
import com.sohail.wheeler.modal.Cart;
import com.sohail.wheeler.url.Url;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductdetailActivity extends AppCompatActivity {

    ImageView dimgview;
    TextView tvdname, txt_price, tvddesc, tvspecification;
    Button btnRent;

    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    private final static String default_notification_channel_id = "default";
    Context mcontext;
    public static String id = null;
    String product_id = "";
    private FloatingActionButton fab;
    Button buttonCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dimgview = findViewById(R.id.imgdis);
        tvddesc = findViewById(R.id.tvddesc);
        txt_price = findViewById(R.id.txtCost);
        tvdname = findViewById(R.id.tvdname);
        tvspecification = findViewById(R.id.tvspecification);
        btnRent = findViewById(R.id.rentnow);
        buttonCart = findViewById(R.id.cartnow);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String imgPath = bundle.getString("image");
            Picasso.get()
                    .load(imgPath)
                    .placeholder(R.drawable.defaultload)
                    .resize(220, 220)
                    .centerCrop()
                    .into(dimgview);
            product_id = bundle.getString("_id");
            tvdname.setText(bundle.getString("name"));
            txt_price.setText("Rent cost: " + bundle.getString("price") + " / day");
            tvddesc.setText(bundle.getString("description"));
            tvspecification.setText(bundle.getString("specification"));
            btnRent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mcontext = getApplicationContext();
                    Bundle bundle = getIntent().getExtras();
                    Toast.makeText(ProductdetailActivity.this, "" +id, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ProductdetailActivity.this, RequestActivity.class);
                    intent.putExtra("_id", bundle.getString("_id"));
                    intent.putExtra("name", bundle.getString("name"));
                    intent.putExtra("price", bundle.getString("price"));
                    startActivity(intent);
                }
            });
        }

        buttonCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _userid = id;
                String _productid = product_id;
                CartBll cartBll = new CartBll();
                if (cartBll.checkcart(_userid, _productid)) {
                    Register();
                    Snackbar.make(view, "Added to Favourite", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    scheduleNotification(getNotification("Buy now the product added in the cart box here ..."), 5000);
                } else {
                    fab.setEnabled(false);
                    Snackbar.make(view, "Already Added! Check favourite", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    scheduleNotification(getNotification("You have already added  ..."), 5000);
                }
            }
        });

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _userid = id;
                String _productid = product_id;
                CartBll cartBll = new CartBll();
                if (cartBll.checkcart(_userid, _productid)) {
                    Register();
                    Snackbar.make(view, "Successfully added to favourite", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    scheduleNotification(getNotification("Buy now the cart added here ..."), 5000);
                } else {
                    fab.setEnabled(false);
                    Snackbar.make(view, "The vehicle is already marked as favourite", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    scheduleNotification(getNotification("You have already added  ..."), 5000);
                }
            }
        });
    }
    private void scheduleNotification(Notification notification, int delay) {
        Intent notificationIntent = new Intent(this, MyNotificationPublisher.class);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        assert alarmManager != null;
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }
    private Notification getNotification(String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, default_notification_channel_id);
        builder.setContentTitle("Check out Cart");
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.icon);
        builder.setAutoCancel(true);
        builder.setChannelId(NOTIFICATION_CHANNEL_ID);
        return builder.build();
    }
    private void Register() {

        String _userid = id;
        String _productid = product_id;
        String name = tvdname.getText().toString();
        String price = txt_price.getText().toString();
        String specification = tvspecification.getText().toString();
        String description = tvddesc.getText().toString();
        Cart cart = new Cart(_userid, _productid, name, price, description, specification);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UsersAPI employeeApi = retrofit.create(UsersAPI.class);
        Call<Void> voidCall = employeeApi.addcart(cart);
        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getApplicationContext(), "You have added to cart registered", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
