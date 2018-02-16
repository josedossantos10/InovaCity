package br.ufrpe.josed.inovacity.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by josed on 14/02/2018.
 */

public class Publicacao {

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

}
