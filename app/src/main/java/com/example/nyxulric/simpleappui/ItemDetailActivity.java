package com.example.nyxulric.simpleappui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ItemDetailActivity extends AppCompatActivity {

    private EditText listingName, productDetail, productPrice, description;
    private TextView  category;
    private ImageView map;
    private Button btnCategory, btnListIt;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    String itemBrand, itemPrice, listName, itemDescribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        listingName = findViewById(R.id.editNameListing);
        productDetail = findViewById(R.id.brand);
        productPrice = findViewById(R.id.price);
        description = findViewById(R.id.editDesc);
        category = findViewById(R.id.viewCategory);
        btnCategory = findViewById(R.id.btnCategory);
        btnListIt = findViewById(R.id.btnListing);
        map = findViewById(R.id.mapView);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnListIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemDetail();
                finish();
                Toast.makeText(ItemDetailActivity.this, "Listing Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ItemDetailActivity.this, SecondActivity.class));
            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void itemDetail(){
        itemBrand = productDetail.getText().toString().trim();
        itemPrice = productPrice.getText().toString().trim();
        listName = listingName.getText().toString().trim();
        itemDescribe = description.getText().toString().trim();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid()).child("Item Detail");

        ProductDetail productDetail = new ProductDetail(itemBrand,itemPrice,listName,itemDescribe);
        myRef.setValue(productDetail);
    }
}
