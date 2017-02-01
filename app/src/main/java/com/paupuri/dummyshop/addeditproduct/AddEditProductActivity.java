package com.paupuri.dummyshop.addeditproduct;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.parse.ParseFile;
import com.parse.ParseUser;
import com.paupuri.dummyshop.BaseActivity;
import com.paupuri.dummyshop.R;
import com.tangxiaolv.telegramgallery.GalleryActivity;
import com.tangxiaolv.telegramgallery.GalleryConfig;

import java.util.ArrayList;
import java.util.List;

public class AddEditProductActivity extends BaseActivity implements AddEditProductContract.View, View.OnClickListener {

    private AddEditProductContract.Presenter presenter;
    private List<String> photos;
    private Button publishBtn;
    private EditText productNameField;
    private EditText productDescriptionField;
    private EditText productPriceField;
    private LinearLayout linearLayout;
    private ImageButton imageButton;
    private ArrayList<ParseFile> placeholder = new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            showEmptyImageError();
        } else {
            photos = (List<String>) data.getSerializableExtra(GalleryActivity.PHOTOS);
            presenter.onPhotosAddition(photos);
        }
    }

    @Override
    public void onClick(View view) {
        if ((view == linearLayout)) {
            hideKeyboard();
        } else if (view == imageButton) {
            GalleryConfig config = new GalleryConfig.Build()
                    .limitPickPhoto(8)
                    .singlePhoto(false)
                    .hintOfPick("You can pick up to 8 pictures.")
                    .filterMimeTypes(new String[]{"image/*"})
                    .build();
            GalleryActivity.openActivity(AddEditProductActivity.this, 2, config);
        } else if (view == publishBtn) {
            presenter.onClickPublishBtnonClickPublishBtn(productNameField.getText().toString(),
                    productDescriptionField.getText().toString(),
                    productPriceField.getText().toString(),
                    placeholder,
                    ParseUser.getCurrentUser().get("city").toString(),
                    ParseUser.getCurrentUser());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_product);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setTitle(R.string.addItemTextview);

        linearLayout = (LinearLayout) findViewById(R.id.activity_add_product);
        linearLayout.setOnClickListener(this);

        imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(this);

        publishBtn = (Button) findViewById(R.id.publishItemBtn);
        publishBtn.setOnClickListener(this);

        productNameField = (EditText) findViewById(R.id.productNameField);
        productDescriptionField = (EditText) findViewById(R.id.productDescriptionField);
        productPriceField = (EditText) findViewById(R.id.priceField);

        presenter = new AddEditProductPresenter(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showEmptyImageError() {
        Toast.makeText(this, R.string.empty_image_error, Toast.LENGTH_SHORT).show();
    }
}
