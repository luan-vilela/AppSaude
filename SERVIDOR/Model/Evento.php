<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Evento
 *
 * @author luan
 */
class Evento {
    private $id;
    private $descricao;
    private $idData;
    private $idRegistro;
    
    function __construct($descricao, $idData, $idRegistro) {
        $this->descricao = $descricao;
        $this->idData = $idData;
         $this->idRegistro = $idRegistro;
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

    function getDescricao() {
        return $this->descricao;
    }

    function getIdData() {
        return $this->idData;
    }

    function setId($id) {
        $this->id = $id;
    }

    function setDescricao($descricao) {
        $this->descricao = $descricao;
    }

    function setIdData($idData) {
        $this->idData = $idData;
    }



    
}
