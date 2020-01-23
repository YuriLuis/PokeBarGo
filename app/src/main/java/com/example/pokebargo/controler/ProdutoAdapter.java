package com.example.pokebargo.controler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokebargo.R;
import com.example.pokebargo.model.Produto;

import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.MyViewHolder> {

    private static List<Produto> listaProduto;
    private Context context;

    // Construtor da classe BarAdapter
    public ProdutoAdapter(Context context, List<Produto> listaProduto) {
        this.context = context;
        ProdutoAdapter.listaProduto = listaProduto;
    }


    // Infla a view
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.rcv_produtos_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // Utiliza o holder para pegar as informações do model Bar
        holder.tv_nome.setText("   " + listaProduto.get(position).getNome());
        holder.tv_valor.setText("   " + listaProduto.get(position).getValor());
        holder.cv_item_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(context, DetalhesProdutoActivity.class);
                intent.putExtra("NomeProduto", listaProdutosFiltrado.get(position).getNomeProduto());
                context.startActivity(intent);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaProduto.size();
    }

    // Informa as views a serem utilizadas
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_nome, tv_valor;
        ImageView iv_img_produto;
        CardView cv_item_bar;

        public MyViewHolder(View itemView) {
            super(itemView);
            // Atribuição
            tv_nome = itemView.findViewById(R.id.tv_nome);
            tv_valor = itemView.findViewById(R.id.tv_valor);
            iv_img_produto = itemView.findViewById(R.id.img_produto);
            cv_item_bar = itemView.findViewById(R.id.cv_item_bar);

        }
    }
}