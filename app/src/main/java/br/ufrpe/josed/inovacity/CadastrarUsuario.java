package br.ufrpe.josed.inovacity;

import android.database.SQLException;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import br.ufrpe.josed.inovacity.model.Usuario;
import br.ufrpe.josed.inovacity.repositorio.UsuarioDAO;
import br.ufrpe.josed.inovacity.util.FireBaseDB;
import br.ufrpe.josed.inovacity.util.Mascaras;
import br.ufrpe.josed.inovacity.util.Mensagens;
import br.ufrpe.josed.inovacity.util.User;

public class CadastrarUsuario extends AppCompatActivity {

    private EditText editNome;
    private EditText editCpf;
    private EditText editEmail;
    private EditText editSenha;
    private EditText editCelular;
    private EditText editRua;
    private EditText editNumero;
    private EditText editCidade;
    private EditText editBairro;
    private EditText editCep;
    private View mProgressView;
    private User u;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        u= new User();

        editNome = (EditText) findViewById(R.id.editTextNome);
        editCpf = (EditText) findViewById(R.id.editTextCPF);
        editEmail = (EditText) findViewById(R.id.editTextEmail);
        editSenha = (EditText) findViewById(R.id.editTextSenha);
        editCelular = (EditText) findViewById(R.id.editTextCelular);
        editRua = (EditText) findViewById(R.id.editTextRua);
        editNumero = (EditText) findViewById(R.id.editTextNumero);
        editCidade = (EditText) findViewById(R.id.editTextCidade);
        editBairro = (EditText) findViewById(R.id.editTextBairro);
        editCep = (EditText) findViewById(R.id.editTextCEP);


        editCelular.addTextChangedListener(Mascaras.formatarCelular(editCelular));
        editCpf.addTextChangedListener(Mascaras.formatarCPF(editCpf));
        editCep.addTextChangedListener(Mascaras.formatarCEP(editCep));
        mProgressView = findViewById(R.id.login_progress3);



    }

    public void salvarUsuario(View view){
        mProgressView.setVisibility(View.VISIBLE);

        Usuario usuario = new Usuario(editNome.getText().toString(),editCpf.getText().toString(), editEmail.getText().toString(), editSenha.getText().toString(),
                editCelular.getText().toString(),editRua.getText().toString(), editNumero.getText().toString(), editCidade.getText().toString(),
                editBairro.getText().toString(),editCep.getText().toString());


        if(validarCampos(usuario)){

            UsuarioDAO usuDAO = new UsuarioDAO(this);


            try {

                usuDAO.inserir(usuario);
                ////    Mensagens.ToastLongo(this, "Bem-Vindo "+(usuario.getNome().indexOf(" ")>0?usuario.getNome().substring(0, usuario.getNome().indexOf(" ")):usuario.getNome()));

                // User.currentUser = usuario;
                // FeedActivity.setLabel(usuario.getNome());
                finish();
            }catch (SQLException e){
                Mensagens.Alerta(this,"Erro","Ocorreu um erro ao conectar com o Banco de Dados: "+e.getMessage());
            }

        }

        mProgressView.setVisibility(View.GONE);

    }


    public boolean validarCampos(Usuario u){

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Campo inválido");
        dialog.setNeutralButton("Entendi!",null);


        if(isCampoVazio(u.getNome())){
            editNome.requestFocus();
            Toast.makeText(this, "Campo 'Nome' inválido", Toast.LENGTH_LONG).show();

        }else if(isCampoVazio(u.getCpf())){
            editCpf.requestFocus();
            Toast.makeText(this, "CPF incorreto", Toast.LENGTH_LONG).show();
        }else if(!isEmailValido(u.getEmail())){
            editEmail.requestFocus();
            dialog.setMessage("E-mail digitado incorretamente! Seguir exemplo:\nexemplo@email.com");
            dialog.show();
        }else if(isCampoVazio(u.getCelular())){
            editCelular.requestFocus();
            Toast.makeText(this, "Celular digitado incorretamente", Toast.LENGTH_LONG).show();
        }else if(!isSenhaValida(editSenha.getText().toString())){
            editSenha.requestFocus();
            dialog.setMessage("Senha Inválida!\nDeve conter no mínimo 8 caracteres com letras e números ");
            dialog.show();
        }else if(isCampoVazio(u.getRua())){
            editRua.requestFocus();
            Toast.makeText(this, "Campo 'Rua' não pode ficar em branco", Toast.LENGTH_LONG).show();
        }else if(isCampoVazio(u.getCidade())){
            editCidade.requestFocus();
            Toast.makeText(this, "Campo 'Cidade' não pode ficar em branco", Toast.LENGTH_LONG).show();
        }else if(isCampoVazio(u.getCep())){
            editCep.requestFocus();
            Toast.makeText(this, "CEP inválido", Toast.LENGTH_LONG).show();
        }else if(isCampoVazio(u.getBairro())){
            editBairro.requestFocus();
            Toast.makeText(this, "Campo 'Bairro' não pode ficar em branco", Toast.LENGTH_LONG).show();
        }else
            return true;

        return false;
    }


    public boolean isEmailValido(String email){

        if ((Patterns.EMAIL_ADDRESS.matcher(email).matches())&&(email.length()>8))
            return true;
        else return false;
    }

    public boolean isCampoVazio(String valor){

        if((TextUtils.isEmpty(valor))||(valor.trim().isEmpty()))
            return true;
        else
            return false;

    }

    public boolean isSenhaValida(String senha){
        if((!isCampoVazio(senha))&&(senha.length()>=8))
            return true;
        else return false;
    }


    public void criarUsuarioFB(View v) {
        u.setNome(editNome.getText().toString());
        u.setEmail(editEmail.getText().toString());
        u.setSenha(editSenha.getText().toString());

        if (isCampoVazio(u.getNome())) {
            editNome.requestFocus();
            Toast.makeText(this, "Campo 'Nome' inválido", Toast.LENGTH_LONG).show();

        } else if (!isEmailValido(u.getEmail())) {
            editEmail.requestFocus();
            Mensagens.Alerta(this,"E-mail digitado incorretamente!","Seguir exemplo:\nexemplo@email.com");
        } else if (!isSenhaValida(editSenha.getText().toString())) {
            editSenha.requestFocus();
            Mensagens.Alerta(this,"Senha Inválida!","Deve conter no mínimo 8 caracteres com letras e números ");
        } else {
            FireBaseDB.mAuth.createUserWithEmailAndPassword(u.getEmail(), u.getSenha())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = FireBaseDB.mAuth.getCurrentUser();
                                u.setId(task.getResult().getUser().getProviderId());
                                u.salvar();

                                Mensagens.ToastLongo(CadastrarUsuario.this, "Bem-Vindo " + (u.getNome()));
                                FeedActivity.setLabel(u.getNome());

                                //updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Mensagens.Alerta(CadastrarUsuario.this, "Falha ao autenticar!", "" + task.getException().getMessage());
                                //updateUI(null);
                            }

                        }
                    });
        }
    }

}
