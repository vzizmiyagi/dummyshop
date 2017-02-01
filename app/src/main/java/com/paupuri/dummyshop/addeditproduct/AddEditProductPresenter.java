package com.paupuri.dummyshop.addeditproduct;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.paupuri.dummyshop.model.Product;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by aziz on 2/1/17.
 */

public class AddEditProductPresenter implements AddEditProductContract.Presenter {

    private final AddEditProductContract.View view;

    private Bitmap bitmap;
    private byte[] byteArray;
    private ArrayList<ParseFile> images;
    private Product newProduct = new Product();

    public AddEditProductPresenter(AddEditProductContract.View view) {
        this.view = view;
    }


    @Override
    public void onPhotosAddition(List<String> images) {
        resizeAndConvertPhotos(images);
    }

    @Override
    public void onClickPublishBtnonClickPublishBtn(String productName, String productDescription,
                                                   String productPrice, ArrayList<ParseFile> productImages,
                                                   String productCity, ParseUser productSeller) {
        newProduct.setName(productName);
        newProduct.setDescription(productDescription);
        newProduct.setPrice(productPrice);
        newProduct.setPhotos(productImages);
        newProduct.setCity(productCity);
        newProduct.setSeller(productSeller);
        newProduct.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    view.redirectToMainScreen();
                } else {
                    view.showErrorToast(e.getMessage());
                }
            }
        });
    }

    private void resizeAndConvertPhotos(List<String> input) {
        images = new ArrayList<ParseFile>();

        Observable.just(input)
                .map(this::doInBackground)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(this::onPreExecute)
                .subscribe(this::onPostExecute);
    }

//    private ArrayList<ParseFile> resizeAndConvertPhotos(List<String> input) {
//        images = new ArrayList<ParseFile>();
//        Observable.just(input)
//                .map(this::doInBackground)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(this::onPreExecute)
//                .subscribe(this::onPostExecute);
//        return images;
//    }

    private void onPreExecute() {
        view.imagesAreBeingUploaded();
    }

    private ArrayList<ParseFile> doInBackground(List<String> photos) {
        for (String photo : photos) {
            bitmap = BitmapFactory.decodeFile(photo);
            //bitmap = bitmap.createScaledBitmap(bitmap, 600, 400, false);
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
        view.imagesHaveBeenUploaded();
        newProduct.setPhotos(arrayList);
        newProduct.saveInBackground();
    }
}