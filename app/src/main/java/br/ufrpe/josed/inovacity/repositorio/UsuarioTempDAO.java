package br.ufrpe.josed.inovacity.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.josed.inovacity.database.DBConnection;
import br.ufrpe.josed.inovacity.model.Usuario;

/**
 * Created by josed on 16/02/2018.
 */

public class UsuarioTempDAO {

    private SQLiteDatabase conexao;

    public UsuarioTempDAO(Context context){
        conexao = DBConnection.criarConexao(context);

    }


    public long inserir(br.ufrpe.josed.inovacity.model.Usuario usuario){
        ContentValues values = new ContentValues();
        values.put("nome",usuario.getNome());
        values.put("cpf",usuario.getCpf());
        values.put("email",usuario.getEmail());
        values.put("senha",usuario.getSenha());
        values.put("celular",usuario.getCelular());
        values.put("rua",usuario.getRua());
        values.put("numero",usuario.getNumero());
        values.put("cidade",usuario.getCidade());
        values.put("bairro",usuario.getBairro());
        values.put("cep",usuario.getCep());
        values.put("estado",usuario.getEstado());
        values.put("data_cadastro",(usuario.getDataCadastro()!=null? usuario.getDataCadastro().getTime(): null));

       return conexao.insertOrThrow("usuario_temp", null , values);

    }


    public void removerTodos(){
        conexao.delete("usuario_temp", null,null );
       // conexao.execSQL("delete from usuarios_temp");

    }


    public Usuario recuperar(){

       Cursor result = conexao.query("usuario_temp",null,null,null,null,null,"nome");
       if(result.getCount()>0){
           result.moveToLast();
               br.ufrpe.josed.inovacity.model.Usuario u = new br.ufrpe.josed.inovacity.model.Usuario();

               u.setId(result.getLong(result.getColumnIndexOrThrow("id")));
               u.setNome(result.getString(result.getColumnIndexOrThrow("nome")));
               u.setCpf(result.getString(result.getColumnIndexOrThrow("cpf")));
               u.setEmail(result.getString(result.getColumnIndexOrThrow("email")));
               u.setSenha(result.getString(result.getColumnIndexOrThrow("senha")));
               u.setCelular(result.getString(result.getColumnIndexOrThrow("celular")));
               u.setRua(result.getString(result.getColumnIndexOrThrow("rua")));
               u.setNumero(result.getString(result.getColumnIndexOrThrow("numero")));
               u.setCidade(result.getString(result.getColumnIndexOrThrow("cidade")));
               u.setBairro(result.getString(result.getColumnIndexOrThrow("bairro")));
               u.setCep(result.getString(result.getColumnIndexOrThrow("cep")));
               u.setEstado(result.getString(result.getColumnIndexOrThrow("estado")));
           return u;

       }

return null;

    }



}
