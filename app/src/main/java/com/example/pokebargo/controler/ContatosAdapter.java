package com.example.pokebargo.controler;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokebargo.R;
import com.example.pokebargo.model.Contato;
import com.example.pokebargo.model.Usuario;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContatosAdapter extends RecyclerView.Adapter<ContatosAdapter.MyViewHolder> {

    private List<Contato> contatos;
    private Context context;

    public ContatosAdapter(ArrayList<Contato> listaContatos, Context context) {
        this.contatos = listaContatos;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from( parent.getContext() ).inflate(R.layout.adapter_contatos, parent, false);

        return  new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {

        Contato contato = contatos.get( position );

        holder.nome.setText(contato.getNomeContato());

    }

    @Override
    public int getItemCount() {
        return contatos.size();
    }


    /**
     * @Class MyViewHolder*/
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CircleImageView foto;
        TextView nome,email,telefone;

        /**
         * Construtor da classe MyViewHolder
         *
         * @author Yuri Luis Garcia Pereira <yuri.luizg@hotmail.com>
         * @since 1.0.0
         *
         * @param itemView que é um item, utilizado para conseguir fazer
         *                 interações com esta view
         */
        public MyViewHolder(View itemView) {
            super(itemView);

            nome     = itemView.findViewById(R.id.tvNome);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
