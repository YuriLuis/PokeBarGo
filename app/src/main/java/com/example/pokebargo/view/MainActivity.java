package com.example.pokebargo.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokebargo.R;
import com.example.pokebargo.controler.BarAdapter;
import com.example.pokebargo.model.Bar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Bar> barList;
    RecyclerView rcv_listaBares;
    FloatingActionButton addCadastro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addCadastro = findViewById(R.id.addCadastro);

        addCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Cadastro_Bar.class);
                startActivity(i);
            }
        });
        carregaListaBares();
    }

    public void carregaListaBares() {
        BarAdapter barAdapter;

        barList = new ArrayList<>();
        rcv_listaBares = findViewById(R.id.rcv_listaBares);

        // Construtor da classe Bar
        barList.add(new Bar("Moitilas Bar", R.mipmap.motilas, "Cardápio variado com diversas opções de hambúrg...", "R. Santa Quitéria, 107", 1));
        barList.add(new Bar("Factory Coffee Bar", R.mipmap.factory, "Cafés gourmets, sanduíches e cervejas artesanai...", "Av. Pres. Castelo Branco, 671", 2));
        barList.add(new Bar("The Basement English Pub", R.mipmap.basement, "Pratos variados e cervejas artesanais, além de....", "R. Paul Hering, 35", 3));
        barList.add(new Bar("Bar Restaurante Adriana", R.mipmap.adriana, "Só os mais fortes que ficam até o final.", "R. Curt Hering, 240", 4));
        barList.add(new Bar("Tunga Choperia", R.mipmap.tunga, "Ponto de encontro com mesinhas ao ar livre e mú...", "R. Quinze de Novembro, 1020", 5));
        barList.add(new Bar("Obs Bar e Restaurante", R.mipmap.obs, "Casa noturna com shows variados de sertanejo, p...", "R. Antônio da Veiga, 136", 4));

        // Construtor da classe Adapter
        barAdapter = new BarAdapter(this, barList);

        // Define a quantidade de cards a ser exibido no layout
        rcv_listaBares.setLayoutManager(new GridLayoutManager(this, 1));

        // Seta o BarAdapter no recycle view
        rcv_listaBares.setAdapter(barAdapter);

    }

    public void abriPerfil(View view) {
        startActivity(new Intent(MainActivity.this, TeladeUsuario.class));
        finish();
    }
}
