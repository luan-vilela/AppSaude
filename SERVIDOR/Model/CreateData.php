<?php

    class CreateData extends Conn{
        
        private static $TABLE_PROFILE = "" .
            "CREATE TABLE IF NOT EXISTS profile (" .
            "id INTEGER PRIMARY KEY, " .
            "nome VARCHAR(100)," .
            "email TEXT," .
            "sexo INTEGER DEFAULT 0," .
            "dataNascimento TEXT," .
            "telefone TEXT," .
            "fotoCaminho TEXT," .
            "gestante INTEGER DEFAULT 0," .
            "idRegistro TEXT," .
            "idDate INTEGER" .
            ")";
        
        private static $TABLE_DATA = ""
                . "CREATE TABLE IF NOT EXISTS  data("
                . "id INTEGER PRIMARY KEY, "
                . "data DATETIME, local TEXT)";

        private static $TABELA_EVENTO = "" .
            "CREATE TABLE IF NOT EXISTS  evento(" .
            "id INTEGER PRIMARY KEY, " .
            "descricao TEXT, " .
            "idRegistro TEXT," .
            "idData INTEGER, " .
            "FOREIGN KEY(idData) REFERENCES data(id)" .
            ")";
        
        private static $TABLE_ENDERECO = "" .
            "CREATE TABLE IF NOT EXISTS endereco (" .
            "id INTEGER PRIMARY KEY, " .
            "rua TEXT, " .
            "complemento TEXT, " .
            "numero TEXT, " .
            "bairro TEXT, " .
            "codPost TEXT, " .
            "provincia TEXT, " .
            "pais TEXT" .
            ")";
        
        private static $TABLE_DOCUMENTO = "" .
            "CREATE TABLE IF NOT EXISTS documento (" .
            "id INTEGER PRIMARY KEY, " .
            "nome TEXT, " .
            "descricao TEXT, " .
            "local_dispositivo TEXT, " .
            "idData INTEGER, " .
            "FOREIGN KEY(idData) REFERENCES data(id)" .
            ")";
        
        private static $TABLE_MEDICO = "" .
            "CREATE TABLE IF NOT EXISTS medico (" .
            "id INTEGER PRIMARY KEY, " .
            "nome TEXT, " .
            "especialidade TEXT, " .
            "examesPedidos TEXT, " .
            "observacao TEXT, " .
            "gestante INTEGER, " .
            "idRegistro TEXT, " .
            "idData INTEGER, " .
            "FOREIGN KEY(idData) REFERENCES data(id) " .
            ")";
        
        private static $TABLE_LAUDO = "" .
            "CREATE TABLE IF NOT EXISTS laudo (" .
            "id INTEGER PRIMARY KEY, " .
            "nome TEXT, " .
            "descricao TEXT, " .
            "gestante BOOLEAN, " .
            "idRegistro TEXT," .
            "idData INTEGER, " .
            "FOREIGN KEY(idData) REFERENCES data(id) " .
            ")";
        
        
        function __construct(){}

        public function createDataBase() {
            parent::getConn()->exec(self::$TABELA_EVENTO);
            parent::getConn()->exec(self::$TABLE_DATA);
            parent::getConn()->exec(self::$TABLE_DOCUMENTO);
            parent::getConn()->exec(self::$TABLE_ENDERECO);
            parent::getConn()->exec(self::$TABLE_LAUDO);
            parent::getConn()->exec(self::$TABLE_MEDICO);
            parent::getConn()->exec(self::$TABLE_PROFILE);
            
        }

    }