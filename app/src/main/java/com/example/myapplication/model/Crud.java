package com.example.myapplication.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class Crud extends Connect {

    public Crud(Context context) {
        super(context);
    }

    public void addUser(Descricao descricao){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        /* Adiciona o campo */
        values.put("nomeUser", descricao.getNome() );
        values.put("data_Nas", descricao.getData_nasc() );
        values.put("emailUser", descricao.getEmail() );
        values.put("sexo", descricao.getSexo() );
        values.put("gestante", descricao.getGestante() );
        values.put("telefoneUser", descricao.getTelefone() );
        values.put("enderecoUser", descricao.getEndereco() );
        values.put("cep", descricao.getCep() );
        values.put("numero", descricao.getNumero() );
        values.put("bairro", descricao.getBairro() );
        values.put("complemento", descricao.getComplemento() );

        db.insert("usuario", null,  values);
        db.close();

    }


}
