package com.example.myapplication.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;


public class Crud extends Connect {

    public Crud(Context context) {
        super(context);
    }

    public void addUser(Profile user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
//
//        /* Adiciona o campo */
        values.put("name", user.getNome() );
        values.put("email", user.getEmail() );
        values.put("sexo", user.getSexo() );
        //values.put("dataNascimento", user.getData_nascString());
        values.put("telefone", user.getTelefone() );
        values.put("fotoCaminho", user.getFotoCaminho() );
        values.put("idRegistro", user.getIdRegistro() );
        values.put("gestante", user.getGestante() );
        values.put("idDate", user.getIdDataCriacao() );

//
//        // Insere na tabela
        db.insert("profile", null,  values);
        db.close();

    }


    public void addEndereco(Endereco endereco){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //(id INTEGER PRIMARY KEY, rua TEXT, complemento TEXT, numero TEXT, bairro TEXT, codPost TEXT, provincia TEXT, pais TEXT)
        values.put("rua", endereco.getRua());
        values.put("complemento", endereco.getComplemento());
        values.put("numero", endereco.getNumero());
        values.put("bairro", endereco.getBairro());
        values.put("codPost", endereco.getCodPost());
        values.put("provincia", endereco.getProvincia());
        values.put("pais", endereco.getPais());

        db.insert("endereco", null,  values);
        db.close();
    }

}
