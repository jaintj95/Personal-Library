package com.tj.personallibrary;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

public class MainActivity extends AppCompatActivity {

    private EditText mEmailField;
    private EditText mPasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        ActionBar actionbar = getSupportActionBar();
//        actionbar.setDisplayHomeAsUpEnabled(true);
//        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mEmailField = findViewById(R.id.edit_text_email);
        mPasswordField = findViewById(R.id.edit_text_password);

        Button registerButton = findViewById(R.id.button_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
            }
        });

        TextView textView = findViewById(R.id.text_view_login);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }

    private void  createAccount()
    {

        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();

        if(email.isEmpty() || password.isEmpty())
        {
            Toast.makeText(this,"Email/Password cannot be empty",Toast.LENGTH_SHORT).show();
            return;
        }

        if(password.length() < 6)
        {
            Toast.makeText(this,"  Password needs to be \natleast 6 characters long",Toast.LENGTH_LONG).show();
            return;
        }
        Log.d("FirebaseRegistration","Email is " +email);
        Log.d("FirebaseRegistration", "Password is " +password);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Log.d("FirebaseRegistration", "create user success.");
                            Toast.makeText(MainActivity.this,"Registration Successful. You can login now",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                        else
                        {
                            Log.w("FirebaseRegistration","create user failure.", task.getException());
                            Toast.makeText(MainActivity.this,"Authentication failed.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
