package br.ufrpe.josed.inovacity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.ufrpe.josed.inovacity.model.Publicacao;
import br.ufrpe.josed.inovacity.util.Mensagens;

public class DetalhesPublicacao extends AppCompatActivity {
    private Publicacao publicacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_publicacao);
    }

    public DetalhesPublicacao(){


    }

    public void curtirPublicacao(View v) {

        Mensagens.SnackLongo(v, "Não implementado!", "OK");
    }

}
