package br.ufrpe.josed.inovacity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import br.ufrpe.josed.inovacity.model.Publicacao;
import br.ufrpe.josed.inovacity.util.Mensagens;

public class DetalhesPublicacao extends AppCompatActivity implements OnMapReadyCallback {
    private Publicacao publicacao;
    private TextView txtTitulo;
    private TextView txtDescricao;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private MapFragment mMapFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_publicacao);
        Intent intent = getIntent();
        publicacao = (Publicacao) intent.getSerializableExtra("Publicacao");

        txtTitulo = findViewById(R.id.textTituloDetalhes);
        txtDescricao = findViewById(R.id.textDescricaoDetalhes);
        imageView1 = findViewById(R.id.imagem1PublicacaoDetalhes);

        txtTitulo.setText(publicacao.getTitulo());
        txtDescricao.setText(publicacao.getDescricao());
        imageView1.setImageBitmap(publicacao.getImagem1());

        mMapFragment = MapFragment.newInstance();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.layoutDetalhes, mMapFragment);
        fragmentTransaction.commit();
        mMapFragment.getMapAsync(this);



    }

    public DetalhesPublicacao(){}

    public void curtirPublicacao(View v) { Mensagens.SnackLongo(v, "Não implementado!", "OK");}

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        googleMap.addMarker(new MarkerOptions().position(new LatLng(publicacao.getLatitude(), publicacao.getLongitude()))
                .title("Local"));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(publicacao.getLatitude(), publicacao.getLongitude()), 17.0f));

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
