package com.example.myapplication.model;

public class Documento {
    private int id;
    private String descricao;
    private String foto;

    public Documento(String descricao, String foto) {
        this.descricao = descricao;
        this.foto = foto;
    }

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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "MainDocumento{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", foto='" + foto +
                '}';
    }
}
