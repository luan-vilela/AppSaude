package com.example.myapplication.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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

    /**
     * Adiciona uma data e local no banco de dados
     * Data gerada automaticamente e local passado por string.
     *
     * Recebe um local(STRING)
     * Grava data e local no banco
     *
     * Retorna id salvo no banco
     *

    /**
     * Adiciona uma data e local no banco de dados
     * Data gerada automaticamente e local passado por string.
     *
     * Recebe um local(STRING)
     * Grava data e local no banco
     *
     * Retorna id salvo no banco
     * */
    public int addData(String local, String data){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // caso não passe data ele cria ela com a do dia
        if(data == null) {
            //gera data DATETIME
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            values.put("data", dateFormat.format(date));
        }
        else{
            values.put("data", data);
        }

        values.put("local", local);
        db.insert("data", null, values);
        db.close();

        return qtdRegistroDB("data");
    }

    /**
     * Retorna um Objeto Data do banco de dados
     *
     * Recebe um id do tipo INTERIRO
     *
     * Retorna Data se encontrar ou null se não entrar ou estiver em formato errado
     * */
    public Data selecionaData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Data data = null;

        // Pega a data por id
        Cursor cursor = db.query("data", new String[]{"id", "data", "local"},
                "id = ?", new String[] {String.valueOf(id)}, null,null,null,null);

        if (cursor != null) {
            cursor.moveToFirst();

            // Caso não consiga criar data vai retornar null
            try {
                data = new Data(cursor.getString(1), cursor.getString(2));
                data.setId(cursor.getInt(0));
            } catch (ParseException e) {
                data = null;
            }

        }
        db.close();

        return data;

    }

    /**
     * Conta a quantidade de Registro em uma tabela
     * Pode ser usado para verificar quantos Laudos,Documentos, etc... existem cadastrados
     * em determinada tabela.
     *
     * Recebe um nome de tabela (STRING)
     * Retorna um (INT) com número de registro da tabela
     * */
    public int qtdRegistroDB(String TabelaName){
        String query = "SELECT count(*) FROM " + TabelaName;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        cursor.moveToFirst();
        db.close();
        return cursor.getInt(0);
    }



    /**
     * Adiciona um único usuário ao banco
     *
     * Recebe um Profile e cadastra no banco profile
     * */
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

        // Cadastra data e hora que foi feito o registro
        // local poderia ser usado para GPS
        addData("desconhecido", null);

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

    public void addMedico(Medico medico){
        //String nome, String especialidade, String[] exames, String observação, int gestante, int idData)

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nome", medico.getNome());
        values.put("especialidade", medico.getEspecialidade());
        values.put("examesPedidos", medico.getExames());
        values.put("observacao", medico.getObservação());
        values.put("gestante", medico.getGestante());
        values.put("idData", medico.getIdData());

        db.insert("medico", null,  values);
        db.close();
    }

    public Medico selecionaMedico(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Medico medico = null;
        Cursor cursor = db.query("medico", new String[]{"id", "nome", "especialidade", "examesPedidos", "observacao", "gestante" , "idData"},
                "id = ?", new String[] {String.valueOf(id)}, null,null,null,null);

        if (cursor != null) {
            cursor.moveToFirst();
//    public Medico(String nome, String especialidade, String[] exames, String observação, int gestante, int idData)
            medico = new Medico(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3).split(","),
                    cursor.getString(4),
                    cursor.getInt(5),
                    cursor.getInt(6)
            );
            medico.setId(cursor.getInt(0));
            db.close();
        }
        return  medico;
    }

    public ArrayList<MedicoListView> listaTodosMedicos(){
        ArrayList<MedicoListView> listaMedico = new ArrayList<MedicoListView>();

        String query = "SELECT medico.id, medico.nome, medico.especialidade, medico.examesPedidos, data.data FROM medico INNER JOIN data " +
                "ON medico.idData = data.id";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do{
                //(int id, String nome, String especialidade, String exames, String data)
                //custom array adapter
                MedicoListView medico = new MedicoListView();
                medico.setId(Integer.parseInt(cursor.getString(0)));
                medico.setNome(cursor.getString(1));
                medico.setEspecialidade(cursor.getString(2));
                medico.setExames(cursor.getString(3));
                medico.setData(cursor.getString(4));

                listaMedico.add(medico);
            }while (cursor.moveToNext());
        }

        return listaMedico;
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
                        cursor.getString(1),
                        cursor.getString(3),
                        cursor.getString(2),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString

                                (7)
                );
                // seta id
                endereco.setId(Integer.parseInt(cursor.getString(0)));
            }
        db.close();
        return  endereco;
    }


    public void atualizaProfile(Profile user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", user.getNome() );
        values.put("email", user.getEmail() );
        values.put("sexo", user.getSexo() );
        values.put("dataNascimento", user.getData_nasc());
        values.put("telefone", user.getTelefone() );
        values.put("fotoCaminho", user.getFotoCaminho() );
        values.put("idRegistro", user.getIdRegistro() );
        values.put("gestante", user.getGestante() );
        values.put("idDate", user.getIdDataCriacao() );

        db.update("profile", values, "id = ?", new  String[] { String.valueOf(1) });
        db.close();
    }

    public void atualizaEndereco(Endereco endereco){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("rua", endereco.getRua());
        values.put("complemento", endereco.getComplemento());
        values.put("numero", endereco.getNumero());
        values.put("bairro", endereco.getBairro());
        values.put("codPost", endereco.getCodPost());
        values.put("provincia", endereco.getProvincia());
        values.put("pais", endereco.getPais());

        db.update("endereco", values, "id = ?", new  String[] { String.valueOf(1) });
        db.close();
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
