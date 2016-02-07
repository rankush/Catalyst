package com.catalyst.travller.app.utils;

import android.util.Patterns;

/**
 * Created by jitendra.karma on 07/02/2016.
 */
public class GeneralValidator {
    public final static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    public final static boolean isValidMobile(String phone) {
        if (phone == null) {
            return false;
        } else {
            return Patterns.PHONE.matcher(phone).matches();
        }
    }
}
