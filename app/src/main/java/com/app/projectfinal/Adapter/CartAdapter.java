package com.app.projectfinal.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.app.projectfinal.ItemClickListener;
import com.app.projectfinal.Model.Cart;
import com.app.projectfinal.R;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private Context mContext;
    private List<Cart> mList;
    private Cart cart;

    public CartAdapter(Context mContext, ArrayList<Cart> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull

    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_cart_product, parent, false);

        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartViewHolder holder, int position) {
        cart = mList.get(position);

        /*holder.tvTenSPCart.setText(cart.get());
        Glide.with(mContext).load(loaiSanPham.getHinh_loai()).into(holder.imgHinhLoai);*/

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

        private TextView tvTenSPCart, tvPriceCart;
        private ImageView img;
        private ImageButton imgAdd, imgRemove;
        private CheckBox checkBox;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTenSPCart = itemView.findViewById(R.id.tvTenSPCart);
            tvPriceCart = itemView.findViewById(R.id.tvPriceCart);
            checkBox = itemView.findViewById(R.id.cbxShop);
            imgAdd = itemView.findViewById(R.id.imgAdd);
            imgRemove = itemView.findViewById(R.id.imgRemove);
            img = itemView.findViewById(R.id.imgSP);
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
