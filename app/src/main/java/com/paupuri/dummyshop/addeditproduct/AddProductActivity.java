package com.paupuri.dummyshop.addeditproduct;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.paupuri.dummyshop.BaseActivity;
import com.paupuri.dummyshop.R;
import com.paupuri.dummyshop.model.Product;
import com.tangxiaolv.telegramgallery.GalleryActivity;
import com.tangxiaolv.telegramgallery.GalleryConfig;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AddProductActivity extends BaseActivity implements View.OnClickListener {
    private Bitmap bitmap;
    private byte[] byteArray;
    private List<String> photos;
    private Button publishBtn;
    private EditText productNameField;
    private EditText productDescriptionField;
    private EditText productPriceField;
    private TextView addItemTextview;
    private ArrayList<ParseFile> images;
    private LinearLayout linearLayout;
    private ImageButton imageButton;
    private Product product = new Product();

    private void startPhotoTreatmentTask(List<String> input) {
        images = new ArrayList<ParseFile>();
        Observable.just(input)
                .map(this::doInBackground)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(this::onPreExecute)
                .subscribe(this::onPostExecute);
    }

    private void onPreExecute() {
        Log.i("Start time", "SET");
        Toast.makeText(this, R.string.image_formatting_toast, Toast.LENGTH_SHORT).show();
    }

    private ArrayList<ParseFile> doInBackground(List<String> photos) {
        for (String photo : photos) {
            bitmap = BitmapFactory.decodeFile(photo);
            bitmap = bitmap.createScaledBitmap(bitmap, 600, 400, false);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byteArray = stream.toByteArray();

            ParseFile file = new ParseFile("image.jpeg", byteArray);
            try {
                file.save();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            images.add(file);
        }
        return images;
    }

    private void onPostExecute(ArrayList<ParseFile> arrayList) {
        Toast.makeText(this, R.string.product_visibility_toast, Toast.LENGTH_SHORT).show();
        product.setPhotos(arrayList);
        product.saveInBackground();
        Log.i("End time", "SET");
    }

    @Override
    public void onClick(View view) {
        if ((view == linearLayout) || (view == addItemTextview)) {
            hideKeyboard();
        } else if (view == imageButton) {
            GalleryConfig config = new GalleryConfig.Build()
                    .limitPickPhoto(8)
                    .singlePhoto(false)
                    .hintOfPick("You can pick up to 8 pictures.")
                    .filterMimeTypes(new String[]{"image/*"})
                    .build();
            GalleryActivity.openActivity(AddProductActivity.this, 2, config);
        } else if (view == publishBtn) {

            product.setName(productNameField.getText().toString());
            product.setDescription(productDescriptionField.getText().toString());
            product.setPrice(productPriceField.getText().toString());
            product.setPhotos(images);
            product.setCity(ParseUser.getCurrentUser().get("city").toString());
            product.setSeller(ParseUser.getCurrentUser());

            product.saveInBackground(ex -> {
                if (ex == null) {
                    redirectToMainScreen();
                } else {
                    showErrorToast(ex.getMessage());
                }
            });
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //async task
        photos = (List<String>) data.getSerializableExtra(GalleryActivity.PHOTOS);
        startPhotoTreatmentTask(photos);
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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}
