<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/font-awesome.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<link rel="stylesheet" href="css/estilos.css">


<!-- pa mi fondo :'V, no lo cambien p TwT se veo feo todo blanco UnU-->
<style> body { background-color: #a4ac86; } </style>

<title>Registrar Préstamo</title>
</head>
<body class="body-oscuro">
<br>

	<h1 class="h1">Registrar Préstamo</h1>
	
		<form action="menu.jsp" id="frmlistar" method="post">
<button class="btn btn-secondary d-inline-flex align-items-center">
<img title="Inicio" src="img/home30.png" style="margin-right: 5px">Inicio</button>
</form>
	
	<div class="container" align="left">
	<br>
		<h5 style ="font-weight: bold;  font-family:SimSun">Por favor, ingrese los datos correctamente</h5>
		
		<%-- MUESTRA LA ALERTA DE ERROR SI EXISTE EL ATRIBUTO --%>
		
		<%
			String msjErrorP = (String) request.getAttribute("mensajeErrorP");
			if (msjErrorP != null) {
		%>		
			<div class="alert alert-danger alert-dismissible fade show my-3" role="alert">
				<strong><i class="fa fa-exclamation-triangle"></i> ¡Aviso!</strong> <%= msjErrorP %>
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<% }%>
			
		<form action="ServletPrestamo?tipo=registrar" id="frmregistro" method="post">
			<div class="container text-center"  align="left" width="75%">
				<table  class="table"    > <!--acá va la wbda, ayuda  -->
	
					<!-- COD DE LIBRO -->
					<tr>
						<td>Código de Libro</td>
						<td><input type="text" name="txt_cod_libro" class="form-control" required placeholder="Ej. 7"></td>
					</tr>

					<!-- NOMBRE -->
					<tr>
						<td>Nombre de Usuario</td>
						<td><input type="text" name="txt_nom" class="form-control" required></td>
					</tr>

					<!-- APELLIDO -->
					<tr>
						<td>Apellido de Usuario</td>
						<td><input type="text" name="txt_ape" class="form-control" required></td>
					</tr>

					<!-- FECHA DE PRESTAMO -->
					<tr>
						<td>Fecha de Préstamo</td>
						<td><input type="date" name="txt_fec_p" class="form-control" required placeholder="Ej. 2007-01-10"></td>
					</tr>

					<!-- FECHA DE DEVOLUCIÓN -->
					<tr>
						<td>Fecha de Devolución</td>
						<td><input type="date" name="txt_fec_d" class="form-control" required placeholder="Ej. 2007-02-15"></td>
					</tr>

					<!-- ESTADO  -->
					<tr>
						<td>Estado</td>
						<td><select class="form-control" type="text" id="demo" name="txt_estado" required onChange="combo(this,demo)">
								<option value="">Seleccionar</option>
								<option value="Activo">Activo</option>
								<option value="Devuelto">Devuelto</option>
								<option value="Vencido">Vencido</option>
						</select></td>
					</tr>

					<tr>
						<td colspan="2" align="right"><input type="submit"
							value="Registrar" class="btn btn-danger" style = "font-size:20px">
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	
	<!-- Scripts para habilitar el cierre de la alerta de Bootstrap -->
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
</body>
</html>