<?php
class Contato {
    protected $id;
    protected $nome;
    protected $email;

//    public function __construct($novoNome, $novoEmail) {
//        $this->nome = $novoNome;
//        $this->email = $novoEmail;
//    }

    public function __construct($novoNome, $novoEmail, $id) {
        $this->id = $id;
        $this->nome = $novoNome;
        $this->email = $novoEmail;
    }

    public function setId($id) {
        $this->id = $id;
    }

    public function setNome($nome) {
        $this->nome = $nome;
    }

    public function setEmail($email) {
        $this->email = $email;
    }

    public function getId() {
        return $this->id;
    }

    public function getNome() {
        return $this->nome;
    }

    public function getEmail() {
        return $this->email;
    }

}