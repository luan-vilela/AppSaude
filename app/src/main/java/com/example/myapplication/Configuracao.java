package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.model.Connect;
import com.example.myapplication.model.Crud;
import com.example.myapplication.model.Endereco;
import com.example.myapplication.model.Profile;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Configuracao extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //servidor
    private RequestQueue requestQueue;
    private Map<String, String> params;
    // Código da requisição
    private static final int PERMISSION_REQUEST_CODE = 200;
    // salvar local imagem
    private String picturePath = null;
    private ImageView minhaFoto;
    private EditText nome;
    private EditText dataNascimento;
    private EditText email;
    private EditText telefone;
    private TextView IdRegitroApp;
    private Switch gestanteSwitch;
    private Boolean gestante;
    Spinner spnGenero;
    // para trabalhar com pop-calendário
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private EditText rua;
    private EditText cep;
    private EditText bairro;
    private EditText complemento;
    private EditText numero;
    private EditText pais;
    private EditText estado;

    private Profile user;
    private Endereco enderecoUser;

    Crud db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao);

        // context para DatabaseopenHelper
        db = new Crud(this);

        /*
         * Spinner que seleciona o sexo
         * Array do Masculino e feminino está puxando de String.xml assim quando mudar
         * idioma vai atualizar no menu também.
         * */
        spnGenero = (Spinner) findViewById(R.id.generos);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.sexoSpiner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnGenero.setAdapter(adapter);


        /*
        * Linkando os botões
        */
        minhaFoto = (ImageView) findViewById(R.id.configImage);
        nome = findViewById(R.id.inputNome);
        dataNascimento = (EditText) findViewById(R.id.inputNascimento);
        dataNascimento.setKeyListener(null);
        dataNascimento.setFocusable(false);
        email = findViewById(R.id.inputEmail);
        telefone = findViewById(R.id.inputTelefone);
        gestanteSwitch = findViewById(R.id.gestante);
        IdRegitroApp = findViewById(R.id.idRegistro);
        rua = findViewById(R.id.inputEndereco);
        cep = findViewById(R.id.inputCep);
        bairro = findViewById(R.id.inputBairro);
        complemento = findViewById(R.id.inputComplemento);
        numero = findViewById(R.id.inputNumero);
        pais = findViewById(R.id.inputPais);
        estado = findViewById(R.id.inputEstado);

        //####################################################
        //Calendário
        // Chama calendário para evitar digitação
        dataNascimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Configuracao.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                String date = year + "-" + month + "-" + day;
                dataNascimento.setText(date);
            }
        };



        //####################################################
        //#   Aqui são carregados os dados na tela
        //# caso já tenha cadastro no celular

        user = db.selecionaProfile();
        if(user != null && user.getId() == 1){
            enderecoUser = db.selecionaEndereco();
            setarPropriedades();
        }


    }

    //Botão salvar
    public void btnSave(View view) {
        //Variável de teste
        // caso não seja especificado algo que deve ser escrito
        // teste muda para 0
        int teste = 1;
        String userNome = nome.getText().toString();
        String userEmail = email.getText().toString();
        String userPhone = telefone.getText().toString();
        String userNascimento = dataNascimento.getText().toString();
        // converter Booleano para int
        int userGestante = (gestanteSwitch.isChecked() == true) ? 1 : 0;


        /*
        * Parte do endereço
        * */
        String userRua = rua.getText().toString();
        String userNumero = numero.getText().toString();
        String userCEP = cep.getText().toString();
        String userBairro = bairro.getText().toString();
        String userComplemento = complemento.getText().toString();
        String userPais = pais.getText().toString();
        String userProvincia = estado.getText().toString();

        if(userNome == null || userNome.equals("")){
            nome.setError(getString(R.string.errorName));
            teste = 0;
        }
        if(userEmail == null || userEmail.equals("")){
            email.setError(getString(R.string.errorEmail));
            teste = 0;
        }
        if(userPhone == null || userPhone.equals("")){
            telefone.setError(getString(R.string.errorPhone));
            teste = 0;
        }
        if(userNascimento == null || userNascimento.equals("")){
            dataNascimento.setError(getString(R.string.errorData));
            teste = 0;
        }

        // Caso todos os campos obrigatórios estejam preenchidos
        if(teste == 1){
            requestQueue = Volley.newRequestQueue(this);
            // caso não tenha foto
            picturePath = (picturePath == null ? user.getFotoCaminho() : picturePath);
            // verificando se é para atualizar ou cadastrar
            if(user != null || enderecoUser != null) {
                user = new Profile(db.selecionaProfile().getIdRegistro(), userNome, userEmail, spnGenero.getSelectedItemPosition(), userNascimento, userPhone, picturePath, userGestante, 1);
                enderecoUser = new Endereco(userRua, userNumero, userComplemento, userBairro, userCEP, userProvincia, userPais);
                sucessAtualizar();
            }
            else {
                user = new Profile(user.getIdRegistro(), userNome, userEmail, spnGenero.getSelectedItemPosition(), userNascimento, userPhone, picturePath, userGestante, 1);
                enderecoUser = new Endereco(userRua, userNumero, userComplemento, userBairro, userCEP, userProvincia, userPais);
                db.addEndereco(enderecoUser);
                db.addUser(user);
                sucess();
            }




        }



    }
    //Botão Cancelar
    public void btnCancel(View view){
        Intent it = new Intent(this, MainActivity.class);
        finish();
        startActivity(it);
    }


    /**
     * Seta os campos da tela configurações
     * */
    public void setarPropriedades(){
        nome.setText(user.getNome());
        dataNascimento.setText(user.getData_nasc());
        email.setText(user.getEmail());
        telefone.setText(user.getTelefone());
        spnGenero.setSelection(user.getSexo());
        IdRegitroApp.setText(user.getIdRegistro());
        // Seta Switch da gestante
        // faz conversão de int para Boolean
        Boolean setGestante = (user.getGestante() == 0) ? false : true;
        gestanteSwitch.setChecked(setGestante);

        /*********** FOTO ************/
        // Verifica se existe foto no banco de dados
        // Se exister verifica se caminho é válido

        if(user.getFotoCaminho() != null){
            File img = new File(user.getFotoCaminho());

            if(img.exists()){
                // coloca foto válida no picturePath caso altera outros dados da tabela menos a foto
                Bitmap myBitmap = BitmapFactory.decodeFile(img.getAbsolutePath());
                minhaFoto.setImageBitmap(myBitmap);
            }
        }

        // endereço
        if(enderecoUser != null) {
            rua.setText(enderecoUser.getRua());
            complemento.setText(enderecoUser.getComplemento());
            numero.setText(enderecoUser.getNumero());
            bairro.setText(enderecoUser.getBairro());
            cep.setText(enderecoUser.getCodPost());
            pais.setText(enderecoUser.getPais());
            estado.setText(enderecoUser.getProvincia());
        }
    }


    /**
     * Carrega foto apartir do botão edit
     * */
    public void editFoto(View view){
        Intent it = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(it, "Selecione uma imagem"), 123);

    }

    // Mensagem de confirmação
    public void sucess(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.alertSave)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // envia dados servidor
                        enviaDados(getString(R.string.servidor)+getString(R.string.path)+"profile.php");
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    // Botão Deletar
    public void sucessAtualizar(){
        AlertDialog.Builder  builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.alertWarning)
                .setMessage(R.string.alertUpdate)
                .setCancelable(true)
                .setPositiveButton(R.string.btnConfirmar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Caso confirme a altualização de dados
                        // envia dados servidor
                        enviaDados(getString(R.string.servidor)+getString(R.string.path)+"profile.php");
                        if(db.qtdRegistroDB("endereco") == 1){
                            db.atualizaEndereco(enderecoUser);
                        }
                        else{
                            db.addEndereco(enderecoUser);
                        }

                        db.atualizaProfile(user);

                    }
                })
                .setNegativeButton(R.string.btnCancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        startActivity(getIntent());
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

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

                            finish();
                            startActivity(getIntent());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Configuracao.this, "ERRO: "+ error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                params = new HashMap<String, String>();
                params.put("nome", user.getNome());
                params.put("email", user.getEmail());
                params.put("fotoCaminho", user.getFotoCaminho());
                params.put("sexo", String.valueOf(user.getSexo()));
                params.put("dataNascimento", user.getData_nasc());
                params.put("telefone", user.getTelefone());
                params.put("gestante", String.valueOf(user.getGestante()));
                params.put("key", "123"); // caso precise de usar uma chave
                return (params);
            }
        };

        request.setTag("tag");
        requestQueue.add(request);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent it = new Intent(this, MainActivity.class);
        finish();
        startActivity(it);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //nada aqui
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //nada aqui
    }

}
