package com.example.myapplication.model;

public class Evento {

    private int id;
    private String descricao;
    private int idData;
    private String Data;

    public Evento(String descricao, int idData) {
        this.descricao = descricao;
        this.idData = idData;
    }
    public Evento(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdData() {
        return idData;
    }

    public void setIdData(int idData) {
        this.idData = idData;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }
}
