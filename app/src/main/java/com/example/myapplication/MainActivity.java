package com.example.myapplication;




import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.model.Crud;
import com.example.myapplication.model.Profile;

public class MainActivity extends AppCompatActivity {
    private LinearLayout calendario,documento,laudo,historico;
    private TextView nome;

    private Crud db;
    private Profile user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Crud(this);

        nome = findViewById(R.id.txtNome);

        // Seta nome do usuário
        user = db.selecionaProfile();
        if(user != null && user.getId() == 1) {
            setarPropriedades();

        }
        // Botão flutuante para configurações
        FloatingActionButton add = findViewById(R.id.btnConfig);

        calendario = findViewById(R.id.btnCalendario);
        documento = findViewById(R.id.btnDocumentos);
        laudo = findViewById(R.id.btnLaudos);
        historico = findViewById(R.id.btnHistoricos);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,Configuracao.class);
                startActivity(it);
            }
        });

        calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,Historico.class);
                startActivity(it);
            }
        });

        documento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,MeusDocumentos.class);
                startActivity(it);
            }
        });

        laudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,Laudo.class);
                startActivity(it);
            }
        });

        historico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,Historico.class);
                startActivity(it);
            }
        });



    }


    public void setarPropriedades(){
        nome.setText(user.getNome());
        
    }
}