package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ParseException;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.myapplication.model.Crud;
import com.example.myapplication.model.Documento;

import java.util.ArrayList;
import java.util.List;

import static com.example.myapplication.model.Connect.TABLE_DOCUMENTO;

public class MeusDocumentos extends AppCompatActivity {
    private FloatingActionButton add;

    ListView listView;
    Crud bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_documentos);

        add = findViewById(R.id.btnAdd);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MeusDocumentos.this, MainDocumento.class);
                startActivity(it);
            }
        });

        //listView = (ListView) findViewById(R.id.lista);
        //List<Documento> documentos = preencher();

        //AdapterPerso adapter = new AdapterPerso(documentos, this);
        //listView.setAdapter(adapter);

    }

    private List<Documento> preencher() {
        String sql = String.format("SELECT * from %s", TABLE_DOCUMENTO);
        SQLiteDatabase db = bd.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        List<Documento> documentos = new ArrayList<>();
        while(cursor.moveToNext()){
            Documento documento = new Documento(null, null);
            documento.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            //documento.setFoto(cursor.getString(cursor.getColumnIndex("foto")));
            documentos.add(documento);
        }
        cursor.close();
        return documentos;
    }

    public void btnCarregarOnclick(View view) {

    }

    public void btnCancel(View view){
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }
}
