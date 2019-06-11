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
import android.widget.Toast;

import com.example.myapplication.model.Crud;
import com.example.myapplication.model.Laudo;
import com.example.myapplication.model.Medico;
import com.example.myapplication.model.Profile;

import java.io.File;
import java.util.Arrays;

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
        Medico med = new Medico("Dr. Carlos Machado","clinico geral",exame, "Sem observação", 0, db.addData("Afonso pena", "1990-12-30 10:59:59"));
        db.addMedico(med);
        String[]  exame2 = {"exame de sangue"};
        med = new Medico("Dra. Maria Lucia Souza","pediatra",exame2, "Sem observação", 0, db.addData("Afonso pena", "2010-01-10 10:59:59"));
        db.addMedico(med);
        String[]  exame3 = {"cirurgia cardiaca, bateria de exames"};
        med = new Medico("Dra. Carol Dias Pinto","cardiologista",exame3, "Sem observação", 0, db.addData("Afonso pena", "2018-06-15 10:59:59"));
        db.addMedico(med);
        String[]  exame4 = {"Teste de contato, Biopsia, Exame por luz de Wood"};
        med = new Medico("Dra. Ricardo Luís Matos","dermatologista",exame4, "Sem observação", 0, db.addData("Afonso pena", null));
        db.addMedico(med);

        // ############### DEBUG LAUDO #############################

        Laudo objLaudo = new Laudo("Ressonância Magnética das Coxas", "Um tipo de procedimento é a biopsia com punch (em geral com 4 mm de diâmetro).", 1, db.addData("Aqui", null));
        db.addLaudo(objLaudo);
        objLaudo = new Laudo("Raspagem da pele", "Os raspados de pele auxiliam no diagnóstico de infecções fúngicas e escabiose. Para infecções fúngicas, a escama é obtida da borda da lesão e colocada em uma lâmina de microscópio. Então, adiciona-se uma gota de hidróxido de potássio a 10% a 20%. Hifas e/ou brotos de leveduras confirmam o diagnóstico de tinha ou candidíase. Na escabiose, o raspado é obtido dos túneis suspeitos, colocado imediatamente na lâmina com óleo mineral e recoberto por uma lamínula; o achado de ácaros, fezes ou ovos confirma o diagnóstico.", 1, db.addData("Aqui", null));
        db.addLaudo(objLaudo);



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