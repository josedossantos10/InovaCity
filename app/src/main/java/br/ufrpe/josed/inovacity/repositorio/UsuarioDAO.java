package br.ufrpe.josed.inovacity.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.josed.inovacity.database.DBConnection;

/**
 * Created by josed on 16/02/2018.
 */

public class UsuarioDAO {

    private SQLiteDatabase conexao;

    public UsuarioDAO(Context context){
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
        values.put("data_cadastro",usuario.getDataCadastro().getTime());

       return conexao.insertOrThrow("usuario", null , values);

    }

    public boolean atualizar(br.ufrpe.josed.inovacity.model.Usuario usuario){

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
        values.put("data_cadastro",usuario.getDataCadastro().getTime());

        String[] identificador = new String[1];
        identificador[0] = String.valueOf(usuario.getId());


        int result =  conexao.update("usuario", values, "id = ?",identificador );

        if(result==0) {
            return false;
        } else {
            return true;
        }

    }

    public boolean remover(int id){

        String[] identificador = new String[1];
        identificador[0] = String.valueOf(id);

       int result = conexao.delete("usuario", "id = ?",identificador );

       if(result==0) {
           return false;
       } else {
           return true;
       }
    }


    public br.ufrpe.josed.inovacity.model.Usuario buscarPorCPF(String cpf){
        String chave[] = new String[1];
        chave[0] = cpf;


        Cursor result = conexao.query("usuario",null,"cpf = ?",chave,null,null,null);
        if(result.moveToFirst()) {
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

    public br.ufrpe.josed.inovacity.model.Usuario buscarPorEmail(String email){
        String chave[] = new String[1];
        chave[0] = email;


        Cursor result = conexao.query("usuario",null,"email = ?",chave,null,null,null);
        if(result.moveToFirst()) {
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

    public br.ufrpe.josed.inovacity.model.Usuario buscarPorID(int id){
        String chave[] = new String[1];
        chave[0] = String.valueOf(id);


        Cursor result = conexao.query("usuario",null,"id = ?",chave,null,null,null);
        if(result.moveToFirst()) {
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

    public List<br.ufrpe.josed.inovacity.model.Usuario> listarTodos(){
        List<br.ufrpe.josed.inovacity.model.Usuario> usuarios = new ArrayList<br.ufrpe.josed.inovacity.model.Usuario>();

       Cursor result = conexao.query("usuario",null,null,null,null,null,"nome");
       if(result.getCount()>0){
           result.moveToFirst();
           do {
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

               usuarios.add(u);
           }while (result.moveToNext());

           return usuarios;

       }

return null;

    }



}
