package com.sohail.wheeler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sohail.wheeler.R;
import com.sohail.wheeler.modal.Notification;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyHolder> {
    List<Notification> notifications;
    Context contexts;

    public NotificationAdapter(List<Notification> notifications, Context contexts) {
        this.notifications = notifications;
        this.contexts = contexts;
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_notification, parent, false);//yaha chai milauna parne xa
        MyHolder myholder = new MyHolder(view);
        return myholder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        final Notification notification = notifications.get(position);
        holder.tvstartdate.setText(notification.getPostedDate());
        holder.tvenddate.setText(notification.getEndDate());
        holder.tvdescription.setText(notification.getDescription());
        holder.tvtitle.setText(notification.getTitle());
    }

    @Override
    public int getItemCount() {
        {
            return notifications.size();
        }

    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView tvstartdate,tvenddate,tvdescription,tvtitle;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvdescription=itemView.findViewById(R.id.tvdesc);
            tvstartdate=itemView.findViewById(R.id.tvstartdate);
            tvenddate=itemView.findViewById(R.id.tvendate);
            tvtitle=itemView.findViewById(R.id.tvtitle);

        }
    }
}