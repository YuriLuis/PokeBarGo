package com.example.pokebargo.activity;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokebargo.R;

import java.net.URI;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContatosAdapter extends RecyclerView.Adapter<ContatosAdapter.MyViewHolder> {

    private List<Usuario> contatos;
    private Context context;

    public ContatosAdapter(List<Usuario> listaContatos, Context context) {
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

        Usuario usuario = contatos.get( position );

        holder.nome.setText(usuario.getNome());
        holder.email.setText(usuario.getEmail());
        holder.telefone.setText(usuario.getTelefone());

        usuario.getFoto();

        if ( usuario.getFoto() != null ){

            Uri uri = Uri.parse( usuario.getFoto() );
            Glide.with( context ).load( uri ).into( holder.foto );
        }else {

            holder.foto.setImageResource(R.drawable.ic_launcher_foreground);
        }

    }

    @Override
    public int getItemCount() {
        return contatos.size();
    }


    /**
     * @Class MyViewHolder*/
    public class MyViewHolder extends RecyclerView.ViewHolder{

        CircleImageView foto;
        TextView nome,email,telefone;


        public MyViewHolder(View itemView) {
            super(itemView);

            foto     = itemView.findViewById(R.id.imgContato);
            nome     = itemView.findViewById(R.id.tvNome);
            email    = itemView.findViewById(R.id.tvEmail);
            telefone = itemView.findViewById(R.id.tvTelefone);
        }
    }
}
