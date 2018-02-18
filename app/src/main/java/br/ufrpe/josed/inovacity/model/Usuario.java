package br.ufrpe.josed.inovacity.model;

import java.util.Date;

/**
 * Created by josed on 14/02/2018.
 */

public class Usuario {


    private long id;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private String celular;

    private String rua;
    private String numero;
    private String cidade;
    private String bairro;
    private String cep;

    private String estado;
    private Date dataCadastro;

    public Usuario(){}

    public Usuario(String nome, String cpf, String email, String senha, String celular, String rua, String numero, String cidade, String bairro, String cep) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.celular = celular;
        this.rua = rua;
        this.numero = numero;
        this.cidade = cidade;
        this.bairro = bairro;
        this.cep = cep;
        this.estado = estado;
        Date date= new Date();
        this.dataCadastro = date;

    }


    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public String getSenha() {
        return senha;
    }
}

