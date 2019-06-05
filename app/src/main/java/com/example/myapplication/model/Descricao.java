package com.example.myapplication.model;

public class Descricao {


    private int id;
    private String  nome;
    private String  data_nasc;
    private String  email;
    private String  sexo;
    private String  gestante;
    private Integer telfone;
    private String  endereco;
    private String  bairro;
    private String  complemento;

    public Descricao(String nome, String data_nasc, String email, String sexo, String gestante, Integer telfone, String endereco, String bairro, String complemento) {
        this.nome = nome;
        this.data_nasc = data_nasc;
        this.email = email;
        this.sexo = sexo;
        this.gestante = gestante;
        this.telfone = telfone;
        this.endereco = endereco;
        this.bairro = bairro;
        this.complemento = complemento;
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

    public String getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(String data_nasc) {
        this.data_nasc = data_nasc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getGestante() {
        return gestante;
    }

    public void setGestante(String gestante) {
        this.gestante = gestante;
    }

    public Integer getTelfone() {
        return telfone;
    }

    public void setTelfone(Integer telfone) {
        this.telfone = telfone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
