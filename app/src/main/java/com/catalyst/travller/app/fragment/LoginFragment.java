package com.catalyst.travller.app.fragment;

import android.content.Context;
import android.content.SharedPreferences;
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

/**
 * Created by jitendra.karma on 07/02/2016.
 */
public class LoginFragment extends Fragment {

    private SharedPreferences preferenceManager;
    private static final String PREF_NAME = "traveller";
    private SQLHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.layout_login, container, false);
        preferenceManager = getActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        dbHelper = new SQLHelper(getActivity());
        view.findViewById(R.id.submit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processLogin(view);
            }
        });
        return view;
    }

    private void processLogin(View v) {
        EditText edTxtLoginName = (EditText) v.findViewById(R.id.user_name);
        EditText edTxtLoginPassword = (EditText) v.findViewById(R.id.password);

        String login = edTxtLoginName.getText().toString();
        String loginPassword = edTxtLoginPassword.getText().toString();

        if (!ifValidLogin(login, loginPassword)) {
            return;
        }

        saveUser(login);
        Toast.makeText(getActivity(), "Logged in successfully", Toast.LENGTH_SHORT).show();
    }

    private boolean ifValidLogin(String login, String loginPassword) {
        if (login == null || login.isEmpty()) {
            Toast.makeText(getActivity(), "Username cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (loginPassword == null || loginPassword.isEmpty()) {
            Toast.makeText(getActivity(), "Password cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (loginPassword.length() < 4) {
            Toast.makeText(getActivity(), "Password is not valid", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!validateLogin(login, loginPassword)) {
            return false;
        }

        return true;
    }

    public SharedPreferences getPreferenceManager() {
        return preferenceManager;
    }

    private boolean validateLogin(String user, String password) {
        return dbHelper.validateUser(user, password);
    }

    private void saveUser(String user) {
        preferenceManager.edit().putString("userName", user).commit();
        getActivity().finish();
    }

    private void deleteUser(String user) {
        preferenceManager.edit().clear().commit();
    }
}
