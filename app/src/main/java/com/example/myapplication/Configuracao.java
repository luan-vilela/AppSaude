package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Configuracao extends AppCompatActivity {


@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao);


    Spinner spinner = (Spinner) findViewById(R.id.generos);
    Spinner spinner2 = (Spinner) findViewById(R.id.gestante);

    String spinnerArray[] = {"Masculino", "Feminino"};
    String spinnerArray2[] = {"Sim", "Nao"};

    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);
    ArrayAdapter<String> spinnerAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray2);

    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    spinner.setAdapter(spinnerAdapter);
    spinner2.setAdapter(spinnerAdapter2);



}
}
