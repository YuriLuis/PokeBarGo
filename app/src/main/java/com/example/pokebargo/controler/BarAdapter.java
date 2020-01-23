package com.example.pokebargo.controler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokebargo.R;
import com.example.pokebargo.model.Bar;
import com.example.pokebargo.model.Produto;
import com.example.pokebargo.view.BarDetalheActivity;

import java.util.List;

public class BarAdapter extends RecyclerView.Adapter<BarAdapter.MyViewHolder> {

    private static List<Bar> listaBar;
    private static List<Produto> listaProdutos;
    private Context context;

    // Construtor da classe BarAdapter
    public BarAdapter(Context context, List<Bar> listaBar) {
        this.context = context;
        BarAdapter.listaBar = listaBar;
    }


    // Infla a view
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.rcv_bar_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // Utiliza o holder para pegar as informações do model Bar
        holder.tv_nome.setText("   " + listaBar.get(position).getNome());
        holder.tv_endereco.setText("   " + listaBar.get(position).getEndereco());
        holder.tv_descricao.setText("  " + listaBar.get(position).getDescricao());
        holder.rb_classificacao.setRating(listaBar.get(position).getClassificacao());
        holder.iv_img_bar.setImageResource(listaBar.get(position).getUrl_img());
        holder.cv_item_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BarDetalheActivity.class);
                intent.putExtra("NomeBar", listaBar.get(position).getNome());
                intent.putExtra("EnderecoBar", listaBar.get(position).getEndereco());
                intent.putExtra("DescricaoBar", listaBar.get(position).getDescricao());
                intent.putExtra("ClassificaoBar", listaBar.get(position).getClassificacao());
                intent.putExtra("ImagemBar", listaBar.get(position).getUrl_img());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaBar.size();
    }

    // Informa as views a serem utilizadas
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_nome, tv_endereco, tv_descricao;
        ImageView iv_img_bar;
        RatingBar rb_classificacao;
        CardView cv_item_bar;

        public MyViewHolder(View itemView) {
            super(itemView);
            // Atribuição
            tv_nome = itemView.findViewById(R.id.tv_nome);
            tv_endereco = itemView.findViewById(R.id.tv_endereco);
            tv_descricao = itemView.findViewById(R.id.tv_descricao);
            iv_img_bar = itemView.findViewById(R.id.img_bar);
            rb_classificacao = itemView.findViewById(R.id.rb_classificacao);
            cv_item_bar = itemView.findViewById(R.id.cv_item_bar);

        }
    }
}