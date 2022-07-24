package com.app.projectfinal.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.app.projectfinal.model.Product;
import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    private List<Product> products;
    private Product product;

    public ProductAdapter(List<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    private Context context;

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
        Log.e("TAG", "onBindViewHolder: " + list_img.get(0));
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

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name, price, unit;
        private ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.tv_name_product);
            price=itemView.findViewById(R.id.tv_price);
            image = itemView.findViewById(R.id.img_product);
            unit = itemView.findViewById(R.id.tv_unit);
        }
    }
}
