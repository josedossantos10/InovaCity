package br.ufrpe.josed.inovacity.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.josed.inovacity.R;
import br.ufrpe.josed.inovacity.model.Publicacao;

/**
 * Created by josed on 17/02/2018.
 */

public class PublicacaoAdapter extends RecyclerView.Adapter<PublicacaoViewHolder> {

    List<Publicacao> publicacaos;

    public PublicacaoAdapter(List<Publicacao> publicacaos) {
        this.publicacaos = publicacaos;
    }

    @Override
    public PublicacaoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.caixa_publicacao,parent,false);
        PublicacaoViewHolder holder = new PublicacaoViewHolder(view);

        return holder;

    }

    @Override
    public void onBindViewHolder(PublicacaoViewHolder holder, int position) {
        if ((publicacaos!=null)&&(!publicacaos.isEmpty())){
            Publicacao publicacao = publicacaos.get(position);

            holder.txtTitulo.setText(publicacao.getTitulo());
        }
    }

    @Override
    public int getItemCount() {
        if ((publicacaos!=null)&&(!publicacaos.isEmpty())){
            return publicacaos.size();

        }else
            return 0;    }
}

class PublicacaoViewHolder extends RecyclerView.ViewHolder{

    public TextView txtTitulo;


    public PublicacaoViewHolder(View itemView) {
        super(itemView);
        txtTitulo = (TextView) itemView.findViewById(R.id.textTitulo);
    }
}
