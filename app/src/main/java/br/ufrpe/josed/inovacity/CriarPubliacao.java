package br.ufrpe.josed.inovacity;

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


    public void publicacar(View v){
        this.finish();

    }
}
