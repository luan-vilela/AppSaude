package com.example.myapplication;




import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Botão flutuante para configurações
        FloatingActionButton add = findViewById(R.id.btnConfig);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,Configuracao.class);
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