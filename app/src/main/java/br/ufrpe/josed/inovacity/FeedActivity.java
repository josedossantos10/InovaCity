package br.ufrpe.josed.inovacity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class FeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    public void abrirCriarPublicacao(View v){

        Snackbar.make(v, "Aguarde!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

        Intent intent = new Intent(this, CriarPubliacao.class );
        startActivity(intent);


    }
}
