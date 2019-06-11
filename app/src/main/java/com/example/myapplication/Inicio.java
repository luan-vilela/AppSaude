package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.myapplication.model.Crud;
import com.example.myapplication.model.Profile;

import java.lang.reflect.Proxy;

public class Inicio extends AppCompatActivity {

    // Código da requisição
    private static final int PERMISSION_REQUEST_CODE = 200;
    // salvar local imagem
    private String picturePath = null;
    private ImageView minhaFoto;
    private EditText nome;
    private EditText email;
    Crud db = new Crud(this);

    private Profile user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        minhaFoto = (ImageView) findViewById(R.id.ivImagem);
        nome = findViewById(R.id.inputNome);
        email = findViewById(R.id.inputEmail);

        //#   Aqui são carregados os dados na tela
        //# caso já tenha cadastro no celular

        user = db.selecionaProfile();

        if (user != null && user.getId() == 1) {
            Intent it = new Intent(Inicio.this, MainActivity.class);
            finish();
            startActivity(it);
        }


    }

    //Botão salvar
    public void btnSalvar(View view) {
        //Variável de teste
        // caso não seja especificado algo que deve ser escrito
        // teste muda para 0
        int teste = 1;
        String userNome = nome.getText().toString();
        String userEmail = email.getText().toString();


        if (userNome == null || userNome.equals("")) {
            nome.setError(getString(R.string.errorName));
            teste = 0;
        }
        if (userEmail == null || userEmail.equals("")) {
            email.setError(getString(R.string.errorEmail));
            teste = 0;
        }

        // Caso todos os campos obrigatórios estejam preenchidos
        if (teste == 1) {
            // caso não tenha foto
            picturePath = (picturePath == null ? "" : picturePath);
            Profile aux = new Profile();

            aux.setEmail(userEmail);
            aux.setNome(userNome);
            aux.setFotoCaminho(picturePath);
            db.addUser(aux);

            Intent it = new Intent(Inicio.this, MainActivity.class);
            finish();
            startActivity(it);

        }
    }

    /**
     * Carrega foto apartir do botão edit
     * */
    public void editFoto(View view){
        Intent it = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(it, "Selecione uma imagem"), 123);

    }

    // faz a conversão do caminho em bitmap
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == 123){
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                picturePath = cursor.getString(columnIndex);
                cursor.close();
                minhaFoto.setImageBitmap(BitmapFactory.decodeFile(picturePath));

            }
        }
    }

    //#################### PERMISSÃO STORAGE ########################
    // Permissão para acessar a câmera
    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        return true;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,  Manifest.permission.READ_EXTERNAL_STORAGE},
                PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (checkPermission()) {
            // Success
        } else {
            requestPermission();
        }
    }
}
