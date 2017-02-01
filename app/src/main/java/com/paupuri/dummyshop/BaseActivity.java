package com.paupuri.dummyshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.paupuri.dummyshop.landing.LandingActivity;
import com.paupuri.dummyshop.main.MainActivity;

/**
 * Created by aziz on 2/1/17.
 */

public class BaseActivity extends AppCompatActivity {

    public void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }


    public void redirectToMainScreen() {
        Intent intent = new Intent(getApplicationContext(), LandingActivity.class);
        startActivity(intent);
    }

    public void redirectToSignupScreen() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void showErrorToast(String e) {
        Toast.makeText(this, e, Toast.LENGTH_SHORT).show();
    }

    public void imagesAreBeingUploaded() {
        Log.i("Start time", "got it?");
        Toast.makeText(this, R.string.image_formatting_toast, Toast.LENGTH_SHORT).show();
    }

    public void imagesHaveBeenUploaded() {
        Log.i("End time", "less than 3s please");
        Toast.makeText(this, R.string.product_visibility_toast, Toast.LENGTH_SHORT).show();
    }
}
