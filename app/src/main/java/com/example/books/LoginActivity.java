package com.example.books;

import static com.example.books.MenuMainActivity.CHAVE_EMAIL_PREFS;
import static com.example.books.MenuMainActivity.USERS_PREFS;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.books.databinding.ActivityLoginBinding;


public class LoginActivity extends AppCompatActivity {
    EditText edEmail, edPassword;

    private ActivityLoginBinding binding;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //setContentView(R.layout.activity_login);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;



        });

        sharedPreferences = getSharedPreferences(USERS_PREFS, Context.MODE_PRIVATE);
        String email = sharedPreferences.getString(CHAVE_EMAIL_PREFS, "");

        if (!email.isEmpty()){
            login(email);

        }




    }

    public boolean isEmailValido(String email){

        if (email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return true;
        }
        return false;
    };

    public boolean isPasswordValida(String password){

        if (password != null && password.length() >= 4){
            return true;
        }
        return  false;

    }

    public void onClickLogin(View view) {
        String email = binding.edtEmailAddress.getText().toString();
        String password = binding.edtPassword.getText().toString();

        if (isEmailValido(email)  ){
            if ( isPasswordValida(password)){

                Toast.makeText(LoginActivity.this, R.string.login_efetuado, Toast.LENGTH_SHORT).show();

                login(email);

            }else{
               binding.edtPassword.setError(getString(R.string.Err_password_invalida));

            }
        }else {
            binding.edtEmailAddress.setError(getString(R.string.err_email_errado));

        }



    }

    private void login(String email) {
        Intent intent = new Intent(this, MenuMainActivity.class);
        intent.putExtra(MenuMainActivity.EMAIL, email);
        startActivity(intent);
    }


}