package com.example.pokebargo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    private Spinner spinner;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinnerCountries);
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, DadosCidades.countryNames));

        editText = findViewById(R.id.editTextPhone);

        findViewById(R.id.buttonContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = DadosCidades.countryAreaCodes[spinner.getSelectedItemPosition()];

                String number = editText.getText().toString().trim();

                if (number.isEmpty() || number.length() < 12) {
                    editText.setError("Número inválido!");
                    editText.requestFocus();
                    return;
                }

                String phoneNumber = "+" + code + number;

                Intent intent = new Intent(MainActivity.this, VerificarCelularActivity.class);
                intent.putExtra("phonenumber", phoneNumber);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(this, PerfilActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }
    }
}