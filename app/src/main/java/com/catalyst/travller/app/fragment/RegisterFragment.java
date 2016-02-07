package com.catalyst.travller.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.catalyst.travller.app.R;
import com.catalyst.travller.app.data.SQLHelper;
import com.catalyst.travller.app.data.UserInfoBean;
import com.catalyst.travller.app.utils.GeneralValidator;

/**
 * Created by jitendra.karma on 07/02/2016.
 */
public class RegisterFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.layout_registration, container, false);
        view.findViewById(R.id.submit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfoBean user = getUserDetails(view);
                if (user == null) {
                    return;
                }

                SQLHelper sq = new SQLHelper(getActivity());
                if (sq.insertUser(user)) {
                    Toast.makeText(getActivity(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                }
            }
        });
        return view;
    }

    private UserInfoBean getUserDetails(View view) {
        EditText edFName = (EditText) view.findViewById(R.id.first_name);
        EditText edLName = (EditText) view.findViewById(R.id.last_name);
        EditText edTxtEmail = (EditText) view.findViewById(R.id.email_address);
        EditText edTxtPassword = (EditText) view.findViewById(R.id.password);
        EditText edTxtRePassword = (EditText) view.findViewById(R.id.confirm_password);
        EditText edTxtMobile = (EditText) view.findViewById(R.id.mobile);

        if (!validateUser(edFName.getText().toString().toString(), edLName.getText().toString(), edTxtEmail.getText().toString(),
                edTxtPassword.getText().toString(), edTxtRePassword.getText().toString(), edTxtMobile.getText().toString())) {
            return null;
        }

        UserInfoBean registerUser = new UserInfoBean(edTxtEmail.getText().toString(), edTxtPassword.getText().toString(),
                edFName.getText().toString() + " " + edLName.getText().toString(), Integer.parseInt(edTxtMobile.getText().toString()));
        return registerUser;
    }

    private boolean validateUser(String fname, String lname, String email, String password, String rePassword, String phone) {
        if (fname == null || fname.isEmpty()) {
            Toast.makeText(getActivity(), "First Name cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (lname == null || lname.isEmpty()) {
            Toast.makeText(getActivity(), "Last Name cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (email == null || email.isEmpty()) {
            Toast.makeText(getActivity(), "Email cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!GeneralValidator.isValidEmail(email)) {
            Toast.makeText(getActivity(), "Email is not valid", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password == null || password.isEmpty()) {
            Toast.makeText(getActivity(), "Password cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.length() < 4) {
            Toast.makeText(getActivity(), "Password cannot be less than 4 chars", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (rePassword == null || rePassword.isEmpty()) {
            Toast.makeText(getActivity(), "Confirm Password cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!rePassword.equals(password)) {
            Toast.makeText(getActivity(), "Password does not match", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (phone == null || phone.isEmpty()) {
            Toast.makeText(getActivity(), "Mobile number cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!GeneralValidator.isValidMobile(phone)) {
            Toast.makeText(getActivity(), "Mobile number is not valid", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
