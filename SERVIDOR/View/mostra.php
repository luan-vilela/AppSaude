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
	<div class="formulario final">
		<?php
		if($test == false) : ?>
		    <a href="./" class="w3-button w3-block w3-section w3-blue w3-ripple w3-padding">Falha</a>
		<?php else :?>
		  <a href="./" class="w3-button w3-block w3-section w3-blue w3-ripple w3-padding">Sucesso</a>
		<?php endif;?>
	</div>
</body>
</html>
