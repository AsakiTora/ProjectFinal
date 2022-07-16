package com.app.projectfinal.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.app.projectfinal.Adapter.CartAdapter;
import com.app.projectfinal.DataLocal.DataLocalManager;
import com.app.projectfinal.Model.Cart;
import com.app.projectfinal.Model.User;
import com.app.projectfinal.R;

public class NotificationsFragment extends Fragment {

    private ProgressBar progressBar;

    public NotificationsFragment() {
        // Required empty public constructor
    }

    public static final String TAG = "My TAG";

    private StringRequest mStringRequest;
    private RequestQueue mRequestQueue;

    private RecyclerView.Adapter mAdapter;
    private ArrayList<Cart> listCart;
    private RecyclerView rcvCart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        listCart = new ArrayList<>();

        rcvCart = view.findViewById(R.id.rcvCart);

        return view;
    }

    private void getCart() {
        mRequestQueue = Volley.newRequestQueue(getActivity());
        progressBar.setVisibility(View.VISIBLE);
        mStringRequest = new StringRequest(Request.Method.POST, getBaseUrl(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "onResponse: " + response);
                        progressBar.setVisibility(View.GONE);
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++){

                                JSONObject object = array.getJSONObject(i);
                                Log.d("objecttttttttttt", String.valueOf(object));

                                int idSP = object.getInt("id_san_pham");
                                int idUser = object.getInt("id_user");
                                int soLuong = object.getInt("so_luong");
                                String tong = object.getString("tong");
                                String hinhSP = object.getString("hinh_san_pham");

                                /*Cart cart = new Cart(idUser, idSP, soLuong, tong, hinhSP);
                                listCart.add(cart);*/
                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        mAdapter = new CartAdapter(getContext(), listCart);
                        rcvCart.setAdapter(mAdapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), error.toString(),Toast.LENGTH_LONG).show();
                Log.d("errorrrrrrrrrrrrrreeeee", String.valueOf(error));
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                User user = DataLocalManager.getUser();
                params.put("id_user", String.valueOf(user.getId_user()));
                return params;
            }
        };

        mStringRequest.setShouldCache(false);
        mRequestQueue.add(mStringRequest);
    }

    private String getBaseUrl (){
        return "http://"+"/du_an_1/getCart.php";
    }


}