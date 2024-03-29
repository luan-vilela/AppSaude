package com.example.myapplication.model;

public class Laudo {

    private int id;
    private String nome;
    private String descrição;
    private int gestante;
    private int idData;

    public Laudo(String nome, String descrição, int gestante, int idData) {
        this.nome = nome;
        this.descrição = descrição;
        this.gestante = gestante;
        this.idData = idData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrição() {
        return descrição;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }

    public int getGestante() {
        return gestante;
    }

    public void setGestante(int gestante) {
        this.gestante = gestante;
    }

    public int getIdData() {
        return idData;
    }

    public void setIdData(int idData) {
        this.idData = idData;
    }
}
