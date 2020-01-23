package com.example.pokebargo.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokebargo.R;
import com.example.pokebargo.controler.BarAdapter;
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
        BarAdapter barAdapter;

        barList = new ArrayList<>();
        rcv_listaBares = findViewById(R.id.rcv_listaBares);

        // Construtor da classe Bar
        barList.add(new Bar("Bar do Juka", R.drawable.ic_launcher_background, "Venham se divertir com seus amigos!", "Rua das Palmeiras", 1));
        barList.add(new Bar("Bar do Leao", R.drawable.ic_launcher_background, "O melhor pokerstop", "Rua Amazonas", 2));
        barList.add(new Bar("Bar Teste", R.drawable.ic_launcher_background, "Venham que aqui é tudo mais barato", "Rua 5 de maio", 3));
        barList.add(new Bar("Bar da Ambev", R.drawable.ic_launcher_background, "Só os mais fortes que ficam até o final", "Rua Bahia", 4));
        barList.add(new Bar("Bar do Leo", R.drawable.ic_launcher_background, "...", "Rua Benjamin Constante", 5));
        barList.add(new Bar("Bar Mutante", R.drawable.ic_launcher_background, "Biiiirr", "Rua dos Mutantes", 4));

        // Construtor da classe Adapter
        barAdapter = new BarAdapter(this, barList);

        // Define a quantidade de cards a ser exibido no layout
        rcv_listaBares.setLayoutManager(new GridLayoutManager(this, 1));

        // Seta o BarAdapter no recycle view
        rcv_listaBares.setAdapter(barAdapter);

        // Pegando o clique do Usuário no recycler view

    }
}
