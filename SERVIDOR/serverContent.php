<?php

    include_once './Model/Conn.php';
    include_once './Model/CreateData.php';
    include_once './Model/crudPDO.php';
    
    include_once './Model/DataServer.php';
    include_once './Model/Medico.php';
    
    $db = new crudPDO();
    
    if(preg_match('/^(123){1}$/', $_POST['key'])){
        if(isset($_POST['idRegistro'])){
            $idRegistro = $_POST['idRegistro'];
            $medico = $db->findStorageRegistroJoin('medico','data', $idRegistro);
            
            $evento = $db->findStorageRegistroJoin('evento','data', $idRegistro);
            
            $laudo = $db->findStorageRegistroJoin('laudo','data', $idRegistro);
            
            $json = array(
                    "medico" => $medico,
                    "laudo" => $laudo,
                    "evento" => $evento
                    );
            echo json_encode($json);
           
        }
    }

    