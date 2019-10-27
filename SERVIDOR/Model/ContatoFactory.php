<?php
    include_once './Model/Conn.php';
    include_once 'CreateData.php';
    include_once 'Contato.php';
    include_once 'crudPDO.php';

class ContatoFactory
{
    private $pdo;
    private $tabela;

    public function __construct (){
        $this->pdo = new crudPDO();
    }
    
    public function cadastrarContato(string $nome, string $email){
        $contato = new Contato($nome, $email, '');

        $result = $this->pdo->setStorage($this->tabela, $contato);
        
        return $result;
    }
    
    public function listarContatos(){
        $result = $this->pdo->getStorage($this->tabela);
        
        return $result;
    }
    
    public function buscarContato(int $id){
        $result = $this->pdo->findStorage($this->tabela, $id);

        $contato = new Contato($result['nome'], $result['email'], $result['id']);

        return $contato;
    }

    public function alterarContato(array $array){
        $this->pdo->updateStorage($this->tabela, $array);
    }

    public function deletarContato(int $id) {
        $result = $this->pdo->deleteStorage($this->tabela, $id);

        return $result;
    }
}