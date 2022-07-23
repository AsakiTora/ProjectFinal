package com.app.projectfinal.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.projectfinal.R;
import com.app.projectfinal.model.Category;
import com.app.projectfinal.utils.ItemClickListener;
import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private Context mContext;
    private List<Category> mListLSP;
    private Category loaiSanPham;

    public CategoryAdapter(Context context, List<Category> mListLSP){
        this.mContext = context;
        this.mListLSP = mListLSP;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private TextView tvTenLoai;
        private CircleImageView imgHinhLoai;

        private ItemClickListener itemClickListener;

        public ViewHolder(View view) {
            super(view);
            tvTenLoai = view.findViewById(R.id.tvTenLoai);
            imgHinhLoai = view.findViewById(R.id.imgHinhLoai);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), true);
            return false;
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_category, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        loaiSanPham = mListLSP.get(position);

//        holder.tvTenLoai.setText(loaiSanPham.getTen_loai());
//        Glide.with(mContext).load(loaiSanPham.getHinh_loai()).into(holder.imgHinhLoai);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {

                } else {
//                    Intent intent = new Intent(mContext, ShowLSPActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("id", mListLSP.get(position).getIdLSP());
//                    bundle.putString("ten_loai", mListLSP.get(position).getTen_loai());
//                    intent.putExtras(bundle);
//                    view.getContext().startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListLSP.size();
    }
}
