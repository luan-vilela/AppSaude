<!-- Luan Vilela - 201619040036
	 Lucas de Brito de Andrade - 201519020600
	 -->

<?php
session_start();
ini_set('error_reporting', E_ALL);
ini_set('display_errors', 1);

require ('Controller/Controle.php');
include_once 'Model/Conn.php';
include_once 'Model/CreateData.php';

$data = new CreateData();

$data->createDataBase();

if(!isset($controller)) {


    $controller = new Controle();
} else {
    $controller->handleRequest();
}
