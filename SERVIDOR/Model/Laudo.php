<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Laudo
 *
 * @author luan
 */
class Laudo {
    private $id;
    private $nome;
    private $descricao;
    private $idRegistro;
    private $gestante;
    private $idData;
    
    function __construct($nome, $descricao, $idRegistro, $gestante, $idData) {
        $this->nome = $nome;
        $this->descricao = $descricao;
        $this->idRegistro = $idRegistro;
        $this->gestante = $gestante;
        $this->idData = $idData;
    }
    
    function getId() {
        return $this->id;
    }

    function getNome() {
        return $this->nome;
    }

    function getDescricao() {
        return $this->descricao;
    }

    function getIdRegistro() {
        return $this->idRegistro;
    }

    function getGestante() {
        return $this->gestante;
    }

    function getIdData() {
        return $this->idData;
    }

    function setId($id) {
        $this->id = $id;
    }

    function setNome($nome) {
        $this->nome = $nome;
    }

    function setDescricao($descricao) {
        $this->descricao = $descricao;
    }

    function setIdRegistro($idRegistro) {
        $this->idRegistro = $idRegistro;
    }

    function setGestante($gestante) {
        $this->gestante = $gestante;
    }

    function setIdData($idData) {
        $this->idData = $idData;
    }

}
