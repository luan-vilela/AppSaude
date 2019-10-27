<?php

    class Conn{
        private static $dsnMemory = "sqlite::memory:";
        private static $dsn = "sqlite:Model/meuBanco.sqlite";
        private static $user = null;
        private static $pass = null;
        private static $opt = null;
        
        /** @var PDO */
        private static $connect = null;
        private static $connectMemory = null;

        /**
         * 
         * Funcão conecta banco local
         */
        private static function Connect(){
            try{
                // singleton
                if(self::$connect == null):
                    self::$connect = new PDO(self::$dsn, self::$user, self::$pass, self::$opt);
                endif;
            } catch (PDOException $e){
                echo '<strong>Erro: </strong>'. $e;
                die();
            }
            self::$connect->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            self::$connect->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
            return self::$connect;
        }
                
        public static function getConn(){
            return self::Connect();
        }
        
        
    }