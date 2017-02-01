package com.paupuri.dummyshop.main;

/**
 * Created by aziz on 1/23/17.
 */

public class MainContract {
    interface View{
        void redirectToLandingPage();

        void showErrorToast(String e);
    }

    interface Presenter{
        void onClickSignUpBtn(String name, String username, String password, String city);
    }
}
