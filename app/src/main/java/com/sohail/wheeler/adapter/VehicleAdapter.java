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
import com.sohail.wheeler.modal.ExploreViewModal;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.MyHolder> {

    List<ExploreViewModal> exploreViewModal_list;
    Context contexts;

    public VehicleAdapter(List<ExploreViewModal> exploreViewModal_list, Context contexts) {
        this.exploreViewModal_list = exploreViewModal_list;
        this.contexts = contexts;
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_vehicle, parent, false);//yaha chai milauna parne xa
        MyHolder myholder = new MyHolder(view);
        return myholder;
    }
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        final ExploreViewModal exploreViewModal = exploreViewModal_list.get(position);
        holder.txt_item_product_name.setText(exploreViewModal.getName());
        holder.txt_item_product_price.setText("Rs: " + exploreViewModal.getPrice() + " / day");

        Picasso.get()
                .load(exploreViewModal.getImage())
                .placeholder(R.drawable.loadingcar)
                .resize(220, 220)
                .centerCrop()
                .into(holder.item_product_image);

        holder.item_product_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(contexts, ProductdetailActivity.class);
                intent.putExtra("_id", exploreViewModal.get_id());
                intent.putExtra("image", exploreViewModal.getImage());
                intent.putExtra("name", exploreViewModal.getName());
                intent.putExtra("description", exploreViewModal.getDescription());
                intent.putExtra("price", exploreViewModal.getPrice());
                intent.putExtra("specification", exploreViewModal.getSpecification());
                contexts.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return exploreViewModal_list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView txt_item_product_name, txt_item_product_price;
        ImageView item_product_image;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            txt_item_product_name = itemView.findViewById(R.id.txtProductName);
            txt_item_product_price = itemView.findViewById(R.id.txtPrice);
            item_product_image = itemView.findViewById(R.id.imgProduct);
        }
    }

}

