<?php
    include_once './Model/Conn.php';
    include_once './Model/CreateData.php';
    include_once './Model/crudPDO.php';
    include_once './Model/ContatoFactory.php';
    
    include_once './Model/DataServer.php';
    include_once './Model/Medico.php';
    include_once './Model/Evento.php';
    include_once './Model/Laudo.php';
    
    
    
class Controle{
    private $db;

    public function __construct (){
        $this->db = new crudPDO();
        $this->handleRequest ();
        
    }

    public function handleRequest (){
        if (isset($_GET['function'])) {
            $f = $_GET['function'];
        } else {
            $f = "default";
        }

        switch ($f) {
            case 'listaMedico':
                $this->listaMedico();
                break;
            case 'cadastrarMedico':
                $this->cadastrarMedico();
                break;
            case 'listaLaudo':
                $this->listaLaudo();
                break;
            case 'cadastrarLaudo':
                $this->cadastrarLaudo();
                break;
            case 'listaEvento':
                $this->listaEvento();
                break;
            case 'cadastrarEvento':
                $this->cadastrarEvento();
                break;
            case 'listaPaciente':
                $this->verTodosDados();
                break;
            case 'pesquisaPaciente':
                $this->pesquisaPaciente();
                break;
            default:
                $this->indexAction();
                break;
        }
    }

    public function indexAction(){
        require('View/index.php');
    }

    public function listaMedico(){
        require('View/medico.php');
    }
    
    public function listaLaudo(){
        require('View/laudo.php');
    }
    
    public function listaEvento(){
        require('View/evento.php');
    }
    
    public function pesquisaPaciente(){
        require('View/paciente.php');
    }

    public function cadastrarMedico()
    {
        // ["idRegistro"] "" ["nome"] "" ["especializacao" "" ["exames"]" ["observacao" data"} 
        //($nome, $especialidade, $exames, $observacao, $gestante, $idRegistro, $idData) {

        $paciente = $this->db->findStorageRegistro('profile', $_POST['idRegistro']);
        $test = false;
        if(!empty($paciente[0]) && isset($paciente[0])){
            $data = new DataServer($_POST['data']." ".$_POST['hora'], "UFMS");
            $this->db->setStorageData('data', $data);
            $med = new Medico($_POST['nome'], $_POST['especializacao'], $_POST['exames'], $_POST['observacao'], $paciente[0]['gestante'], $_POST['idRegistro'], $this->db->getPosicaoBanco('data'));

            $test = $this->db->setStorageMedico('medico', $med);
        }
        require('View/mostra.php');
    }
    
    public function cadastrarEvento()
    {
        // ["idRegistro"] "" ["nome"] "" ["especializacao" "" ["exames"]" ["observacao" data"} 
        //($nome, $especialidade, $exames, $observacao, $gestante, $idRegistro, $idData) {
        $paciente = $this->db->findStorageRegistro('profile', $_POST['idRegistro']);
        $test = false;
        if(!empty($paciente[0]) && isset($paciente[0])){
            $data = new DataServer($_POST['data']." ".$_POST['hora'], "UFMS");
            $this->db->setStorageData('data', $data);
            $idData = $this->db->getPosicaoBanco('data');
            $evento = new Evento($_POST['descricao'], $idData, $_POST['idRegistro']);
            $test = $this->db->setStorageEvento('evento', $evento);
        }
        require('View/mostra.php');
    }

    public function cadastrarLaudo(){
        
        $paciente = $this->db->findStorageRegistro('profile', $_POST['idRegistro']);
        $test = false;
        if(!empty($paciente[0]) && isset($paciente[0])){
            $data = new DataServer($_POST['data']." ".$_POST['hora'], "UFMS");
            $this->db->setStorageData('data', $data);
            $idData = $this->db->getPosicaoBanco('data');
            //__construct($nome, $descricao, $idRegistro, $gestante, $idData) 
            $laudo = new Laudo($_POST['nome'], $_POST['descricao'], $idData, $_POST['idRegistro'],$paciente[0]['gestante'], $idData);
            $test = $this->db->setStorageLaudo('laudo', $laudo);
        }
        require('View/mostra.php'); 
    }
    
    
    
     public function verTodosDados(){
            
            $idRegistro = $_POST['idRegistro'];
            $dados = $this->db->findStorageRegistro('profile', $idRegistro);
            $medico = $this->db->findStorageRegistroJoin('medico','data', $idRegistro);
            
            $evento = $this->db->findStorageRegistroJoin('evento','data', $idRegistro);
            
            $laudo = $this->db->findStorageRegistroJoin('laudo','data', $idRegistro);
            
            $json = array(
                    "medico" => $medico,
                    "laudo" => $laudo,
                    "evento" => $evento
                    );
            
            require('View/lista.php');
     }
}