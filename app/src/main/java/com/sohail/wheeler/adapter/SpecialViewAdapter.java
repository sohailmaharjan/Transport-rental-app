package com.sohail.wheeler.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sohail.wheeler.ProductdetailActivity;
import com.sohail.wheeler.R;
import com.sohail.wheeler.modal.SpecialViewModal;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SpecialViewAdapter extends RecyclerView.Adapter<SpecialViewAdapter.MyHolder> {

    List<SpecialViewModal> products_list;
    Context contexts;

    public SpecialViewAdapter(List<SpecialViewModal> products_list, Context contexts) {
        this.products_list = products_list;
        this.contexts = contexts;
    }

    @NonNull
    @Override
    public SpecialViewAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_special, parent, false);//yaha chai milauna parne xa
        MyHolder myholder = new MyHolder(view);
        return myholder;
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialViewAdapter.MyHolder holder, int position) {

        final SpecialViewModal specialmodal = products_list.get(position);
        holder.txt_item_product_name.setText(specialmodal.getName());
        holder.txt_item_product_price.setText("Rs: " + specialmodal.getPrice() + " / day");
        Picasso.get()
                .load(specialmodal.getImage())
                .placeholder(R.drawable.loadingcar)
                .resize(220, 220)
                .centerCrop()
                .into(holder.item_product_image);

        holder.item_product_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(contexts, ProductdetailActivity.class);
                intent.putExtra("_id", specialmodal.get_id());
                intent.putExtra("image", specialmodal.getImage());
                intent.putExtra("name", specialmodal.getName());
                intent.putExtra("description", specialmodal.getDescription());
                intent.putExtra("price", specialmodal.getPrice());
                intent.putExtra("specification", specialmodal.getSpecification());
                contexts.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount()
    {
        return products_list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView txt_item_product_name, txt_item_product_price;
        ImageView item_product_image;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            txt_item_product_name = itemView.findViewById(R.id.txtProductName1);
            txt_item_product_price = itemView.findViewById(R.id.txtPrice1);
            item_product_image = itemView.findViewById(R.id.imgProduct1);
        }
    }
}

