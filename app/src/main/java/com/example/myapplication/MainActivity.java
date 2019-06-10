package com.example.myapplication;




import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.model.Crud;
import com.example.myapplication.model.Medico;
import com.example.myapplication.model.Profile;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private LinearLayout calendario,documento,laudo,historico;
    private TextView medicoContador;
    private TextView nome;

    private Crud db;
    private Profile user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Crud(this);

        // Debug Medico
        String[] exame = {"exame de sangue", "exame de tireoide"};
        Medico med = new Medico("medico","clinico geral",exame, "Sem observação", 0, db.addData("Afonso pena", "1990-12-30 10:59:59"));
        db.addMedico(med);
        med = new Medico("medico","clinico geral 2",exame, "Sem observação", 0, db.addData("Afonso pena", "1990-12-30"));
        db.addMedico(med);

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
        medicoContador = findViewById(R.id.txtDescricaoMedico);

        //Atualiza contadores de menu
        atualizaContadores();

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
                Intent it = new Intent(MainActivity.this, MainCalendario.class);
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
                Intent it = new Intent(MainActivity.this, MainLaudos.class);
                startActivity(it);
            }
        });

        historico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, MainHistorico.class);
                startActivity(it);
            }
        });



    }


    public void setarPropriedades(){
        nome.setText(user.getNome());
        File img = new  File(user.getFotoCaminho());

        if(img.exists()){
            // coloca foto válida no picturePath caso altera outros dados da tabela menos a foto
            Bitmap myBitmap = BitmapFactory.decodeFile(img.getAbsolutePath());
            ImageView minhaFoto = findViewById(R.id.ivImagem);
            minhaFoto.setImageBitmap(myBitmap);

        }

    }

    /** Atualiza view usuário com quantidade de dados */
    public void atualizaContadores(){
        medicoContador.setText( db.qtdRegistroDB("medico") + " " +getString(R.string.medicoContador));
    }
}