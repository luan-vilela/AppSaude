package com.example.myapplication;

import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.model.Connect;
import com.example.myapplication.model.Crud;
import com.example.myapplication.model.Endereco;
import com.example.myapplication.model.Profile;

import java.text.ParseException;


public class Configuracao extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText nome;
    private EditText dataNascimento;
    private EditText email;
    private EditText telefone;
    private TextView IdRegitroApp;
    private Switch gestanteSwitch;
    private Boolean gestante;

    private EditText endereco;
    private EditText cep;
    private EditText bairro;
    private EditText complemento;
    private EditText numero;
    private EditText pais;
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

        Spinner spnGenero = (Spinner) findViewById(R.id.generos);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.sexoSpiner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnGenero.setAdapter(adapter);


        /*
        * Linkando os botões
        */
        nome = findViewById(R.id.inputNome);
        dataNascimento = findViewById(R.id.inputNascimento);
        email = findViewById(R.id.inputEmail);
        telefone = findViewById(R.id.inputTelefone);
        gestanteSwitch = findViewById(R.id.gestante);

        endereco = findViewById(R.id.inputEndereco);
        cep = findViewById(R.id.inputCep);
        bairro = findViewById(R.id.inputBairro);
        complemento = findViewById(R.id.inputComplemento);
        numero = findViewById(R.id.inputNumero);
        pais = findViewById(R.id.inputPais);

        //debug user

        enderecoUser = new Endereco("minha rua", "140", "", "centro", "79000-000","MS", "Brasil");
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    //Botão salvar
    public void btnSave(View view) {
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
        String userEndereco = endereco.getText().toString();
        String userNumero = numero.getText().toString();
        String userCEP = cep.getText().toString();
        String userBairro = bairro.getText().toString();
        String userComplemento = complemento.getText().toString();
        String userPais = pais.getText().toString();

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


        if(teste == 1){
            user = new Profile("Luan#1234", userNome, userEmail, 1, "1990-09-13", "9999-9999", "/xx",0, 1);
            db.addUser(user);
            Toast.makeText(this,"Adicionado com sucesso", Toast.LENGTH_LONG).show();
        }



    }
    //Botão Cancelar
    public void btnCancel(View view){

    }



}
