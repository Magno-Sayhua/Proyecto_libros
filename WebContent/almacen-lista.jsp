<%@page import="entidad.Almacen"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/font-awesome.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<link rel="stylesheet" href="css/estilos.css">


<title>Almacen-lista</title>
</head>
<body class="body-claro">

<%
	List<Almacen> da = (List<Almacen>) request.getAttribute("data");
%>

<%-- MUESTRA LA ALERTA DE ERROR SI EXISTE EL ATRIBUTO --%>
		
		<% String msjError = (String) request.getAttribute("mensajeError");
			if (msjError != null){
		%>		
			<div class="alert alert-danger alert-dismissible fade show my-3" role="alert">
				<strong><i class="fa fa-exclamation-triangle"></i> ¡Aviso!</strong> <%= msjError %>
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<% }%>
		
<div class="container my-4">
<h1 align="center">LISTADO DE ALMACÉN</h1>
<p align="center">
<a href="almacen-registra.jsp" class="btn btn-secondary">+ Nuevo Libro</a>
<br>


<form action="ServletAlmacen?tipo=buscarcod" id="frmbuscarcod" method="post" class="row g-4">
<div class="col-auto">
<input type="text" name="cod1" class="form-control" required placeholder="Código de Libro">
</div>
<div class="d-inline-flex align-items-center">
<input type="submit" value="Consultar" class="btn btn-dark">
<img title="Consultar" src="img/search.png" style="margin-left: 5px;">
</div>
</form>

<br>

<div class="d-flex justify-content-between align-items-center w-100">
<form action="ServletAlmacen?tipo=listar" id="frmlistar" method="post">
<button class="btn btn-info d-inline-flex align-items-center">
Mostrar Lista <img title="Inicio" src="img/eye24.png" style="margin-left: 5px;"></button>
</form>
<form action="menu.jsp" id="frmlistar" method="post">
<button class="btn btn-danger d-inline-flex align-items-center">
<img title="Inicio" src="img/home30.png">Inicio</button>
</form>
</div>

<br>

<table border="2" align="center" class="table table-striped">
<thead>
<tr>
<th scope="col">CÓDIGO</th>
<th scope="col">TÍTULO</th>
<th scope="col">AUTOR</th>
<th scope="col">FECHA DE INGRESO</th>
<th scope="col">STOCK</th>
<th scope="col">CATEGORÍA</th>
<th colspan="2" scope="col">ACCIONES</th>
</tr>
</thead>
<tbody>
<%
	if (da != null) {
		for (Almacen p : da) {
%>
<tr>
<td scope="row"><%=p.getCod_almacen()%></td>
<td><%=p.getTitulo()%></td>
<td><%=p.getAutor()%></td>
<td><%=p.getFecha_ingreso()%></td>
<td><%=p.getStock()%></td>
<td><%=p.getCategoria()%></td>

<td colspan="2" align="center">
<a href="ServletAlmacen?tipo=buscar&cod=<%=p.getCod_almacen()%>">
<img title="Editar" src="img/edit.png"></a>
<a href="ServletAlmacen?tipo=eliminar&cod=<%=p.getCod_almacen()%>">
<img title="Eliminar" src="img/delete.png"></a>
</td>
</tr>
<%			
		}
	}
%>

</tbody>
</table>
</div>
</body>
</html>