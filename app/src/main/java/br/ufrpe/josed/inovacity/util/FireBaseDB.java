package br.ufrpe.josed.inovacity.util;

import android.support.v7.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.josed.inovacity.adapters.PublicacaoAdapter;
import br.ufrpe.josed.inovacity.model.Publicacao;
import br.ufrpe.josed.inovacity.model.PublicacaoFB;

/**
 * Created by josed on 27/02/2018.
 */

public class FireBaseDB  {

    public static FirebaseDatabase database;
    public static FirebaseAuth mAuth;
    public static DatabaseReference PublicacaoRef;
    public static DatabaseReference UserRef;
    public static ArrayList<Marker> Marcadores;
    static Marker m;

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


    public static void gerarMarcadores(GoogleMap googleMap,LatLng l){
        final GoogleMap mMap = googleMap;
        final LatLng local=l;



        FireBaseDB.PublicacaoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mMap.clear();
                Marcadores = new ArrayList<>();
                for (DataSnapshot dados: dataSnapshot.getChildren()){ //recupera os filhos do nó principal
                    PublicacaoFB publicacao = dados.getValue(PublicacaoFB.class);
                    // publicacaoLista.add(publicacao);

                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(new LatLng(publicacao.getLatitude(), publicacao.getLongitude()));
                    markerOptions.title(publicacao.getTitulo());
                    m= mMap.addMarker(markerOptions);
                    Marcadores.add(m);
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                    mMap.addMarker(markerOptions);

                }
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(local);
                markerOptions.title("Meu Local");
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                CameraPosition cameraPosition = new CameraPosition.Builder().zoom(15).target(local).build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                mMap.addMarker(markerOptions);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }
}
