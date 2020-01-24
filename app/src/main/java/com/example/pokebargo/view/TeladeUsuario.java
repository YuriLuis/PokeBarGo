package com.example.pokebargo.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.example.pokebargo.R;
import com.google.firebase.auth.FirebaseAuth;

import java.io.File;

public class TeladeUsuario extends AppCompatActivity {

    private static final int REQUEST_CAMERA_PERMISSION = 2000;
    private static final int GALERIA_IMAGENS = 111;
    private final int CAPTURAR_IMAGEM = 222;
    private final int PERMISSAO_REQUEST = 2;
    FirebaseAuth mAuth;
    private Button btn_carregarImagem, btn_carregarCamera, btn_Entrar;
    private AlertDialog alerta2;
    private EditText etNome, etEmail, etTel;
    private ImageView img_Produto;
    private String nomeImagem;
    private Uri uri;
    private Activity thisActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telade_usuario);
        btn_carregarImagem = findViewById(R.id.btn_carregarImagem);
        btn_carregarCamera = findViewById(R.id.btn_carregarCamera);
        btn_Entrar = findViewById(R.id.btn_Entrar);
        etNome = findViewById(R.id.etNome);
        etEmail = findViewById(R.id.etEmail);
        etTel = findViewById(R.id.etTel);
        mAuth = FirebaseAuth.getInstance();

        btn_carregarCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    Log.d("DEBUG", "onclick antes");
                    ActivityCompat.requestPermissions(thisActivity, new String[]{Manifest.permission.READ_CONTACTS},
                            REQUEST_CAMERA_PERMISSION
                    );
                    Log.d("DEBUG", "onclick dps");
                } else {
                    boolean temCamera = getPackageManager()
                            .hasSystemFeature(PackageManager.FEATURE_CAMERA);
                    if (temCamera) {
                        tirarFoto();
                    }
                }
            }
        });
        btn_Entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((String.valueOf(etNome.getText()).length() > 0)
                        && (String.valueOf(etEmail.getText()).length() > 0)
                        && (String.valueOf(etTel.getText()).length() > 0)) {
                    btn_Entrar.setActivated(true);
                    //cadastrarProduto();
                    alert();
                } else {
                    alert();
                }
                limpaCampos();
            }

            private void alert() {
                //Cria o gerador do AlertDialog
                AlertDialog.Builder builder2 = new AlertDialog.Builder(getApplicationContext());
                //define o titulo
                builder2.setTitle("StockSys");
                //define a mensagem
                builder2.setMessage("\n » Dados inválidos!");
                //cria o AlertDialog
                alerta2 = builder2.create();
                //Exibe
                alerta2.show();
            }
        });

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSAO_REQUEST);
            }
        }

        img_Produto = findViewById(R.id.imageView);
        Button galeria = findViewById(R.id.btn_carregarImagem);
        galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, GALERIA_IMAGENS);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALERIA_IMAGENS) {
            if (resultCode == RESULT_OK) {
                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                thumbnail = rotationBitMap(thumbnail);
                img_Produto.setImageBitmap(thumbnail);
            }
        }
        if (requestCode == CAPTURAR_IMAGEM) {
            if (resultCode == RESULT_OK) {
                ;

                Bitmap thumbnail = (BitmapFactory.decodeFile(nomeImagem));
                thumbnail = rotationBitMap(thumbnail);
                img_Produto.setImageBitmap(thumbnail);
                mostrarMensagem("Imagem capturada!");
                adicionarNaGaleria();
            } else {
                mostrarMensagem("Imagem não capturada!");
            }
        }

    }

    private Bitmap rotationBitMap(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.setRotate(90);
        Bitmap bmRotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        bitmap.recycle();
        return bmRotated;
    }

    private void tirarFoto() {
        File diretorio = Environment
                .getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES);
        nomeImagem = diretorio.getPath() + "/" +
                System.currentTimeMillis() +
                ".jpg";
        //uri = Uri.fromFile(new File(nomeImagem));
        uri = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getApplicationContext().getPackageName() + ".provider", new File(nomeImagem));

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, CAPTURAR_IMAGEM);
    }

    private void mostrarMensagem(String msg) {
        Toast.makeText(this, msg,
                Toast.LENGTH_LONG)
                .show();
    }

    private void adicionarNaGaleria() {
        Intent intent = new Intent(
                Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(uri);
        this.sendBroadcast(intent);
    }

    public void limpaCampos() {
        etNome.setText("");
        etEmail.setText("");
        etTel.setText("");
    }

    public void deslogarClick(View view) {
        mAuth.signOut();
        startActivity(new Intent(TeladeUsuario.this, LoginActivity.class));
        finish();
    }

    public void voltarClick(View view) {
        startActivity(new Intent(TeladeUsuario.this, MainActivity.class));
        finish();
    }


}