package com.paupuri.dummyshop.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseUser;
import com.paupuri.dummyshop.R;
import com.paupuri.dummyshop.landing.LandingActivity;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    MainContract.Presenter presenter;

    Button signUpBtn;
    EditText nameField;
    EditText passwordField;
    EditText usernameField;
    EditText cityField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ParseUser.getCurrentUser() != null) {
            redirectToLandingPage();
        }

        signUpBtn = (Button) findViewById(R.id.signUp);
        nameField = (EditText) findViewById(R.id.nameField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        usernameField = (EditText) findViewById(R.id.username);
        cityField = (EditText) findViewById(R.id.cityField);

        presenter = new MainPresenter(this);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickSignUpBtn(nameField.getText().toString(),
                                            usernameField.getText().toString(),
                                            passwordField.getText().toString(),
                                            cityField.getText().toString());
            }
        });
    }

    @Override
    public void redirectToLandingPage() {
        Intent intent = new Intent(getApplicationContext(), LandingActivity.class);
        startActivity(intent);
    }

    @Override
    public void showErrorToast(String e) {
            Toast.makeText(this, e, Toast.LENGTH_SHORT).show();
    }
}
