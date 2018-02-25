package br.ufrpe.josed.inovacity.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
            if (publicacao.getImagem1()!=null){
                holder.imageView1.setImageBitmap(publicacao.getImagem1());
            }else{
                holder.imageView1.setImageResource(R.drawable.logo_inovacity2);
            }
        }
    }

    public Publicacao getPublicacao(int position){
        return publicacaos.get(position);

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
    public ImageView imageView1;


    public PublicacaoViewHolder(View itemView) {
        super(itemView);
        txtTitulo = (TextView) itemView.findViewById(R.id.textTitulo);
        imageView1 = (ImageView) itemView.findViewById(R.id.imagePublicacao);

    }
}
