package br.ufrpe.josed.inovacity.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.content.ContextCompat;

/**
 * Created by josed on 16/02/2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static String nomeBD = "inovacity_serra.db";
    private static int versao = 1;



    public DBHelper(Context context){
        super(context,nomeBD, null, versao);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS publicacao (" +
                "    id            INTEGER      PRIMARY KEY AUTOINCREMENT," +
                "    titulo          VARCHAR (20) NOT NULL," +
                "    descricao       VARCHAR (100), " +
                "    latitude         INTEGER," +
                "    longitude       INTEGER," +
                "    apoios           INTEGER," +
                "    nao_apoios           INTEGER," +
                "    url             VARCHAR (200)," +
                "    resolvida        boolean," +
                "    visivel           boolean," +
                "     data_abertura DATE," +
                "    data_fechamento DATE);");


        db.execSQL("CREATE TABLE IF NOT EXISTS usuario (" +
                "    id            INTEGER      PRIMARY KEY AUTOINCREMENT," +
                "    nome          VARCHAR (50) NOT NULL," +
                "    cpf           STRING       UNIQUE," +
                "    email         VARCHAR (50)," +
                "    senha         VARCHAR (50)," +
                "    celular       VARCHAR (20)," +
                "    rua           VARCHAR (50)," +
                "    numero        VARCHAR (10)," +
                "    bairro        VARCHAR (50)," +
                "    cidade        VARCHAR (50)," +
                "    cep           VARCHAR (8)," +
                "    estado        VARCHAR (30)," +
                "    data_cadastro DATE);");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
