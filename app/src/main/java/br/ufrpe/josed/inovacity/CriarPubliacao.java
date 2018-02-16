package br.ufrpe.josed.inovacity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CriarPubliacao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_publiacao);
    }

    public void cancelarCriarPublicacao(View v){
        this.finish();

    }


    public void publicar(View v){
        Intent intent = new Intent(this, LoginActivity.class );
        startActivity(intent);
        /*
        * Aqui o codigo para salvar os dados no banco de dados sqlite
        * */
      //  this.finish();

    }
}
