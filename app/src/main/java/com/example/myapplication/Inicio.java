package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.ParseException;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.model.Crud;
import com.example.myapplication.model.Profile;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Inicio extends AppCompatActivity {
    //servidor
    private RequestQueue requestQueue;
    private Map<String, String> params;
    // Código da requisição
    private static final int PERMISSION_REQUEST_CODE = 200;
    // salvar local imagem
    private String picturePath = null;
    private ImageView minhaFoto;
    private EditText nome;
    private EditText email;
    String userNome;
    String userEmail;
    private String idRegistro;
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

        requestQueue = Volley.newRequestQueue(this);
        //Variável de teste
        // caso não seja especificado algo que deve ser escrito
        // teste muda para 0
        int teste = 1;
        userNome = nome.getText().toString();
        userEmail = email.getText().toString();


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
            // envia dados servidor
            enviaDados(getString(R.string.servidor)+getString(R.string.path)+"profileStart.php");


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


    //#################################### SERVIDOR PROFILE ######################################
    void enviaDados(String url){
        StringRequest request = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        user = new Profile();

                        try {
                            JSONObject json = new JSONObject(response);

                            user.setIdRegistro(json.getString("idRegistro"));
                            user.setEmail(json.getString("email"));
                            user.setNome(json.getString("nome"));
                            user.setSexo(json.getInt("sexo"));
                            user.setTelefone(json.getString("telefone"));
                            user.setData_nasc(json.getString("dataNascimento"));
                            user.setGestante(json.getInt("gestante"));
                            user.setFotoCaminho(json.getString("fotoCaminho"));

                            db.addUser(user);

                            Intent it = new Intent(Inicio.this, MainActivity.class);
                            finish();
                            startActivity(it);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Inicio.this, "ERRO: "+ error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }){
                @Override
                public Map<String, String> getParams() throws AuthFailureError{
                    params = new HashMap<String, String>();
                    params.put("nome", userNome);
                    params.put("email", userEmail);
                    params.put("fotoCaminho", picturePath);
                    params.put("key", "123"); // caso precise de usar uma chave
                    return (params);
                }
        };

        request.setTag("tag");
        requestQueue.add(request);
    }


    // Destroi rescontrução da activity
    @Override
    public void onStop(){
        super.onStop();
        requestQueue.cancelAll("tag");
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
