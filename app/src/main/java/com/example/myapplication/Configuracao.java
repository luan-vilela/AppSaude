package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.model.Crud;
import com.example.myapplication.model.Descricao;
import com.example.myapplication.Configuracao;



public class Configuracao extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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






        /**
         * Spinner que seleciona o sexo
         * Array do Masculino e feminino está puxando de String.xml assim quando mudar
         * idioma vai atualizar no menu também.
         * */

        Spinner spnGenero = (Spinner) findViewById(R.id.generos);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.sexoSpiner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnGenero.setAdapter(adapter);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void btnCancel(View v){
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }
}
