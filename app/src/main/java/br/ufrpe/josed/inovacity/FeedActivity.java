package br.ufrpe.josed.inovacity;

import android.content.Context;
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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import br.ufrpe.josed.inovacity.Adapters.PublicacaoAdapter;
import br.ufrpe.josed.inovacity.model.Usuario;
import br.ufrpe.josed.inovacity.repositorio.PublicacaoDAO;
import br.ufrpe.josed.inovacity.repositorio.UsuarioTempDAO;
import br.ufrpe.josed.inovacity.util.User;

public class FeedActivity extends AppCompatActivity {


    private ConstraintLayout layout;
    PublicacaoDAO publicacaoDAO;
    RecyclerView listaPublicacoes;
    private static TextView txtLabelNome;
    private UsuarioTempDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        layout = (ConstraintLayout) findViewById(R.id.contentFeed);
        txtLabelNome = (TextView) findViewById(R.id.txtLabelEntrar);

        listaPublicacoes = (RecyclerView) findViewById(R.id.recyclePubicacao);
        listaPublicacoes.setHasFixedSize(true);
        listaPublicacoes.setLayoutManager(new LinearLayoutManager(this));

        publicacaoDAO = new PublicacaoDAO(this);
        dao = new UsuarioTempDAO(this);

        PublicacaoAdapter publicacaoAdapter = new PublicacaoAdapter(publicacaoDAO.listarTodos());
        listaPublicacoes.setAdapter(publicacaoAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Usuario u = dao.recuperar();
        if(u!=null){
            User.currentUser = u;
            setLabel(u.getNome());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dao.removerTodos();
        if(User.currentUser!=null){
            dao.inserir(User.currentUser);
        }
    }


    public void atualizar() {
        listaPublicacoes.setAdapter(new PublicacaoAdapter(publicacaoDAO.listarTodos()));
    }

    public void abrirCriarPublicacao(View v){
        Intent intent = new Intent(this, CriarPubliacao.class );
        startActivity(intent);
    }

    public void abrirLogin(View v){

        if(User.currentUser!=null) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Sair");
            dialog.setMessage("Você já está conectado como "+User.currentUser.getNome()+". Deseja realmente sair?");
            dialog.setNegativeButton("Cancelar",null);
            dialog.setPositiveButton("SAIR",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    User.currentUser=null;
                    dao.removerTodos();
                    FeedActivity.setLabel("ENTRAR");
                }

            });
            dialog.show();

        }else{
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    public static void setLabel(String nome){
        txtLabelNome.setText(nome);

    }






}
