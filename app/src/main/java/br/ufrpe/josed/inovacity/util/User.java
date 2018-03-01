package br.ufrpe.josed.inovacity.util;


import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import br.ufrpe.josed.inovacity.model.Usuario;

/**
 * Created by josed on 07/02/2018.
 */

public class User {

    private String id;
    private String nome;
    private String senha;
    private String email;


  //  public static Usuario currentUser=null;

public void user(){}

    public void salvar(){

    FireBaseDB.UserRef.child(String.valueOf(id)).setValue(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
