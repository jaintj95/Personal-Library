package com.tj.personallibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity{

    private EditText mEmailField;
    private EditText mPasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailField = findViewById(R.id.edit_text_login_email);
        mPasswordField = findViewById(R.id.edit_text_login_password);

        TextView backToRegister = findViewById(R.id.textView_backToRegister);
        backToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button login = findViewById(R.id.button_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    private void signIn()
    {
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();

        if(email.isEmpty() || password.isEmpty())
        {
            Toast.makeText(this,"Email/Password cannot be empty",Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d("LoginActivity","Email is "  +email);
        Log.d("LoginActivity","Password is " +password);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Log.d("LoginActivity","Sign in successful");
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            LoginActivity.this.startActivity(intent);
                        }

                        else
                        {
                            Log.d("LoginActivity","Login failed.");
                            Toast.makeText(LoginActivity.this,"                 Login failed.\nUsername or Password incorrect.",Toast.LENGTH_SHORT);
                        }
                    }
                });
    }
}
