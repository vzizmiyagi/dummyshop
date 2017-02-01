package com.paupuri.dummyshop.addeditproduct;

import com.parse.ParseFile;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aziz on 2/1/17.
 */

public class AddEditProductContract {
    interface View{
        void redirectToMainScreen();

        void showEmptyImageError();

        void imagesAreBeingUploaded();

        void imagesHaveBeenUploaded();

        void showErrorToast(String e);
    }

    interface Presenter{
        void onPhotosAddition(List<String> images);

        void onClickPublishBtnonClickPublishBtn(String productName, String productDescription,
                                                String productPrice, ArrayList<ParseFile> productPhotos,
                                                String productCity, ParseUser productSeller);
    }
}