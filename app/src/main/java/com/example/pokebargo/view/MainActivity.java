package com.example.pokebargo.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokebargo.R;
import com.example.pokebargo.controler.BarAdapter;
import com.example.pokebargo.controler.SmsFlash;
import com.example.pokebargo.model.Bar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Bar> barList;
    RecyclerView rcv_listaBares;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        barList = new ArrayList<>();
        rcv_listaBares = findViewById(R.id.rcv_listaBares);

        // Construtor da classe Bar
        barList.add(new Bar("Bar do Juka", "", "Venham se divertir com seus amigos!", "Rua das Palmeiras", 1));
        barList.add(new Bar("Bar do Leao", "", "Venham se divertir com seus amigos!", "Rua das Palmeiras", 2));
        barList.add(new Bar("Bar Teste", "", "Venham se divertir com seus amigos!", "Rua das Palmeiras", 3));
        barList.add(new Bar("Bar da Ambev", "", "Venham se divertir com seus amigos!", "Rua das Palmeiras", 4));
        barList.add(new Bar("Bar do Leo", "", "Venham se divertir com seus amigos!", "Rua das Palmeiras", 5));

        // Construtor da classe Adapter
        BarAdapter barAdapter = new BarAdapter(this, barList);

        // Define a quantidade de cards a ser exibido no layout
        rcv_listaBares.setLayoutManager(new GridLayoutManager(this, 1));

        // Seta o BarAdapter no recycle view
        rcv_listaBares.setAdapter(barAdapter);
    }
}
