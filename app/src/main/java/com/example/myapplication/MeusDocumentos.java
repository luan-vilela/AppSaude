package com.example.myapplication;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MeusDocumentos extends AppCompatActivity {
    private FloatingActionButton add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_documentos);

        add = findViewById(R.id.btnAdd);

        //List<Documento> documentos =
        //ListView lista = (ListView) findViewById(R.id.lista);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MeusDocumentos.this, Documento.class);
                startActivity(it);
            }
        });

    }

    public void btnCancel(View v){
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }
}
