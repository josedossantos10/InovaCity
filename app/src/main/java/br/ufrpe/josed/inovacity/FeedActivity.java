package br.ufrpe.josed.inovacity;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import br.ufrpe.josed.inovacity.database.DBHelper;
import br.ufrpe.josed.inovacity.util.Mensagens;

public class FeedActivity extends AppCompatActivity {

    private SQLiteDatabase connexao;
    private DBHelper dbHelper;
    private ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        layout = (ConstraintLayout) findViewById(R.id.contentFeed);
        criarConexao();

    }

    public void abrirCriarPublicacao(View v){

        Intent intent = new Intent(this, CriarPubliacao.class );
        startActivity(intent);
    }

    private void criarConexao(){

        try {

            dbHelper = new DBHelper(this);
            connexao = dbHelper.getWritableDatabase();
            Mensagens.SnackCurto(layout,"Online!");

        }catch (SQLException e){
            Mensagens.Alerta(this,"Erro","Ocorreu um erro ao conectar com o Banco de Dados: "+e.getMessage());
        }
    }
}
