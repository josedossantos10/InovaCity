package br.ufrpe.josed.inovacity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import br.ufrpe.josed.inovacity.adapters.*;
import br.ufrpe.josed.inovacity.repositorio.UsuarioDAO;

public class ListarUsuarios extends AppCompatActivity {

    UsuarioAdapter usuarioAdapter;
    UsuarioDAO usuarioDAO;
    RecyclerView listaUsuarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuarios);

        listaUsuarios = (RecyclerView) findViewById(R.id.recycleUsuarios);
        listaUsuarios.setHasFixedSize(true);
        listaUsuarios.setLayoutManager(new LinearLayoutManager(this));
        usuarioDAO = new UsuarioDAO(this);
        usuarioAdapter = new UsuarioAdapter(usuarioDAO.listarTodos());

        listaUsuarios.setAdapter(usuarioAdapter);

    }


    public void atualizar(View view){
        usuarioAdapter = new UsuarioAdapter(usuarioDAO.listarTodos());

    }
}
