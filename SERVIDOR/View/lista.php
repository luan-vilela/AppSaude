<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Inicio</title>
    <link rel="stylesheet" type="text/css" href="View/css/style.css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">


</head>
<body>
	<div class="lista">
		<table class="w3-table w3-striped">
                    <thead>
                    <tr><th colspan="3">Dados do paciente.</th></tr>
                    </thead>    
                                      
                    <tr class="w3-blue">
		        <th>id</th>
		        <th>Nome</th>
		        <th>Sexo</th>
                <th></th>
                <th></th>
		    </tr>

		<?php
		foreach($dados as $dado) :?>
		<tr>
		    <td><?=$dado['id']?></td>
                    <td><?=$dado['nome']?></td>
		    <td><?= ($dado['sexo'] == 0) ? "Feminino" : "Masculino" ?></td>
		</tr>
		<?php endforeach; ?>
                </table><br>


       <div class="lista">
		<table class="w3-table w3-striped">
                    <thead>
                    <tr><th colspan="3">Consultas médicas.</th></tr>
                    </thead>    
		    <tr class="w3-blue">
		        <th>id</th>
		        <th>Médico</th>
		        <th>Especialidade</th>
		        <th>examesPedidos</th>
                        <th>Observação</th>
                        <th>Paciente Gestante</th>
                <th></th>
                <th></th>
		    </tr>

		<?php
		foreach($json["medico"] as $dado) :?>
		<tr>
		    <td><?=$dado['id']?></td>
                    <td><?=$dado['nome']?></td>
                    <td><?=$dado['especialidade']?></td>
                    <td><?=$dado['examesPedidos']?></td>
                    <td><?=$dado['observacao']?></td>
                    <td><?= ($dado['gestante'] == 0) ? "Não" : "Sim" ?></td>

		</tr>
		<?php endforeach; ?>
                </table><br>
	</div>
        
        <div class="lista">
		<table class="w3-table w3-striped">
                    <thead>
                    <tr><th colspan="3">Laudos médicos.</th></tr>
                    </thead>    
		    <tr class="w3-blue">
		        <th>id</th>
		        <th>Título</th>
		        <th>Descrição</th>
                        <th>Paciente Gestante</th>
                <th></th>
                <th></th>
		    </tr>

		<?php
		foreach($json["laudo"] as $dado) :?>
		<tr>
		    <td><?=$dado['id']?></td>
                    <td><?=$dado['nome']?></td>
                    <td><?=$dado['descrição']?></td>
                    <td><?= ($dado['gestante'] == 0) ? "Não" : "Sim" ?></td>

		</tr>
		<?php endforeach; ?>
                </table><br>
	</div>
        <?php var_dump($json)?>
        <div class="lista">
		<table class="w3-table w3-striped">
                    <thead>
                    <tr><th colspan="3">Eventos.</th></tr>
                    </thead> 
		    <tr class="w3-blue">
		        <th>id</th>
		        <th>Descrição</th>

                <th></th>
                <th></th>
		    </tr>

		<?php
		foreach($json["evento"] as $dado) :?>
		<tr>
		    <td><?=$dado['id']?></td>
                    <td><?=$dado['descricao']?></td>

		</tr>
		<?php endforeach; ?>
                </table><br>
	</div>
</body>
</html>