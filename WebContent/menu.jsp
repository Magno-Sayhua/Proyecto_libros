<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">


<link rel="stylesheet" href="css/estilos.css">


<title>MENÚ</title>
</head>
<body class="body-oscuro">
	<div class="container" style="">


	
    <h1 class ="h1">BIENVENIDO</h1>
    <h2>Por favor, seleccione una opción</h3>
    <br>

<!-- ALMACÉN -->
  
    <a class="menu-btn" href="ServletAlmacen?tipo=listar">Listar Libros</a>
    <a class="menu-btn" href="almacen-registra.jsp">Registrar Libros</a>
	</div>
	<br> <br>


<!-- PRÉSTAMO -->
		<div>
			<a class="menu-btn-2" href="ServletPrestamo?tipo=listar">Listar prestamos</a>
		</div>
		<div>
			<a class="menu-btn-2" href="prestamo-registra.jsp">Registrar Prestamos</a>
		</div>
	
	

</body>
</html>