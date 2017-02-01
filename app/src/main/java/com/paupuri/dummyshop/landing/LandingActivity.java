package com.paupuri.dummyshop.landing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.paupuri.dummyshop.R;
import com.paupuri.dummyshop.addeditproduct.AddEditProductActivity;
import com.paupuri.dummyshop.addeditproduct.AddProductActivity;

public class LandingActivity extends AppCompatActivity implements View.OnClickListener {
    private Button addProductBtnGA;
    private Button addProductBtnMVP;

    @Override
    public void onClick(View view) {
        if (view == addProductBtnGA) {
            Intent intent = new Intent(this, AddProductActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, AddEditProductActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        addProductBtnGA = (Button) findViewById(R.id.button);
        addProductBtnGA.setOnClickListener(this);

        addProductBtnMVP = (Button) findViewById(R.id.buttonMVP);
        addProductBtnMVP.setOnClickListener(this);
    }
}
