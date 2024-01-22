package com.example.pacinterviewtesting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pacinterviewtesting.movieList.MovieListActivity;
import com.example.pacinterviewtesting.sql.DatabaseHelper;
import com.example.pacinterviewtesting.sql.UserModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    EditText edtUserName;
    EditText edtPassword;
    TextView txtMasuk;
    TextView txtDaftar;
    List<UserModel> loadUser;
    DatabaseHelper savedbhelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        listener();

        savedbhelp = new DatabaseHelper(this);
    }

    private void listener() {

        txtMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUserName.getText().toString();
                String passwor = edtPassword.getText().toString();

                if (!user.equals("")) {

                    loadUser = new ArrayList<>();
                    loadUser.addAll(savedbhelp.getUser(user));
                }else {
                    Toast.makeText(MainActivity.this,"User cannot be empty",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!passwor.equals("")){
                    loadUser = new ArrayList<>();
                    loadUser.addAll(savedbhelp.getPassword(passwor));
                }
                if (loadUser.size()>0) {
                    Intent i = new Intent(MainActivity.this, MovieListActivity.class);
                    startActivity(i);
                    finish();
                }else {
                    Toast.makeText(MainActivity.this,"You have Wrong Passwor",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        txtDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    private void initUI() {

        txtDaftar = findViewById(R.id.txtDaftar);
        txtMasuk = findViewById(R.id.txtLogin);
        edtPassword = findViewById(R.id.edtPassword);
        edtUserName = findViewById(R.id.edtUserName);

    }
}