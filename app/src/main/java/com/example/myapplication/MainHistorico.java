package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.myapplication.model.Crud;
import com.example.myapplication.model.MedicoListView;
import com.example.myapplication.model.MyAdapterMedico;

import java.util.ArrayList;

public class MainHistorico extends AppCompatActivity {

    private ListView lista;
    Crud db = new Crud(this);
    // vai salvar os objetos customizados para lista
    ArrayList<MedicoListView> listaMedicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);


        lista = findViewById(R.id.listaMedico);


        listaMedicos = new ArrayList<>();
        listaMedicos = db.listaTodosMedicos();
        MyAdapterMedico adapter = new MyAdapterMedico(this, listaMedicos);
        lista.setAdapter(adapter);
    }


    //Botão Cancelar
    public void btnCancel(View view) {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent it = new Intent(this, MainActivity.class);
        finish();
        startActivity(it);
    }

}
