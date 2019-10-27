<?php
	$f = fopen('volley-header-header.txt', 'w');
	fwrite($f, var_export(getallheaders(), true));
	fwrite($f, var_export($_POST, true));
    fclose($f);
    
    include_once './Model/Conn.php';
    include_once './Model/CreateData.php';
    include_once './Model/crudPDO.php';
    
    include_once './Model/DataServer.php';
    include_once './Model/Profile.php';
    
    $db = new crudPDO();
    
   $idRegistro = "#".strval(gerarID());

    if(preg_match('/^(123){1}$/', $_POST['key'])){
        $resposta = $db->findStorageEmail('profile', $_POST['email']);
        // Recupera usuário
        if(gettype($resposta) == 'array'){
            // caso email já tenha sido cadastrado envia dados
            $user = new Profile($idRegistro, $_POST['nome'], $_POST['email'],$_POST['sexo'],$_POST['dataNascimento'],$_POST['telefone'],$_POST['fotoCaminho'],$_POST['gestante'],$db->getPosicaoBanco('data'));
            $db->updateStorageProfile('profile', $user->arrayObject());

        }
        // Cadastra novo usuário
        else{
            
            //Testa se já existe registro
            while($db->findStorageRegistro('profile', $idRegistro)){
                   $idRegistro = "#".strval(gerarID());
            }
            
            date_default_timezone_set('America/Sao_Paulo');
            $data = date('Y-m-d');
            $minhaData = new DataServer("UFMS", $data);
            $db->setStorageData('data', $minhaData);

            //__construct($idRegistro, $nome, $email, $sexo, $data_nasc, $telefone, $fotoCaminho, $gestante, $idDataCriacao)
            $user = new Profile($idRegistro, $_POST['nome'], $_POST['email'],"","","",$_POST['fotoCaminho'],"",$db->getPosicaoBanco('data'));
            $db->setStorageProfileInicial('profile', $user);

            
        }
        
            $resposta = $db->findStorageEmail('profile', $_POST['email']);
            echo json_encode($resposta);
	die;
    }
    
    echo 'Ocorreu algum erro no servidor!';
       
    
    function gerarID(){
         return mt_rand(1001, 9999);
    }

    