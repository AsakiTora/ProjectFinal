package com.app.projectfinal.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.app.projectfinal.R;
import com.app.projectfinal.activity.ProductDetailActivity;
import com.app.projectfinal.model.Product;
import com.app.projectfinal.utils.ItemClickListener;
import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    private List<Product> products;
    private Product product;
    private Context context;

    public ProductAdapter(List<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        private TextView name, price, unit;
        private ImageView image;
        private ItemClickListener itemClickListener;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.tv_name_product);
            price=itemView.findViewById(R.id.tv_price);
            image = itemView.findViewById(R.id.img_product);
            unit = itemView.findViewById(R.id.tv_unit);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(),false);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(),true);
            return false;
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        product = products.get(position);
        List<Object> list_img = product.getImage();
        holder.name.setText(product.getName());
        holder.price.setText(product.getPrice());
        holder.unit.setText(product.getUnit_name());
        Glide.with(context).load(list_img.get(0)).into(holder.image);

//        if (product.getImage().contains("https")) {
//        } else {
//            byte[] decodedString = Base64.decode(list_img.get(0).toString().trim(), Base64.DEFAULT);
//            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//            holder.image.setImageBitmap(decodedByte);
//            Glide.with(context).load(decodedByte).fitCenter().into(holder.image);
//        }

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                product = products.get(position);
                if (isLongClick) {

                } else {
                    Intent intent = new Intent(context, ProductDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("position_product", position);
                    intent.putExtras(bundle);
                    view.getContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
