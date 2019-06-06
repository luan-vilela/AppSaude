package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.model.Crud;
import com.example.myapplication.model.Descricao;
import com.example.myapplication.Configuracao;



public class Configuracao extends AppCompatActivity {

    private EditText nome;
    private EditText dataNascimento;
    private EditText email;
    private EditText phone;
    private EditText endereco;
    private EditText cep;
    private EditText bairro;
    private EditText complemento;
    private EditText numero;


    Crud db;


@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao);


    Spinner spinner = (Spinner) findViewById(R.id.generos);
    Spinner spinner2 = (Spinner) findViewById(R.id.gestante);

    nome = (EditText) findViewById(R.id.btnNome);
    dataNascimento = (EditText) findViewById(R.id.btnNascimento);
    email = (EditText) findViewById(R.id.btnEmail);
    phone = (EditText) findViewById(R.id.btnTelefone);
    endereco = (EditText) findViewById(R.id.btnEndereco);
    cep = (EditText) findViewById(R.id.btnCep);
    bairro = (EditText) findViewById(R.id.btnBairro);
    complemento = (EditText) findViewById(R.id.btnComplemento);
    numero = (EditText) findViewById(R.id.btnNumero);

    db = new Crud(this);


    String spinnerArray[] = {"Masculino", "Feminino"};
    String spinnerArray2[] = {"Sim", "Nao"};

    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);
    ArrayAdapter<String> spinnerAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray2);

    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    spinner.setAdapter(spinnerAdapter);
    spinner2.setAdapter(spinnerAdapter2);

    }

    public void btnSave(View v){
        if(nome.getText().toString().isEmpty()){
            Toast.makeText(this, R.string.errorName,Toast.LENGTH_SHORT).show();
        }
        else if(dataNascimento.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.errorData, Toast.LENGTH_SHORT).show();
        }
        else if(email.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.errorEmail,Toast.LENGTH_SHORT).show();
        }
        else if(phone.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.errorPhone, Toast.LENGTH_SHORT).show();
        }
        else if(endereco.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.errorEndereco, Toast.LENGTH_SHORT).show();
        }
        else if(cep.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.errorCep, Toast.LENGTH_SHORT).show();
        }
        else if(bairro.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.errorBairro, Toast.LENGTH_SHORT).show();
        }
        else if(complemento.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.errorComplemento, Toast.LENGTH_SHORT).show();
        }
        else if(numero.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.errorNumero, Toast.LENGTH_SHORT).show();
        }

        db.addUser(new Descricao(nome.getText().toString(), dataNascimento.getText().toString(),email.getText().toString() ,phone.getText().toString(),endereco.getText().toString(),cep.getText().toString(),bairro.getText().toString(),complemento.getText().toString(),numero.getText().toString(),"Masculino","Sim"));
        // alerta de salvo com sucesso
        Toast.makeText(this,R.string.alertSave,Toast.LENGTH_SHORT).show();

    }

    public void btnCancel(View v){
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }
}
