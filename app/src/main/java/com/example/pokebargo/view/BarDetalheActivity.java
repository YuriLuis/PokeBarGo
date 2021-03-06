package com.example.pokebargo.view;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokebargo.R;
import com.example.pokebargo.controler.ProdutoAdapter;
import com.example.pokebargo.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class BarDetalheActivity extends AppCompatActivity {

    private static final int RESULT_PICK_CONTACT = 1;
    private Context mContext;
    RecyclerView rcv_listaProduto;
    List<Produto> produtosList;
    TextView tv_nomeBar, tv_endereco;
    RatingBar rb_classificacao;
    ImageView img_bar;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RESULT_PICK_CONTACT:
                    contactPicked(data);
                    break;
            }
        } else {
            Toast.makeText(this, "Nenhum contato selecionado!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_detalhe);
        ProdutoAdapter produtoAdapter;

        produtosList = new ArrayList<>();
        rcv_listaProduto = findViewById(R.id.rcv_listaProdutos);

        mContext = this;

        // Variáveis Chaves
        Intent intent = getIntent();
        String nomeBar = intent.getExtras().getString("NomeBar");
        String endereco = intent.getExtras().getString("EnderecoBar");
        Float classificao = intent.getExtras().getFloat("ClassificaoBar");
        int imgBar = intent.getExtras().getInt("ImagemBar");

        tv_nomeBar = findViewById(R.id.tv_nomeBar);
        tv_endereco = findViewById(R.id.tv_endereco);
        rb_classificacao = findViewById(R.id.rb_classificacao);
        img_bar = findViewById(R.id.img_bar);

        tv_nomeBar.setText("   " + nomeBar);
        tv_endereco.setText("   " + endereco);
        rb_classificacao.setRating(classificao);
        img_bar.setImageResource(imgBar);

        img_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MapsActivity.class);
                startActivity(intent);
            }
        });

        produtosList.add(new Produto("Fusion", 5.50, 0));
        produtosList.add(new Produto("Original", 1.50, 0));
        produtosList.add(new Produto("Brahma", 10.0, 0));
        produtosList.add(new Produto("Água sem gás", 2.50, 0));
        produtosList.add(new Produto("Água com gás", 2.50, 0));

        produtoAdapter = new ProdutoAdapter(this, produtosList);

        rcv_listaProduto.setLayoutManager(new GridLayoutManager(this, 1));

        rcv_listaProduto.setAdapter(produtoAdapter);


    }

    private void contactPicked(Intent data) {
        Cursor cursor = null;
        try {
            String phoneNo = null;
            Uri uri = data.getData();
            cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();
            int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            phoneNo = cursor.getString(phoneIndex);
            Toast.makeText(this, "Endereço enviado para o contato " + phoneNo, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void compartilhar(View view) {
        Intent in = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(in, RESULT_PICK_CONTACT);
    }
}
