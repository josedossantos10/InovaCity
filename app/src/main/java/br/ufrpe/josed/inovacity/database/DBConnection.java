package br.ufrpe.josed.inovacity.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import br.ufrpe.josed.inovacity.util.Mensagens;

/**
 * Created by josed on 17/02/2018.
 */

public class DBConnection {


    private static DBHelper dbHelper;
    private static SQLiteDatabase conexao;

    public static SQLiteDatabase criarConexao(Context context){

        try {
            if((dbHelper==null)||(conexao ==null)||(!conexao.isOpen())) {
                dbHelper = new DBHelper(context);
                conexao = dbHelper.getWritableDatabase();
                Mensagens.ToastLongo(context, "Conectado!");
            }
            return conexao;

        }catch (SQLException e){
            Mensagens.Alerta(context,"Erro","Ocorreu um erro ao conectar com o Banco de Dados: "+e.getMessage());
        }

        return null;
    }

    public static void fecharConexao(Context context){

        try {

            conexao.close();
            dbHelper.close();
            dbHelper=null;
            conexao =null;
            Mensagens.ToastLongo(context,"Desconectado!");

        }catch (SQLException e){
            Mensagens.Alerta(context,"Erro","Ocorreu um erro ao desconectar com o Banco de Dados: "+e.getMessage());
        }

    }


}
