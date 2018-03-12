package br.ufrpe.josed.inovacity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import br.ufrpe.josed.inovacity.adapters.PublicacaoAdapter;
import br.ufrpe.josed.inovacity.model.Publicacao;
import br.ufrpe.josed.inovacity.util.FireBaseDB;
import br.ufrpe.josed.inovacity.util.Mensagens;

public class FeedActivity extends Fragment{

    //FireBaseDB fireBaseDB = new FireBaseDB();

    private ConstraintLayout layout;
    private RecyclerView listaPublicacoes;
   // private static TextView txtLabelNome;
    private PublicacaoAdapter publicacaoAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view=inflater.inflate(R.layout.content_feed, container, false);

        listaPublicacoes = (RecyclerView) view.findViewById(R.id.recyclePubicacao);
        if(listaPublicacoes!=null){
            listaPublicacoes.setHasFixedSize(true);
            listaPublicacoes.setLayoutManager(new LinearLayoutManager(getContext()));
            FireBaseDB.listarTodasPublicacoes(listaPublicacoes);
        }else
            Mensagens.ToastCurto(getContext(),"Falha ao carregar lista!");

        return view;

    }

    //private UsuarioTempDAO dao;
    //PublicacaoDAO publicacaoDAO;

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher_inovacity_round);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        layout = (ConstraintLayout) findViewById(R.id.contentFeed);
//        txtLabelNome = (TextView) findViewById(R.id.txtLabelEntrar);

        listaPublicacoes = (RecyclerView) findViewById(R.id.recyclePubicacao);
        listaPublicacoes.setHasFixedSize(true);
        listaPublicacoes.setLayoutManager(new LinearLayoutManager(this));

        //  publicacaoDAO = new PublicacaoDAO(this);
        //  dao = new UsuarioTempDAO(this);

        FireBaseDB.listarTodasPublicacoes(listaPublicacoes);


    }*/

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        atualizar();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

      /*  dao.removerTodos();
        if (FireBaseDB.mAuth.getCurrentUser() != null) {
            dao.inserir(User.currentUser);
        }*/
    }


    public void atualizar() {
       FireBaseDB.listarTodasPublicacoes(listaPublicacoes);
        if (FireBaseDB.mAuth.getCurrentUser()!=null) {

            MainActivity.setLabel(FireBaseDB.mAuth.getCurrentUser().getEmail() == null ? "Entrar" : FireBaseDB.mAuth.getCurrentUser().getEmail());

        }
    }

   /* public void abrirCriarPublicacao(View v){
        Intent intent = new Intent(this, CriarPubliacao.class );
        startActivity(intent);
    }
    public void abrirMapa(View v){
        Intent intent = new Intent(this, MapaFeedActivity.class );
        startActivity(intent);
    }

    public void curtirPublicacao(View v) {

        Mensagens.SnackLongo(v, "Não implementado!", "OK");
    }*/



    public void  abrirPublicacaoDetalhes(View view){
        int i= listaPublicacoes.getChildViewHolder(view).getAdapterPosition();
        Publicacao p = publicacaoAdapter.getPublicacao(i);
        Intent intent = new Intent(getContext(), DetalhesPublicacao.class);
        intent.putExtra("Publicacao", p);
        startActivity(intent);


    }
}
