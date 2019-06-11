package com.example.myapplication.model;

import android.widget.TextView;

import com.example.myapplication.R;

public class MedicoListView {
    private int id;
    private String nome;
    private String especialidade;
    private String exames;
    private String data;

    public MedicoListView(int id, String nome, String especialidade, String exames, String data) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
        this.exames = exames;
        this.data = data;
    }

    public MedicoListView(){};

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

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getExames() {
        return exames;
    }

    public void setExames(String exames) {
        this.exames = exames;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
