package br.ufrpe.josed.inovacity;

import android.Manifest;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import br.ufrpe.josed.inovacity.model.Publicacao;
import br.ufrpe.josed.inovacity.repositorio.PublicacaoDAO;
import br.ufrpe.josed.inovacity.util.Mensagens;
import br.ufrpe.josed.inovacity.util.User;

public class CriarPubliacao extends AppCompatActivity implements OnMapReadyCallback {
    private static final int PERMISSAO_REQUEST = 2;
    private PublicacaoDAO publicacaoDAO;
    private EditText txtTitulo;
    private EditText txtDescricao;
    public static final int IMAGEM_EXTERNAL = 12;
    public static final int CAMERA = 12;
    private ImageSwitcher imageSwitcher;
    private Bitmap imagem1;
    private ImageView imagemView;
    private MapView mapView;
    private MapFragment mMapFragment;
    private LocationManager lm;
    private double latitude=0;
    private double longitude=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_publiacao);
        txtTitulo = (EditText) findViewById(R.id.editTitulo);
        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        txtDescricao = (EditText) findViewById(R.id.editDescricaoDescricao);
        imagemView = (ImageView) findViewById(R.id.imageView);
        publicacaoDAO = new PublicacaoDAO(this);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSAO_REQUEST);
            }
        }


        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        mMapFragment = MapFragment.newInstance();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.layout, mMapFragment);
        fragmentTransaction.commit();
        mMapFragment.getMapAsync(this);



    }



    public void cancelarCriarPublicacao(View v) {
        this.finish();

    }


    public void publicar(View v) {
        /* Aqui o codigo para salvar os dados no banco de dados sqlite
        * */
        if (User.currentUser != null) {

            Publicacao publicacao = new Publicacao();
            publicacao.setTitulo(txtTitulo.getText().toString());
            publicacao.setDescricao(txtDescricao.getText().toString());
            publicacao.setImagem1(imagem1);
            publicacao.setLongitude(longitude);
            publicacao.setLatitude(latitude);

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

/*        Intent intentImagens = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intentImagens.setType("image/*");
        startActivityForResult(intentImagens, IMAGEM_EXTERNAL);
*/
        Intent intentCaptura = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intentCaptura, CAMERA);




    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if((resultCode==RESULT_OK)&&(requestCode==IMAGEM_EXTERNAL)){

            Uri imagemSelecionada = data.getData();
            String[] filePath = {MediaStore.Images.Media.DATA};
            Cursor c =  getContentResolver().query(imagemSelecionada,filePath,null,null,null);
            c.moveToFirst();
            int columIndex = c.getColumnIndex(filePath[0]);
            String picturePath = c.getString(columIndex);
            c.close();
            Bitmap imagem = (BitmapFactory.decodeFile(picturePath));
            imagemView.setImageBitmap(imagem);
        }
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if((resultCode==RESULT_OK)&&(requestCode==CAMERA)){
            Bundle bundle = data.getExtras();
            imagem1 = (Bitmap) bundle.get("data");
            imagemView.setImageBitmap(imagem1);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        if(requestCode== PERMISSAO_REQUEST) {
            if(grantResults.length> 0&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // A permissão foi concedida. Pode continuar
            }else{
                // A permissão foi negada. Precisa ver o que deve ser desabilitado
            }return;
        }

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        Location location = (Location) lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        longitude = location.getLongitude();
        latitude = location.getLatitude();

        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude,longitude))
                .title("Meu Local"));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude), 18.0f));

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}