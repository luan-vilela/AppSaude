<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Data
 *
 * @author luan
 */
class DataServer {
    private $id;
    private $data;
    private $local;
    
    function __construct($data, $local) {
        $this->data = $data;
        $this->local = $local;
    }
    
    function getId() {
        return $this->id;
    }

    function getData() {
        return $this->data;
    }

    function getLocal() {
        return $this->local;
    }

    function setId($id) {
        $this->id = $id;
    }

    function setData($data) {
        $this->data = $data;
    }

    function setLocal($local) {
        $this->local = $local;
    }



}
