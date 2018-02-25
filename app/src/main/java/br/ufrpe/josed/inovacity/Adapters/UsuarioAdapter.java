package br.ufrpe.josed.inovacity.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.ufrpe.josed.inovacity.R;
import br.ufrpe.josed.inovacity.model.Usuario;

/**
 * Created by josed on 17/02/2018.
 */

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.ViewHolderUsuario> {

    private List<Usuario> dados;

    public UsuarioAdapter(List<Usuario> dados) {
        this.dados = dados;
    }

    @Override
    public ViewHolderUsuario onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.caixa_usuario,parent,false);

        ViewHolderUsuario viewHolderUsuario = new ViewHolderUsuario(view);


        return viewHolderUsuario;
    }


    @Override
    public void onBindViewHolder(ViewHolderUsuario holder, int position) {
        if ((dados!=null)&&(!dados.isEmpty())){
            Usuario usuario = dados.get(position);

            holder.txtNome.setText(usuario.getNome());
            holder.txtEmail.setText(usuario.getEmail());
            holder.txtCpf.setText(usuario.getCpf());
            holder.txtSenha.setText(usuario.getSenha());
            holder.txtRua.setText(usuario.getRua());
        }

    }

    @Override
    public int getItemCount() {
        if ((dados!=null)&&(!dados.isEmpty())){
            return dados.size();

        }else
            return 0;
    }

    public class ViewHolderUsuario extends RecyclerView.ViewHolder{
        public TextView txtNome;
        public TextView txtEmail;
        public TextView txtCpf;
        public TextView txtSenha;
        public TextView txtRua;



        public ViewHolderUsuario(View itemView) {
            super(itemView);

            txtNome = (TextView)itemView.findViewById(R.id.textNome);
            txtEmail=(TextView)itemView.findViewById(R.id.textEmail);
            txtCpf=(TextView)itemView.findViewById(R.id.textCpf);
            txtSenha=(TextView)itemView.findViewById(R.id.textSenha);
            txtRua=(TextView)itemView.findViewById(R.id.textRua);


        }
    }
}
