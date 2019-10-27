package com.example.myapplication;




import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ParseException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.model.Crud;
import com.example.myapplication.model.Evento;
import com.example.myapplication.model.Laudo;
import com.example.myapplication.model.Medico;
import com.example.myapplication.model.Profile;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    //servidor
    private RequestQueue requestQueue;
    private Map<String, String> params;

    private LinearLayout calendario,documento,laudo,historico;
    private TextView medicoContador, laudoContador, eventoContador, id;
    private TextView nome;
    private Crud db;
    private Profile user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Crud(this);
        requestQueue = Volley.newRequestQueue(this);
        // Referência de layout
        nome = findViewById(R.id.txtNome);
        id = findViewById(R.id.idRegistro);
        calendario = findViewById(R.id.btnCalendario);
        documento = findViewById(R.id.btnDocumentos);
        laudo = findViewById(R.id.btnLaudos);
        historico = findViewById(R.id.btnHistoricos);
        medicoContador = findViewById(R.id.txtDescricaoMedico);
        laudoContador = findViewById(R.id.txtDescricaoLaudo);
        eventoContador = findViewById(R.id.txtDescricaoCalendario);


        // Seta nome do usuário
        user = db.selecionaProfile();
        if(user != null && user.getId() == 1) {
            setarPropriedades();
            atualizarEnvio();

        }
        else{
            Intent it = new Intent(MainActivity.this,Inicio.class);
            finish();
            startActivity(it);
        }
        // Botão flutuante para configurações
        FloatingActionButton add = findViewById(R.id.btnConfig);

        //Atualiza contadores de menu
        atualizaContadores();

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
                Intent it = new Intent(MainActivity.this, MainCalendario.class);
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

        if(user.getIdRegistro() == null || user.getIdRegistro().isEmpty() || user.getIdRegistro().equals("")){
            id.setText("#0000");
        }
        else {
            id.setText(user.getIdRegistro());
        }
        /*********** FOTO ************/
        // Verifica se existe foto no banco de dados
        // Se exister verifica se caminho é válido

        if(user.getFotoCaminho() != null){
            File img = new File(user.getFotoCaminho());

            if(img.exists()){
                // coloca foto válida no picturePath caso altera outros dados da tabela menos a foto
                Bitmap myBitmap = BitmapFactory.decodeFile(img.getAbsolutePath());
                ImageView minhaFoto = findViewById(R.id.ivImagem);
                minhaFoto.setImageBitmap(myBitmap);
            }
        }

    }

    /** Atualiza view usuário com quantidade de dados */
    public void atualizaContadores(){

        medicoContador.setText( db.qtdRegistroDB("medico") + " " +getString(R.string.medicoContador));
        laudoContador.setText( db.qtdRegistroDB("laudo") + " " +getString(R.string.laudoContador));
        eventoContador.setText(getString(R.string.eventoContador) + " " + db.qtdRegistroDB("evento") + " " + getString(R.string.eventoContador2));


    }




    //#################################### SERVIDOR PROFILE ######################################
    void enviaDados(String url){
        StringRequest request = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        String msg = null;

                        try {
                            int contador = 0;
                            JSONObject json = new JSONObject(response);

                            // ############         CARREGA MÉDICO      ############
                            JSONArray jsonMedico = json.getJSONArray("medico");
                            for(int i = db.qtdRegistroDB("medico"); i < jsonMedico.length(); i++){
                                JSONObject objMedico = jsonMedico.getJSONObject(i);
                                String[] exame = objMedico.getString("examesPedidos").split(",");
                                Medico med = new Medico( objMedico.getString("nome"), objMedico.getString("especialidade"),exame, objMedico.getString("observacao"), objMedico.getInt("gestante"), db.addData(objMedico.getString("local"), objMedico.getString("data")));
                                db.addMedico(med);
                                contador++;
                            }
                            if(contador == 1){
                                msg = getString(R.string.eventoContador) + " " + contador + " " + getString(R.string.novoMedico);
                                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                                contador = 0;
                            }
                            else if(contador > 1){
                                msg = getString(R.string.eventoContador) + " " + contador + " " + getString(R.string.novosMedicos);
                                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                                contador = 0;
                            }


                            // ############         CARREGA LAUDOS      ############
                            jsonMedico = json.getJSONArray("laudo");
                            for(int i = db.qtdRegistroDB("laudo"); i < jsonMedico.length(); i++){
                                JSONObject objectLaudo = jsonMedico.getJSONObject(i);
                                Laudo laudo = new Laudo(objectLaudo.getString("nome"), objectLaudo.getString("descricao"), objectLaudo.getInt("gestante"), db.addData(objectLaudo.getString("local"), objectLaudo.getString("data")));
                                db.addLaudo(laudo);
                                contador++;
                            }
                            if(contador == 1){
                                msg = getString(R.string.eventoContador) + " " + contador + " " + getString(R.string.novoLaudo);
                                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                                contador = 0;
                            }
                            else if(contador > 1){
                                msg = getString(R.string.eventoContador) + " " + contador + " " + getString(R.string.novosLaudos);
                                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                                contador = 0;
                            }


                            // ############         CARREGA EVENTOS      ############
                            jsonMedico = json.getJSONArray("evento");
                            for(int i = db.qtdRegistroDB("evento"); i < jsonMedico.length(); i++){
                                JSONObject objEvento = jsonMedico.getJSONObject(i);
                                Evento evento = new Evento(objEvento.getString("descricao"), db.addData(objEvento.getString("local"), objEvento.getString("data")));
                                db.addEvento(evento);
                                contador++;
                            }
                            if(contador == 1){
                                msg = getString(R.string.eventoContador) + " " + contador + " " + getString(R.string.novoEvento);
                                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                                contador = 0;
                            }
                            else if(contador > 1){
                                msg = getString(R.string.eventoContador) + " " + contador + " " + getString(R.string.novosEventos);
                                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                                contador = 0;
                            }



                            atualizaContadores();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }




                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "ERRO: "+ error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                params = new HashMap<String, String>();
                // Envia id registro para o servidor
                // ele vai retornar os dados do banco atualizados
                params.put("idRegistro", user.getIdRegistro());
                params.put("key", "123"); // caso precise de usar uma chave
                return (params);
            }
        };

        request.setTag("tagMedico");
        requestQueue.add(request);
    }


// ############# FAZ REQUISIÇÃO PARA O SERVIDOR DE 30s E 30s  #############
    void atualizarEnvio(){
        long TEMPO = (1000 * 10); // chama o método a cada 3 segundos
        Timer timer = null;
        if (timer == null) {
            timer = new Timer();
            TimerTask tarefa = new TimerTask() {

                public void run() {
                    try {
                        enviaDados(getString(R.string.servidor)+getString(R.string.path)+"serverContent.php");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            timer.scheduleAtFixedRate(tarefa, 1000, 1000*30);
        }


    }


}