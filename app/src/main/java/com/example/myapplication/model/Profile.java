/**
 * Classe responsável pelos dados pessoais do usuário
 * Contém todos os itens da tabela PROFILE
 *
 * Trabalha com data nascimento com tipo DATE recebendo uma string no formato yyyy-MM-dd
 * */
package com.example.myapplication.model;

public class Profile {


    private int id;
    private String idRegistro;
    private String  nome;
    private String  email;
    private int sexo;
    private String data_nasc;
    private String  telefone;
    private String fotoCaminho;
    private int gestante;
    private int idDataCriacao;




    public Profile(String idRegistro, String nome, String email, int sexo, String data_nasc, String telefone, String fotoCaminho, int gestante, int idDataCriacao) {
        this.idRegistro = idRegistro;
        this.nome = nome;
        this.email = email;
        this.sexo = sexo;
        this.data_nasc = data_nasc;
        this.telefone = telefone;
        this.fotoCaminho = fotoCaminho;
        this.gestante = gestante;
        this.idDataCriacao = idDataCriacao;
    }

    public Profile(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public String getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(String data_nasc) {
        this.data_nasc = data_nasc;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getFotoCaminho() {
        return fotoCaminho;
    }

    public void setFotoCaminho(String fotoCaminho) {
        this.fotoCaminho = fotoCaminho;
    }

    public int getGestante() {
        return gestante;
    }

    public void setGestante(int gestante) {
        this.gestante = gestante;
    }

    public int getIdDataCriacao() {
        return idDataCriacao;
    }

    public void setIdDataCriacao(int idDataCriacao) {
        this.idDataCriacao = idDataCriacao;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", idRegistro='" + idRegistro + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", sexo=" + sexo +
                ", data_nasc='" + data_nasc + '\'' +
                ", telefone='" + telefone + '\'' +
                ", fotoCaminho='" + fotoCaminho + '\'' +
                ", gestante=" + gestante +
                ", idDataCriacao=" + idDataCriacao +
                '}';
    }
}
