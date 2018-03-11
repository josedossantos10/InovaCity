package br.ufrpe.josed.inovacity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

import br.ufrpe.josed.inovacity.adapters.PublicacaoAdapter;
import br.ufrpe.josed.inovacity.model.Publicacao;
import br.ufrpe.josed.inovacity.util.FireBaseDB;
import br.ufrpe.josed.inovacity.util.Mensagens;

public class FeedActivity extends AppCompatActivity{

    //FireBaseDB fireBaseDB = new FireBaseDB();

    private ConstraintLayout layout;
    private RecyclerView listaPublicacoes;
    private static TextView txtLabelNome;
    private PublicacaoAdapter publicacaoAdapter;

    //private UsuarioTempDAO dao;
    //PublicacaoDAO publicacaoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher_inovacity_round);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        layout = (ConstraintLayout) findViewById(R.id.contentFeed);
        txtLabelNome = (TextView) findViewById(R.id.txtLabelEntrar);

        listaPublicacoes = (RecyclerView) findViewById(R.id.recyclePubicacao);
        listaPublicacoes.setHasFixedSize(true);
        listaPublicacoes.setLayoutManager(new LinearLayoutManager(this));

        //  publicacaoDAO = new PublicacaoDAO(this);
        //  dao = new UsuarioTempDAO(this);

        FireBaseDB.listarTodasPublicacoes(listaPublicacoes);


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = FireBaseDB.mAuth.getCurrentUser();
        if (currentUser!=null){
            setLabel(currentUser.getEmail()==null?"Entrar":currentUser.getEmail());

        }

        //updateUI(currentUser);

     /*   Usuario u = dao.recuperar();
        if(u!=null){
            User.currentUser = u;
            setLabel(u.getNome());
        }*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

      /*  dao.removerTodos();
        if (FireBaseDB.mAuth.getCurrentUser() != null) {
            dao.inserir(User.currentUser);
        }*/
    }


    public void atualizar() {
        //listaPublicacoes.setAdapter(new PublicacaoAdapter(publicacaoDAO.listarTodos()));
        FireBaseDB.listarTodasPublicacoes(listaPublicacoes);
        if (FireBaseDB.mAuth.getCurrentUser()!=null) {

            setLabel(FireBaseDB.mAuth.getCurrentUser().getEmail() == null ? "Entrar" : FireBaseDB.mAuth.getCurrentUser().getEmail());

        }
    }

    public void abrirCriarPublicacao(View v){
        Intent intent = new Intent(this, CriarPubliacao.class );
        startActivity(intent);
    }
    public void abrirMapa(View v){
        Intent intent = new Intent(this, MapaFeedActivity.class );
        startActivity(intent);
    }

    public void abrirLogin(View v){

        if (FireBaseDB.mAuth.getCurrentUser() != null) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Sair");
            dialog.setMessage("Você já está conectado como "+FireBaseDB.mAuth.getCurrentUser().getEmail()+". Deseja realmente sair?");
            dialog.setNegativeButton("Cancelar",null);
            dialog.setPositiveButton("SAIR",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    FireBaseDB.mAuth.signOut();
                    //  dao.removerTodos();
                    FeedActivity.setLabel("ENTRAR");
                }

            });
            dialog.show();

        }else{
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    public void curtirPublicacao(View v) {

        Mensagens.SnackLongo(v, "Não implementado!", "OK");
    }

    public static void setLabel(String nome){
        txtLabelNome.setText(nome);

    }

    public void  abrirPublicacaoDetalhes(View view){
        int i= listaPublicacoes.getChildViewHolder(view).getAdapterPosition();
        Publicacao p = publicacaoAdapter.getPublicacao(i);
        Intent intent = new Intent(this, DetalhesPublicacao.class);
        intent.putExtra("Publicacao", p);
        startActivity(intent);


    }
}
