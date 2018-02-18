package br.ufrpe.josed.inovacity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import br.ufrpe.josed.inovacity.model.Publicacao;
import br.ufrpe.josed.inovacity.repositorio.PublicacaoDAO;
import br.ufrpe.josed.inovacity.util.Mensagens;
import br.ufrpe.josed.inovacity.util.User;

public class CriarPubliacao extends AppCompatActivity {
    private PublicacaoDAO publicacaoDAO;
    private EditText txtTitulo;
    private EditText txtDescricao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_publiacao);
        txtTitulo = (EditText) findViewById(R.id.editTitulo);
        txtDescricao = (EditText) findViewById(R.id.editDescricaoDescricao);
        publicacaoDAO = new PublicacaoDAO(this);
    }

    public void cancelarCriarPublicacao(View v){
        this.finish();

    }


    public void publicar(View v){


        /* Aqui o codigo para salvar os dados no banco de dados sqlite
        * */
        if(User.currentUser!=null){
            Publicacao publicacao = new Publicacao();
            publicacao.setTitulo(txtTitulo.getText().toString());
            publicacao.setDescricao(txtDescricao.getText().toString());

            if(publicacao.getTitulo().length()>5){
                if(publicacaoDAO.inserir(publicacao)>0){
                    Mensagens.ToastCurto(this,"Publicando!");
                    this.finish();}else{
                    Mensagens.SnackCurto(v,"Erro ao publicar!");
                }

            }else{
                Mensagens.SnackCurto(v,"Digite ao menos um Título");
            }
        }else{
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Entrar");
            dialog.setMessage("Você não está conectado em nenhuma conta, entre agora mesmo ou faça seu cadastro e comece a fazer a diferença na sua cidade.");
            dialog.setNegativeButton("Cadastrar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(CriarPubliacao.this, CadastrarUsuario.class );
                    startActivity(intent);
                }
            });

            dialog.setPositiveButton("Entrar",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(CriarPubliacao.this, LoginActivity.class );
                    startActivity(intent);
                }

            });
            dialog.show();


        }

    }

    public void adicionarFotos(View v){
        Mensagens.SnackLongo(v,"Não implementado!", "OK");

    }

}
