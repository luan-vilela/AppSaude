package com.example.myapplication.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Connect extends SQLiteOpenHelper {


    private static final String DATABASE = "ConsultasOnline";
    private static final int VERSIOM_DATABASE = 1;

    public static final String TABLE_USUARIO = "" +
            "CREATE TABLE IF NOT EXISTS usuario (" +
            "userId INTEGER PRIMARY KEY," +
            "nomeUser VARCHAR(50) NOT NULL," +
            "data_Nas VARCHAR(50) NOT NULL," +
            "emailUser VARCHAR(80) NOT NULL," +
            "sexo ENUM('Masculino', 'Feminino') NOT NULL," +
            "gestante ENUM('Sim', 'Nao') NOT NULL," +
            "telefoneUser VARCHAR(11)," +
            "enderecoUser VARCHAR(80) NOT NULL," +
            "cep INTEGER NOT NULL,"+
            "numero INTEGER NOT NULL,"+
            "bairro VARCHAR(50) NOT NULL," +
            "complemento VARCHAR(50) NOT NULL," +")";


    public Connect(Context context) {
        super(context, DATABASE, null, VERSIOM_DATABASE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS usuario");
            onCreate(db);
        }
    }


}
