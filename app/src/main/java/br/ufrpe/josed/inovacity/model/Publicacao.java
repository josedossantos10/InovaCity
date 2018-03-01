package br.ufrpe.josed.inovacity.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by josed on 14/02/2018.
 */

public class Publicacao implements Serializable{

    private long id;
    private String titulo;
    private String descricao;


    //RECURSOS
    private byte[] imagem1;
    private byte[] imagem2;
    private byte[] imagem3;
    private double latitude;
    private double longitude;

    //CONTADORES
    private int apoios;
    private int naoApoios;
    private int denuncias;

    //INFORMAÇÕES
    private Date DataAbertura;
    private Date DataFechamento;
    private boolean resolvida;
    private boolean visivel;
    private  String url;

    public Publicacao(String titulo, String descricao, double latitude, double longitude) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.latitude = latitude;
        this.longitude = longitude;
        Date date= new Date();
        DataAbertura = date;
        this.resolvida = false;
        this.visivel = true;
        this.apoios=0;
        this.naoApoios=0;
        this.denuncias=0;

    }



    public Publicacao() {
        Date date= new Date();
        DataAbertura = date;
        this.resolvida = false;
        this.visivel = true;
        this.apoios=0;
        this.naoApoios=0;
        this.denuncias=0;


    }


    public void apoiar(){
        this.apoios++;
    }
    public void naoApoiar(){
        this.naoApoios++;
    }
    public void denunciar(){
        this.denuncias++;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getApoios() {
        return apoios;
    }

    public void setApoios(int apoios) {
        this.apoios = apoios;
    }

    public int getNaoApoios() {
        return naoApoios;
    }

    public void setNaoApoios(int naoApoios) {
        this.naoApoios = naoApoios;
    }

    public int getDenuncias() {
        return denuncias;
    }

    public void setDenuncias(int denuncias) {
        this.denuncias = denuncias;
    }

    public Date getDataAbertura() {
        return DataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        DataAbertura = dataAbertura;
    }

    public Date getDataFechamento() {
        return DataFechamento;
    }

    public void setDataFechamento(Date dataFechamento) {
        DataFechamento = dataFechamento;
    }

    public boolean isResolvida() {
        return resolvida;
    }

    public void setResolvida(boolean resolvida) {
        this.resolvida = resolvida;
    }

    public boolean isVisivel() {
        return visivel;
    }

    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public void setImagem1Byte(byte[] imagem1) {
        this.imagem1 = imagem1;
    }

    public void setImagem2Byte(byte[] imagem2) {
        this.imagem2 = imagem2;
    }

    public void setImagem3Byte(byte[] imagem3) {
        this.imagem3 = imagem3;
    }

    public byte[] getImagem1Byte(){
        return imagem1;
    }
    public byte[] getImagem2Byte(){
        return imagem2;
    }
    public byte[] getImagem3Byte(){
        return imagem3;
    }


    public Bitmap getImagem1() {
        if (imagem1!=null){
            ByteArrayInputStream imageStream= new ByteArrayInputStream(this.imagem1);
            return BitmapFactory.decodeStream(imageStream);
        }else {
            return null;
        }

    }

    public void setImagem1(Bitmap imagem) {
        if (imagem!=null){
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imagem.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            this.imagem1 = stream.toByteArray();
        }
    }


    public Bitmap getImagem2() {
        if (imagem1!=null){
            ByteArrayInputStream imageStream= new ByteArrayInputStream(this.imagem1);
            return BitmapFactory.decodeStream(imageStream);
        }else {
            return null;
        }

    }

    public void setImagem2(Bitmap imagem) {
        if (imagem!=null){
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imagem.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            this.imagem1 = stream.toByteArray();
        }
    }

    public Bitmap getImagem3() {
        if (imagem1!=null){
            ByteArrayInputStream imageStream= new ByteArrayInputStream(this.imagem1);
            return BitmapFactory.decodeStream(imageStream);
        }else {
            return null;
        }

    }

    public void setImagem3(Bitmap imagem) {
        if (imagem!=null){
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imagem.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            this.imagem1 = stream.toByteArray();
        }
    }



public PublicacaoFB getPublicacaoFb(){
        return new PublicacaoFB(this.titulo, this.descricao, this.latitude, this.longitude);
    }





    public Publicacao(PublicacaoFB publicacao) {
        //Date date= new Date();
        //DataAbertura = date;
        //  this.resolvida = false;
        //  this.visivel = true;
        //  this.apoios=0;
        //  this.naoApoios=0;
        // this.denuncias=0;
        this.titulo = publicacao.getTitulo();
        this.descricao = publicacao.getDescricao();
        this.latitude = publicacao.getLatitude();
        this.longitude = publicacao.getLongitude();

    }





}
