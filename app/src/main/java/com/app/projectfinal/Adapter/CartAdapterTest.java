package com.app.projectfinal.adapter;

import static com.app.projectfinal.activity.ProductDetailActivity.carts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.projectfinal.R;
import com.app.projectfinal.model.Cart;
import com.app.projectfinal.utils.ItemClickListener;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CartAdapterTest extends RecyclerView.Adapter<CartAdapterTest.CartViewHolder> {

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private Context mContext;
    private List<Cart> mList;
    private Cart cart;

    public CartAdapterTest(Context mContext, List<Cart> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull

    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_product, parent, false);

        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        cart = mList.get(position);

//        holder.rcv_item_cart.setAdapter(cartAdapter);
//
//        holder.tv_name_product.setText(cart.getName_product());
//        Glide.with(mContext).load(cart.getImg_product()).into(holder.img);
//        holder.tv_price.setText(cart.getPrice());
//        holder.edt_amount.setText(cart.getAmount());
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private ItemClickListener itemClickListener;

        private TextView tv_name_shop;
        private RecyclerView rcv_item_cart;
        private CartAdapter cartAdapter;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name_shop = itemView.findViewById(R.id.tv_name_shop);
            rcv_item_cart = itemView.findViewById(R.id.rcv_item_cart);
            cartAdapter = new CartAdapter(mContext, carts);


            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

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

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
    }



}
