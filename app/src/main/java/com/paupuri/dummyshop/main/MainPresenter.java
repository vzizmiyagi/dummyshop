package com.paupuri.dummyshop.main;


import com.parse.ParseException;
import com.parse.SignUpCallback;
import com.paupuri.dummyshop.model.User;

/**
 * Created by aziz on 1/23/17.
 */

public class MainPresenter implements MainContract.Presenter {

    private final MainContract.View view;

    User newUser = new User();

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void onClickSignUpBtn(String name, String username, String password, String city) {
        newUser.setName(name);
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setCity(city);

        newUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    view.redirectToLandingPage();
                } else {
                    view.showErrorToast(e.getMessage());
                }
            }
        });
    }
}
