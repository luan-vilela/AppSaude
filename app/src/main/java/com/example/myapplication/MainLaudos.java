package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.myapplication.model.Crud;
import com.example.myapplication.model.LaudoListView;
import com.example.myapplication.model.MedicoListView;
import com.example.myapplication.model.MyAdapterLaudo;
import com.example.myapplication.model.MyAdapterMedico;

import java.util.ArrayList;

public class MainLaudos extends AppCompatActivity {

    private ListView lista;
    Crud db = new Crud(this);
    // vai salvar os objetos customizados para lista
    ArrayList<LaudoListView> listaLaudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laudo);


        lista = findViewById(R.id.ListViewLaudo);


        listaLaudo = new ArrayList<>();
        listaLaudo = db.listaTodosLaudos();
        MyAdapterLaudo adapter = new MyAdapterLaudo(this, listaLaudo);
        lista.setAdapter(adapter);

    }
}
