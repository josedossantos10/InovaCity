package br.ufrpe.josed.inovacity;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.josed.inovacity.model.Publicacao;
import br.ufrpe.josed.inovacity.model.PublicacaoFB;
import br.ufrpe.josed.inovacity.util.FireBaseDB;
import br.ufrpe.josed.inovacity.util.Mensagens;

public class MapaActivityTeste extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);






        setContentView(R.layout.activity_feed_maps);
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.feedMap);
        mapFragment.getMapAsync(this);



       // gerarMarcadores();
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        FireBaseDB.gerarMarcadores(mMap);

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
