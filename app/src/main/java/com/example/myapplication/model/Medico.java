package com.example.myapplication.model;

public class Medico {
    private int id;
    private String nome;
    private String especialidade;
    private String exames[];
    private String observação;
    private int gestante;
    private int idData;

    public Medico(String nome, String especialidade, String[] exames, String observação, int gestante, int idData) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.exames = exames;
        this.observação = observação;
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

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String[] getExames() {
        return exames;
    }

    public void setExames(String[] exames) {
        this.exames = exames;
    }

    public String getObservação() {
        return observação;
    }

    public void setObservação(String observação) {
        this.observação = observação;
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
