<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/font-awesome.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<link rel="stylesheet" href="css/estilos.css">

<title>Registrar Datos</title>
</head>
<body class="body-oscuro">
	<h1 class="h1">Registrar Libros</h1>
	
	<form action="menu.jsp" id="frmlistar" method="post">
		<button class="btn btn-secondary d-inline-flex align-items-center">
			<img title="Inicio" src="img/home30.png" style="margin-right: 5px"> Inicio
		</button>
	</form>
	
	<div class="container mt-3" align="left">
		<h4 align="left">Por favor, ingrese los datos correctamente</h4>

		<%-- MUESTRA LA ALERTA DE ERROR SI EXISTE EL ATRIBUTO --%>
		<% 
			String msjError = (String) request.getAttribute("mensajeError");
			if (msjError != null) { 
		%>
			<div class="alert alert-danger alert-dismissible fade show my-3" role="alert">
				<strong><i class="fa fa-exclamation-triangle"></i> ¡Aviso!</strong> <%= msjError %>
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		<% } %>

		<form action="ServletAlmacen?tipo=registrar" id="frmregistro" method="post">
			<div align="left" width="75%">
				<table class="table">
					<tr>
						<td>Título</td>
						<td><input type="text" name="txt_tit" class="form-control" required placeholder="Ej. Los ríos profundos"></td>
					</tr>
					<tr>
						<td>Autor</td>
						<td><input type="text" name="txt_aut" class="form-control" required placeholder="Ej. José María Arguedas"></td>
					</tr>
					<tr>
						<td>Fecha de Ingreso</td>
						<td><input type="date" name="txt_fec" class="form-control" required placeholder="Ej. 2015-08-09"></td>
					</tr>
					<tr>
						<td>Stock</td>
						<td><input type="number" name="txt_sto" class="form-control" min="0" step="1" required placeholder="Ej. 10"></td>
					</tr>
					<tr>
						<td>Categoría</td>
						<td>
							<select class="form-control" name="txt_cat" required onChange="combo(this,demo)">
								<option value="">Seleccionar</option>
								<option value="Novela Contemporánea">Novela Contemporánea</option>
								<option value="Poesía y Lírica">Poesía y Lírica</option>
								<option value="Relato Histórico">Relato Histórico</option>
								<option value="Ensayo y Crítica">Ensayo y Crítica</option>
								<option value="Literatura Indigenista">Literatura Indigenista</option>
								<option value="Literatura Juvenil">Literatura Juvenil</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="right">
							<input type="submit" value="Registrar" class="btn btn-danger">
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