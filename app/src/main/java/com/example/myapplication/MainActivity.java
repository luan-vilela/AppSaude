package com.example.myapplication;




import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LinearLayout calendario,documento,laudo,historico;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                finish();
                startActivity(it);
            }
        });

        calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,Calendario.class);
                finish();
                startActivity(it);
            }
        });

        documento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,MeusDocumentos.class);
                finish();
                startActivity(it);
            }
        });

        laudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,Laudo.class);
                finish();
                startActivity(it);
            }
        });

        historico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,Historico.class);
                finish();
                startActivity(it);
            }
        });



    }



    // ##################################################################
    //                  BOTÕES PRINCIPAIS DA MAIN                       #
    // ##################################################################
    void btnLaudo(View view){
        Toast.makeText(this,"Clicou no laudo", Toast.LENGTH_LONG).show();
    }

    void btnMedico(View view){
        Toast.makeText(this,"Clicou no Médico", Toast.LENGTH_LONG).show();
    }

    void btnCalendario(View view){
        Toast.makeText(this,"Clicou no Calendário", Toast.LENGTH_LONG).show();
    }
    void btnDocumento(View view){
        Toast.makeText(this,"Clicou no Documento", Toast.LENGTH_LONG).show();
    }







}