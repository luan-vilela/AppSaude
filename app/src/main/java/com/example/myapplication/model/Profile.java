/**
 * Classe responsável pelos dados pessoais do usuário
 * Contém todos os itens da tabela PROFILE
 *
 * Trabalha com data nascimento com tipo DATE recebendo uma string no formato yyyy-MM-dd
 * */
package com.example.myapplication.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Profile {


    private int id;
    private String idRegistro;
    private String  nome;
    private String  email;
    private int sexo;
    private Date data_nasc;
    private String  telefone;
    private String fotoCaminho;
    private int gestante;
    private int idDataCriacao;

    // Formato que deve ser a data
    SimpleDateFormat format;


    public Profile(String idRegistro, String nome, String email, int sexo, String data_nasc, String telefone, String fotoCaminho, int gestante, int idDataCriacao) {
        format = new SimpleDateFormat("yyyy-MM-dd");
        this.idRegistro = idRegistro;
        this.nome = nome;
        this.email = email;
        this.sexo = sexo;
        try {
            this.data_nasc = format.parse(data_nasc);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.telefone = telefone;
        this.fotoCaminho = fotoCaminho;
        this.gestante = gestante;
        this.idDataCriacao = idDataCriacao;
        this.format = format;
    }

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

    public Date getData_nasc() {
        return data_nasc;
    }
    public String getData_nascString() {
        return format.format(data_nasc);
    }

    public void setData_nasc(Date data_nasc) {
        this.data_nasc = data_nasc;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

    public SimpleDateFormat getFormat() {
        return format;
    }

    public void setFormat(SimpleDateFormat format) {
        this.format = format;
    }
}
