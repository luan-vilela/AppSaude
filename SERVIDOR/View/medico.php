<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Cadastrar Contato</title>
    <link rel="stylesheet" type="text/css" href="View/css/style.css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
        <script type="text/javascript">
        window.onload = function(){
            atualizaRelogio();
        }
    </script>

</head>
<body>
    <div class="formulario">
        <div class="conteudo">
            <form method="post" action="?function=cadastrarMedico" class="w3-container w3-card-4 w3-light-grey w3-text-blue w3-margin">
                <h2 class="w3-center">Cadastrar Médico</h2>
                <input  class="w3-input w3-border" type="text" name="idRegistro" placeholder="id Paciente" />
                <input  class="w3-input w3-border" type="text" name="nome" placeholder="Nome Médico" />
                <input  class="w3-input w3-border" type="text" name="especializacao" placeholder="Especialização" />
                <input  class="w3-input w3-border" type="text" name="exames" placeholder="Exame1, Exame2, etc..." />
                <input  class="w3-input w3-border" type="text" name="observacao" placeholder="Obeservações" />
                <input  class="w3-input w3-border" type="date" name="data" placeholder="Data" />
                <input  class="w3-input w3-border" type="hidden" id="hora" name="hora" />
                <input class="w3-button w3-block w3-section w3-blue w3-ripple w3-padding" type="submit" value="Enviar"/>
            </form>
        </div>
    </div>
</body>
<script>
        function atualizaRelogio(){ 
			var momentoAtual = new Date();
			
			var vhora = momentoAtual.getHours();
			var vminuto = momentoAtual.getMinutes();
			var vsegundo = momentoAtual.getSeconds();
			
			if (vhora < 10){ vhora = "0" + vhora;}
			if (vminuto < 10){ vminuto = "0" + vminuto;}
 
			horaFormat = vhora + " : " + vminuto;

                horaFormat = vhora + ":" + vminuto;
                
                document.querySelector("[name='hora']").value = horaFormat;

        }
</script>

</html>