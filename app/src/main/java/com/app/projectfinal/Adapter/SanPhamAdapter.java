package com.app.projectfinal.Adapter;

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
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import com.app.projectfinal.Activity.DetailsProductActivity;
import com.app.projectfinal.ItemClickListener;
import com.app.projectfinal.Model.SanPham;
import com.app.projectfinal.R;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.MyViewHolder> {

    private Context mContext;
    private List<SanPham> mListSP = new ArrayList<>();
    SanPham sanPham;

    public SanPhamAdapter(Context context, List<SanPham> mListSP){
        this.mContext = context;
        this.mListSP = mListSP;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        private TextView tvTenSP, tvPrice, tvTyGia, tvStatus, tvNguonSP;
        private ImageView imgSP;
        private RatingBar rtbSP;
        private LinearLayout llSP;
        private ItemClickListener itemClickListener;

        public MyViewHolder (@NonNull View view){
            super(view);

            tvTenSP = view.findViewById(R.id.tvTenSP);
            tvPrice = view.findViewById(R.id.tvPrice);
            tvTyGia = view.findViewById(R.id.tvTyGia);
/*            tvStatus = view.findViewById(R.id.tvStatus);
            tvNguonSP = view.findViewById(R.id.tvNguonSP);*/
            imgSP = view.findViewById(R.id.imgSP);

            llSP = view.findViewById(R.id.llSP);

            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
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


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_san_pham, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        sanPham = mListSP.get(position);

        holder.tvTenSP.setText(sanPham.getTenSP());
        holder.tvPrice.setText(sanPham.getGiaSP() + " đ");
        holder.tvTyGia.setText(" / " + sanPham.getDonVi());
        /*holder.tvNguonSP.setText(sanPham.getShop());
        if (sanPham.getSoLuong() == 0){
            holder.tvStatus.setText("Hết hàng");
            holder.tvStatus.setTextColor(Color.RED);
        } else {
            holder.tvStatus.setText("Còn hàng");
            holder.tvStatus.setTextColor(Color.BLUE);
        }*/

        if (sanPham.getHinhSP().contains("https")) {
            Glide.with(mContext).load(sanPham.getHinhSP()).into(holder.imgSP);
        } else {
            byte[] decodedString = Base64.decode(sanPham.getHinhSP().trim(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            holder.imgSP.setImageBitmap(decodedByte);
            Log.d("TAGclclcclclclclcl", "onCl" +sanPham.getHinhSP().trim());
            Glide.with(mContext).load(decodedByte).fitCenter().into(holder.imgSP);
        }

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                sanPham = mListSP.get(position);
                if (isLongClick) {

                } else {
                    Intent intent = new Intent(mContext, DetailsProductActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("idSP", sanPham.getIdSP());
                    Log.d("TAG", "onClick: " + sanPham.getIdSP());
//                    bundle.putInt("idLSP", sanPham.getIdLSP());
//                    bundle.putInt("idUser", sanPham.getIdUser());
//                    bundle.putString("tenSP", String.valueOf(sanPham.getTenSP()));
//                    bundle.putString("giaSP", String.valueOf(sanPham.getGiaSP()));
//                    bundle.putString("donVi", String.valueOf(sanPham.getDonVi()));
//                    bundle.putString("tinhTrang", String.valueOf(sanPham.getTinhTrang()));
//                    bundle.putString("ngayDang", String.valueOf(sanPham.getNgayDang()));
//                    bundle.putString("tenLoai", String.valueOf(sanPham.getLoaiSP()));
//                    bundle.putString("shop", String.valueOf(sanPham.getShop()));
//                    bundle.putInt("soLuong", sanPham.getSoLuong());
//                    bundle.putString("hinhSP", String.valueOf(sanPham.getHinhSP()));
                    intent.putExtras(bundle);
                    view.getContext().startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListSP.size();
    }

}
