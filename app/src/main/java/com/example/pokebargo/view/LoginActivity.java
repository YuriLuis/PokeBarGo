package com.example.pokebargo.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pokebargo.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        verificaUsuarioLogado();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
    }

    // Abre a tela de autenticador
    public void autenticador(View view) {
        startActivity(new Intent(LoginActivity.this, AutenticadorActivity.class));
    }

    // Caso o usuário esteja conectado ele redireciona para a tela principal
    public void verificaUsuarioLogado() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }
}