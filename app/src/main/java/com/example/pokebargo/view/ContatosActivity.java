package com.example.pokebargo.view;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokebargo.R;
import com.example.pokebargo.model.Contato;
import com.example.pokebargo.model.Usuario;
import com.example.pokebargo.controler.ContatosAdapter;

import java.util.ArrayList;

public class ContatosActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> , AdapterView.OnItemClickListener{

    private RecyclerView recyclerViewListaContatos;
    private ContatosAdapter contatosAdapter;
    private ArrayList<Contato> listaContatos = new ArrayList<Contato>();
    private Usuario usuario;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contatos_recycler_view);


        recyclerViewListaContatos = findViewById( R.id.recyclerViewListaContatos );

        //Configurando Adapter
        contatosAdapter = new ContatosAdapter( listaContatos , getApplicationContext() );

        //Configurando RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( getApplicationContext() );
        recyclerViewListaContatos.setLayoutManager( layoutManager );
        recyclerViewListaContatos.setHasFixedSize( true );
        recyclerViewListaContatos.setAdapter( contatosAdapter );


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }



}

