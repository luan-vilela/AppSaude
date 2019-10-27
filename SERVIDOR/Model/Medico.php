<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Medico
 *
 * @author luan
 */


class Medico {
    private $id;
    private $nome;
    private $especialidade;
    private $exames;
    private $observacao;
    private $gestante;
    private $idRegistro;
    private $idData;
    
    function __construct($nome, $especialidade, $exames, $observacao, $gestante, $idRegistro, $idData) {
        $this->nome = $nome;
        $this->especialidade = $especialidade;
        $this->exames = $exames;
        $this->observacao = $observacao;
        $this->gestante = $gestante;
        $this->idRegistro = $idRegistro;
        $this->idData = $idData;
    }
    
    
    function getIdRegistro() {
        return $this->idRegistro;
    }

    function setIdRegistro($idRegistro) {
        $this->idRegistro = $idRegistro;
    }

        
    function getId() {
        return $this->id;
    }

    function getNome() {
        return $this->nome;
    }

    function getEspecialidade() {
        return $this->especialidade;
    }

    function getExames() {
        return $this->exames;
    }

    function getObservacao() {
        return $this->observacao;
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

    function setEspecialidade($especialidade) {
        $this->especialidade = $especialidade;
    }

    function setExames($exames) {
        $this->exames = $exames;
    }

    function setObservacao($observacao) {
        $this->$observacao = $observacao;
    }

    function setGestante($gestante) {
        $this->gestante = $gestante;
    }

    function setIdData($idData) {
        $this->idData = $idData;
    }

    function toString(){
        return $this->nome +"-"+ $this->especialidade +"-"+$this->exames +"-"+$this->observacao+ "-"+ $this->gestante + "-";
    }


}
