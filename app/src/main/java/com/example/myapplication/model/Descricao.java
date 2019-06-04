package com.example.myapplication.model;

public class Descricao {


    private int id;
    private String nome;


    public Descricao(String nome) {
        this.nome = nome;
    }

    public Descricao(){

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

}


