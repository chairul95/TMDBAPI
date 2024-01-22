package com.example.pacinterviewtesting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pacinterviewtesting.sql.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {

    private TextView txtRegis;
    private EditText edtUserName;
    private EditText edtPassword;
    DatabaseHelper savedbhelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initUI();
        listener();
        savedbhelp = new DatabaseHelper(this);
    }

    private void listener() {
        txtRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUserName.getText().toString();
                String password = edtPassword.getText().toString();
                if (user.equals("")||password.equals("")){
                    Toast.makeText(RegisterActivity.this,"Sorry! you must fill the form",Toast.LENGTH_SHORT).show();
                    return;
                }
                savedbhelp.insertData(user,password);
                Toast.makeText(RegisterActivity.this,"Register Succes",Toast.LENGTH_SHORT).show();
                finish();

            }
        });
    }

    private void initUI() {

        txtRegis = findViewById(R.id.txtRegister);
        edtPassword = findViewById(R.id.edtPassword);
        edtUserName = findViewById(R.id.edtUserName);
    }
}