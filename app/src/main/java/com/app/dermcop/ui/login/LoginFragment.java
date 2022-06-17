package com.app.dermcop.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.app.dermcop.MainActivity;
import com.app.dermcop.MainActivity1;
import com.app.dermcop.R;
import com.app.dermcop.LoginActivity;
import com.app.dermcop.Storage.sql.DatabaseHelper;
import com.app.dermcop.ui.home.HomeFragment;


public class LoginFragment extends Fragment {
    LoginActivity main;
    View view;
    EditText emailEditText;
    EditText passwordEditText;
    Button loginButton;
    TextView forgotPassword;
    TextView registerButton;
    String input_email;
    String input_password;
    DatabaseHelper databaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login_fragment_login, container, false);
        emailEditText = view.findViewById(R.id.editTextEmail);
        passwordEditText = view.findViewById(R.id.editTextPassword2);
        registerButton = view.findViewById(R.id.registerButton);
        loginButton = view.findViewById(R.id.loginButton);
        forgotPassword = view.findViewById(R.id.forgotPassword);
        main = (LoginActivity)getActivity();

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Button listeners
        loginButton.setOnClickListener(v -> {
            input_email = emailEditText.getText().toString();
            input_password = passwordEditText.getText().toString();
            login(input_email, input_password);
        });

        registerButton.setOnClickListener(v -> main.pushNewFragment(new RegisterFragment()));

        forgotPassword.setOnClickListener(v -> main.pushNewFragment(new PasswordResetFragment()));
    }

    private void login(String email, String password) {
//        Call<LoginResponse> call = RetrofitClient
//                .getInstanceAuth(email, password)
//                .getApi()
//                .userLogin();
//
//        call.enqueue(new Callback<LoginResponse>() {
//            @Override
//            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//                LoginResponse loginResponse = response.body();

//                if(response.code() == 200) {
//                    if (loginResponse != null) {
//                        String authToken = loginResponse.getAccessToken();
//                        String refreshToken = loginResponse.getRefreshToken();
//                        SharedPrefManager.getInstance(getActivity()).saveAccessToken(authToken);
//                        SharedPrefManager.getInstance(getActivity()).saveRefreshToken(refreshToken);
//                    }
            databaseHelper = new DatabaseHelper(getContext());


            if(databaseHelper.checkUser(email.trim(), password.trim())){
                Toast.makeText(getActivity(), "Email: "+email+"\nPassword: "+password, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("email_key",email);
                startActivity(intent);

            }else{
                Toast.makeText(getActivity(), "Error Logging in", Toast.LENGTH_SHORT).show();
            }










    }
}
