package com.example.myapplication.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class Crud extends Connect {

    public Crud(Context context) {
        super(context);
    }

    public void addDoc(Documento doc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("descricao", doc.getDescricao());
        values.put("foto", doc.getFoto());

        db.insert("Documento", null, values);
        db.close();
    }

    public void addUser(Profile user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
//
//        /* Adiciona o campo */
        values.put("name", user.getNome() );
        values.put("email", user.getEmail() );
        values.put("sexo", user.getSexo() );
        values.put("dataNascimento", user.getData_nasc());
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

    /**
     * Retorna um objeto do tipo Profile
     * Pode ser usado para manipular dados
     * // exemplo pegar a foto atual do banco de dados
     *  profile.fotoCaminho
     *
     * */
    public Profile selecionaProfile(){
        Profile profile = null;
        Cursor cursor = null;
        SQLiteDatabase db = this.getWritableDatabase();
        cursor = db.query("profile", new String[]{"id", "name", "email", "sexo", "dataNascimento", "telefone", "fotoCaminho", "idRegistro", "gestante","idDate"},
                "id = ?", new String[] {String.valueOf(1)}, null,null,null,null);

        if (cursor != null)
            if(cursor.moveToFirst()) {
//          (String idRegistro, String nome, String email, int sexo, String data_nasc, String telefone, String fotoCaminho, int gestante, int idDataCriacao)
                profile = new Profile(
                        cursor.getString(7),
                        cursor.getString(1),
                        cursor.getString(2),
                        Integer.parseInt(cursor.getString(3)),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        Integer.parseInt(cursor.getString(8)),
                        Integer.parseInt(cursor.getString(9))
                );
                // seta id
                profile.setId(Integer.parseInt(cursor.getString(0)));
            }
        db.close();
        return  profile;
    }

    /**
     * Retorna um objeto do tipo Endereço
     * Pode ser usado para manipular dados
     * */
    public Endereco selecionaEndereco(){
        Endereco endereco = null;
        Cursor cursor = null;
        SQLiteDatabase db = this.getWritableDatabase();
        cursor = db.query("endereco", new String[]{"id", "rua", "complemento", "numero", "bairro", "codPost", "provincia", "pais"},
                "id = ?", new String[] {String.valueOf(1)}, null,null,null,null);

        if (cursor != null)
            if(cursor.moveToFirst()) {
//          (String rua, String numero, String complemento, String bairro, String codPost, String provincia, String pais)
                endereco = new Endereco(
                        cursor.getColumnName(1),
                        cursor.getColumnName(3),
                        cursor.getColumnName(2),
                        cursor.getColumnName(4),
                        cursor.getColumnName(5),
                        cursor.getColumnName(6),
                        cursor.getColumnName(7)
                );
                // seta id
                endereco.setId(Integer.parseInt(cursor.getString(0)));
            }
        db.close();
        return  endereco;
    }


    /**
     * Altera foto apenas, sem precisar puxar todos os dados
     * */
    public void setaFoto(String local){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("fotoCaminho", local);

        //atualiza local da foto
        db.update("profile", values, "id = ?", new String[] {String.valueOf(1)});
        db.close();
    }

}
