package com.example.myapplication.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Connect extends SQLiteOpenHelper {


    private static final String DATABASE = "database";
    private static final int VERSIOM_DATABASE = 1;

    // Tabela profile
    public static final String TABLE_PROFILE = "" +
            "CREATE TABLE IF NOT EXISTS profile(" +
            "id INTEGER PRIMARY KEY, " +
            "name CHARACTER(100), " +
            "email TEXT, " +
            "sexo BOOLEAN, " +
            "dataNascimento DATE, " +
            "telefone TEXT, " +
            "fotoCaminho TEXT, " +
            "idRegistro TEXT, " +
            "gestante BOOLEAN, " +
            "idEndereco INTEGER, " +
            "idData INTEGER, " +
            "FOREIGN KEY(idEndereco) REFERENCES endereco(id), " +
            "FOREIGN KEY(idData) REFERENCES data(id)" +
            ")";

    // Tabela data
    public static final String TABLE_DATA = "CREATE TABLE IF NOT EXISTS  data(id INTEGER PRIMARY KEY, data DATETIME, local TEXT)";

    // Tabela endereço
    public static final String TABLE_ENDERECO = "" +
            "CREATE TABLE IF NOT EXISTS endereco(" +
            "id INTEGER PRIMARY KEY, " +
            "rua TEXT, " +
            "complemento TEXT, " +
            "numero TEXT, " +
            "bairro TEXT, " +
            "codPost TEXT, " +
            "provincia TEXT, " +
            "pais TEXT" +
            ")";

    // Tabela Documento
    public static final String TABLE_DOCUMENTO = "" +
            "CREATE TABLE IF NOT EXISTS documento(" +
            "id INTEGER PRIMARY KEY, " +
            "nome TEXT, " +
            "descricao TEXT, " +
            "local_dispositivo TEXT, " +
            "idData INTEGER, " +
            "FOREIGN KEY(idData) REFERENCES data(id))";


    // Tabela médico
    public static final String TABLE_MEDICO = "" +
            "CREATE TABLE IF NOT EXISTS medico(" +
            "id INTEGER PRIMARY KEY, " +
            "nome TEXT, " +
            "especialidade TEXT, " +
            "examesPedidos TEXT, " +
            "observacao TEXT, " +
            "gestante BOOLEAN, " +
            "idData INTEGER, " +
            "idProfile INTEGER, " +
            "FOREIGN KEY(idData) REFERENCES data(id), " +
            "FOREIGN KEY(idProfile) REFERENCES profile(id)" +
            ")";

    public Connect(Context context) {
        super(context, DATABASE, null, VERSIOM_DATABASE);
    }

    // Tabela Laudo
    public static final String TABLE_LAUDO = "" +
            "CREATE TABLE IF NOT EXISTS laudo(" +
            "id INTEGER PRIMARY KEY, " +
            "nome TEXT, " +
            "local_dispositivo TEXT, " +
            "gestante BOOLEAN, " +
            "idData INTEGER, " +
            "idProfile INTEGER, " +
            "FOREIGN KEY(idData) REFERENCES data(id), " +
            "FOREIGN KEY(idProfile) REFERENCES profile(id)" +
            ")";

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Criação das tabelas
        db.execSQL(TABLE_PROFILE);
        db.execSQL(TABLE_DATA);
        db.execSQL(TABLE_ENDERECO);
        db.execSQL(TABLE_DOCUMENTO);
        db.execSQL(TABLE_MEDICO);
        db.execSQL(TABLE_LAUDO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS endereco");
            db.execSQL("DROP TABLE IF EXISTS documento");
            db.execSQL("DROP TABLE IF EXISTS medico");
            db.execSQL("DROP TABLE IF EXISTS laudo");
            db.execSQL("DROP TABLE IF EXISTS data");
            db.execSQL("DROP TABLE IF EXISTS profile");
            onCreate(db);
        }
    }


}
