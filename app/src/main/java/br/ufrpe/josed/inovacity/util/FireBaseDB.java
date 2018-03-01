package br.ufrpe.josed.inovacity.util;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import br.ufrpe.josed.inovacity.Adapters.PublicacaoAdapter;
import br.ufrpe.josed.inovacity.CadastrarUsuario;
import br.ufrpe.josed.inovacity.model.Publicacao;
import br.ufrpe.josed.inovacity.model.PublicacaoFB;

/**
 * Created by josed on 27/02/2018.
 */

public class FireBaseDB {

    public static FirebaseDatabase database;
    public static FirebaseAuth mAuth;
    public static DatabaseReference PublicacaoRef;
    public static DatabaseReference UserRef;


    static {
        database = FirebaseDatabase.getInstance();
        PublicacaoRef = database.getReference("Publicacoes");
        UserRef = database.getReference("Usuarios");
        mAuth = FirebaseAuth.getInstance();
    }



    public static List<Publicacao> listarTodasPublicacoes(final RecyclerView listaPublicacoes){
        final List<Publicacao> publicacaoList = new ArrayList<>();

        FireBaseDB.PublicacaoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                publicacaoList.clear();
                for (DataSnapshot dados: dataSnapshot.getChildren()){ //recupera os filhos do nó principal
                    PublicacaoFB publicacao = dados.getValue(PublicacaoFB.class);

                    publicacaoList.add(new Publicacao(publicacao));
                }

                // listaPublicacoes.setAdapter(null);
                PublicacaoAdapter publicacaoAdapter = new PublicacaoAdapter(publicacaoList);
                //  publicacaoAdapter = new PublicacaoAdapter(publicacaoDAO.listarTodos());
                listaPublicacoes.setAdapter(publicacaoAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });


        return publicacaoList;

    }


    }
