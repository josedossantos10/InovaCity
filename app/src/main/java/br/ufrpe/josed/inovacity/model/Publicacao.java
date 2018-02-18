package br.ufrpe.josed.inovacity.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by josed on 14/02/2018.
 */

public class Publicacao {

    private long id;
    private String titulo;
    private String descricao;

    //RECURSOS
    private ArrayList<String> imagens;
    private long latitude;
    private long longitude;

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

    public Publicacao() {
        Date date= new Date();
        DataAbertura = date;
        this.resolvida = false;
        this.visivel = true;
        this.apoios=0;
        this.naoApoios=0;
        this.denuncias=0;


    }

    public Publicacao(String titulo, String descricao, ArrayList<String> imagens, long latitude, long longitude) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.imagens = imagens;
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

    public ArrayList<String> getImagens() {
        return imagens;
    }

    public void setImagens(ArrayList<String> imagens) {
        this.imagens = imagens;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
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
}
