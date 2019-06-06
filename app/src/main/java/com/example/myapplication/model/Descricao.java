package com.example.myapplication.model;

public class Descricao {


    private int id;
    private String  nome;
    private String  data_nasc;
    private String  email;
    private String  telefone;
    private String  endereco;
    private String cep;
    private String  bairro;
    private String  complemento;
    private String  numero;

    private String  sexo;
    private String  gestante;

    public Descricao(String nome, String data_nasc, String email, String telefone, String endereco, String cep, String bairro, String complemento, String numero, String sexo, String gestante) {
        this.nome = nome;
        this.data_nasc = data_nasc;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cep = cep;
        this.bairro = bairro;
        this.complemento = complemento;
        this.numero = numero;
        this.sexo = sexo;
        this.gestante = gestante;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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
}
