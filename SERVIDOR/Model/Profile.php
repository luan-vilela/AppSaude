<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Profile
 *
 * @author luan
 */
class Profile {
    private $id;
    private $idRegistro;
    private $nome;
    private $email;
    private $sexo;
    private $data_nasc;
    private $telefone;
    private $fotoCaminho;
    private $gestante;
    private $idDataCriacao;
    /**
     * Entre com id registro, nome, email, sexo, data_nascimento, telefone, fotoCaminho, $gestante, idDataCriacação
     */
    function __construct($idRegistro, $nome, $email, $sexo, $data_nasc, $telefone, $fotoCaminho, $gestante, $idDataCriacao) {
        $this->idRegistro = $idRegistro;
        $this->nome = $nome;
        $this->email = $email;
        $this->sexo = $sexo;
        $this->data_nasc = $data_nasc;
        $this->telefone = $telefone;
        $this->fotoCaminho = $fotoCaminho;
        $this->gestante = $gestante;
        $this->idDataCriacao = $idDataCriacao;
    }
 
    function getId() {
        return $this->id;
    }

    function getIdRegistro() {
        return $this->idRegistro;
    }

    function getNome() {
        return $this->nome;
    }

    function getEmail() {
        return $this->email;
    }

    function getSexo() {
        return $this->sexo;
    }

    function getData_nasc() {
        return $this->data_nasc;
    }

    function getTelefone() {
        return $this->telefone;
    }

    function getFotoCaminho() {
        return $this->fotoCaminho;
    }

    function getGestante() {
        return $this->gestante;
    }

    function getIdDataCriacao() {
        return $this->idDataCriacao;
    }

    function setId($id) {
        $this->id = $id;
    }

    function setIdRegistro($idRegistro) {
        $this->idRegistro = $idRegistro;
    }

    function setNome($nome) {
        $this->nome = $nome;
    }

    function setEmail($email) {
        $this->email = $email;
    }

    function setSexo($sexo) {
        $this->sexo = $sexo;
    }

    function setData_nasc($data_nasc) {
        $this->data_nasc = $data_nasc;
    }

    function setTelefone($telefone) {
        $this->telefone = $telefone;
    }

    function setFotoCaminho($fotoCaminho) {
        $this->fotoCaminho = $fotoCaminho;
    }

    function setGestante($gestante) {
        $this->gestante = $gestante;
    }

    function setIdDataCriacao($idDataCriacao) {
        $this->idDataCriacao = $idDataCriacao;
    }
    
    function arrayObject(){
        return ['nome' => $this->getNome(), 
            'sexo' => $this->getSexo(), 
            'dataNascimento' => $this->getData_nasc(), 
            'telefone' => $this->getTelefone(), 
            'fotoCaminho' => $this->getFotoCaminho(),
            'gestante' => $this->getGestante(),
            'email' => $this->getEmail(),
            'idRegistro' => $this->getIdRegistro()
            ];
    }
    
}
