//package fpoly.andoid.test2;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.appcompat.app.ActionBar;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//
//import com.bumptech.glide.Glide;
//
//public class DetailedProductsActivity extends AppCompatActivity {
//
//    private Toolbar mToolbar;
//    private ActionBar mActionBar;
//    private ImageView mImage;
//    private TextView mTitle, mRating, mPrice;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_detailed_products);
//
//        mToolbar = findViewById(R.id.toolbar);
//        mImage = findViewById(R.id.image_view);
//        mPrice = findViewById(R.id.price);
//        mRating = findViewById(R.id.rating);
//        mTitle = findViewById(R.id.name);
//
//        // Setting up action bar
//        setSupportActionBar(mToolbar);
//        mActionBar = getSupportActionBar();
//        mActionBar.setDisplayHomeAsUpEnabled(true);
//        mActionBar.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_chevron_left_black_24dp));
//
//        // Catching incoming intent
//        Intent intent = getIntent();
//
//        String ten_loai = intent.getStringExtra("ten_loai");
//        String hinh_loai = intent.getStringExtra("hinh_loai");
//
//        if (intent !=null){
//
//            mActionBar.setTitle(ten_loai);
//            mTitle.setText(ten_loai);
//
//            Glide.with(DetailedProductsActivity.this).load(hinh_loai).into(mImage);
//        }
//
//    }
//}
