<?php

class crudPDO extends CreateData{
    /*
     *             "CREATE TABLE IF NOT EXISTS laudo (" .
            "id INTEGER PRIMARY KEY, " .
            "nome TEXT, " .
            "descricao TEXT, " .
            "gestante BOOLEAN, " .
            "idRegistro TEXT," .
            "idData INTEGER, " .
            "FOREIGN KEY(idData) REFERENCES data(id) " .
     */
    public function setStorageLaudo(string $table, Laudo $value){
        parent::createDataBase();

        try{
            $qrCreate = 'INSERT INTO ' .$table. ' (nome, descricao, gestante, idRegistro, idData) VALUES (?, ?, ?, ?, ?)';
            $create = parent::getConn()->prepare($qrCreate);
            
            $create->bindValue(1, $value->getNome(), PDO::PARAM_STR); 
            $create->bindValue(2, $value->getDescricao(), PDO::PARAM_STR);
            $create->bindValue(3, $value->getGestante(), PDO::PARAM_STR);
            $create->bindValue(4, $value->getIdRegistro(), PDO::PARAM_STR);
            $create->bindValue(5, $value->getIdData(), PDO::PARAM_STR);


            return $create->execute();
            

        } catch (PDOException $e){
           
            if($e->getCode() == 23000) 
                return false;
            
            echo 'Não foi possivel cadastrar data'.$e->getMessage();
            
            die;
        }
    }
    
    public function setStorageEvento(string $table, Evento $value){
        parent::createDataBase();

        try{
            $qrCreate = 'INSERT INTO ' .$table. ' (descricao, idData, idRegistro) VALUES (?, ?, ?)';
            $create = parent::getConn()->prepare($qrCreate);
            
            $create->bindValue(1, $value->getDescricao(), PDO::PARAM_STR); 
            $create->bindValue(2, $value->getIdData(), PDO::PARAM_STR);
            $create->bindValue(3, $value->getIdRegistro(), PDO::PARAM_STR);


            return $create->execute();
            

        } catch (PDOException $e){
           
            if($e->getCode() == 23000) 
                return false;
            
            echo 'Não foi possivel cadastrar data'.$e->getMessage();
            
            die;
        }
    }
    
        public function setStorageData(string $table, DataServer $value){
        parent::createDataBase();

        try{
            $qrCreate = 'INSERT INTO ' .$table. ' (data, local) VALUES (?, ?)';
            $create = parent::getConn()->prepare($qrCreate);
            
            $create->bindValue(1, $value->getData(), PDO::PARAM_STR); 
            $create->bindValue(2, $value->getLocal(), PDO::PARAM_STR);


            return $create->execute();
            

        } catch (PDOException $e){
           
            if($e->getCode() == 23000) 
                return false;
            
            echo 'Não foi possivel cadastrar data'.$e->getMessage();
            
            die;
        }
    }

   
    public function setStorageMedico(string $table, Medico $value){
        parent::createDataBase();

        try{
            $qrCreate = 'INSERT INTO ' .$table. ' (nome, especialidade, examesPedidos, observacao, gestante, idRegistro, idData) VALUES (?, ?, ?, ?, ?, ?, ?)';
            $create = parent::getConn()->prepare($qrCreate);
               
            $create->bindValue(1, $value->getNome(), PDO::PARAM_STR);
            $create->bindValue(2, $value->getEspecialidade(), PDO::PARAM_STR);
            $create->bindValue(3, $value->getExames(), PDO::PARAM_STR);
            $create->bindValue(4, $value->getObservacao(), PDO::PARAM_STR);
            $create->bindValue(5, $value->getGestante(), PDO::PARAM_STR);
            $create->bindValue(6, $value->getIdRegistro(), PDO::PARAM_STR);
            $create->bindValue(7, $value->getIdData(), PDO::PARAM_STR);

            return $create->execute();
            

        } catch (PDOException $e){
           
            if($e->getCode() == 23000) 
                return false;
            
            echo 'Não cadastrou medico'.$e->getMessage();
            
            die;
        }
    }
    
    public function setStorageProfileInicial(string $table, Profile $value){
     parent::createDataBase();

        try{
            //construct($idRegistro, $nome, $email, $sexo, $data_nasc, $telefone, $fotoCaminho, $gestante, $idDataCriacao)
            $qrCreate = 'INSERT INTO ' .$table. ' (nome, email, fotoCaminho, idDate, sexo, dataNascimento, telefone, gestante, idRegistro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)';
            $create = parent::getConn()->prepare($qrCreate);
               
            $create->bindValue(1, $value->getNome(), PDO::PARAM_STR);
            $create->bindValue(2, $value->getEmail(), PDO::PARAM_STR);
            $create->bindValue(3, $value->getFotoCaminho(), PDO::PARAM_STR);
            $create->bindValue(4, $value->getIdDataCriacao(), PDO::PARAM_STR);
            $create->bindValue(5, $value->getSexo(), PDO::PARAM_STR);
            $create->bindValue(6, $value->getData_nasc(), PDO::PARAM_STR);
            $create->bindValue(7, $value->getTelefone(), PDO::PARAM_STR);
            $create->bindValue(8, $value->getGestante(), PDO::PARAM_STR);
            $create->bindValue(9, $value->getIdRegistro(), PDO::PARAM_STR);

            return $create->execute();
            

        } catch (PDOException $e){
           
            if($e->getCode() == 23000) 
                return false;
            
            echo 'Não cadastrou Usuário'.$e->getMessage();
            
            die;
        }
    }

    
    public function getPosicaoBanco(string $table){
        $query = parent::getConn()->query('SELECT count(*) FROM '.$table);

        $result = $query->fetchAll(PDO::FETCH_ASSOC);
        $inteiro = (int) $result[0]['count(*)'];
        return $inteiro;
    }
    public function getDadosBanco(string $table){
        $query = parent::getConn()->query('SELECT * FROM '.$table);

        $result = $query->fetchAll(PDO::FETCH_ASSOC);
        return (int) $result['count(*)'];
    }

    public function findStorageRegistro(string $table, string $idRegistro){
        $query = parent::getConn()->query('SELECT * FROM '.$table. ' WHERE idRegistro = "'. $idRegistro.'"');
        $result = $query->fetchAll(PDO::FETCH_ASSOC);
        return $result;
    }
    public function findStorageRegistroJoin(string $table, string $table2, string $idRegistro){
        $query = parent::getConn()->query('SELECT m.*, d.local, d.data FROM '.$table. ' as m  JOIN ' .$table2. ' as d WHERE m.id = d.id and idRegistro = "'. $idRegistro.'"');
        $result = $query->fetchAll(PDO::FETCH_ASSOC);
        return $result;
    }
    
    public function findStorageEmail(string $table, string $email){
        $query = parent::getConn()->query('SELECT * FROM '.$table. ' WHERE email = "'. $email.'"');

        $result = $query->fetch(PDO::FETCH_ASSOC);
        return $result;
    }
    
    public function findStorageID(string $table,int $id){
        $query = parent::getConn()->query('SELECT * FROM '.$table. ' WHERE id = "'. $id . '"');

        $result = $query->fetch(PDO::FETCH_ASSOC);
        return $result;
    }

    public function updateStorageProfile(string $table, array $usuario){
        $query = parent::getConn()->prepare('UPDATE '.$table.' SET
            nome = :nome, 
            sexo = :sexo, 
            dataNascimento = :dataNascimento, 
            telefone = :telefone, 
            fotoCaminho = :fotoCaminho,
            gestante = :gestante
            WHERE email = :email');

        $query->execute(array(
            ':nome' => $usuario['nome'],
            ':email' => $usuario['email'],
            ':sexo' => $usuario['sexo'],
            ':telefone' => $usuario['telefone'],
            ':dataNascimento' => $usuario['dataNascimento'],
            ':fotoCaminho' => $usuario['fotoCaminho'],
            ':gestante' => $usuario['gestante']
        ));
    }

    public function deleteStorage(string $table,int $id){
        $query = parent::getConn()->query('DELETE FROM '.$table. ' WHERE id = '. $id);

        $result = $query->execute();
        return $result;
    }
}