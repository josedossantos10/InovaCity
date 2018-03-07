package br.ufrpe.josed.inovacity.model;

/**
 * Created by josed on 27/02/2018.
 */

public class PublicacaoFB{

     String titulo;
     String descricao;
     double latitude;
     double longitude;

    public PublicacaoFB(String titulo, String descricao, double latitude, double longitude) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public PublicacaoFB() {

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
}