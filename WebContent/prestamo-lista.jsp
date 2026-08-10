<%@page import="entidad.Prestamo"%>
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


<title>Prestamo-lista</title>
</head>
<body class="body-claro">

<%
	List<Prestamo> da = (List<Prestamo>) request.getAttribute("data");
%> 
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
		<%}%>
<div class="container my-4">
<h2 align="center">LISTADO DE PRÉSTAMO</h2>
<p align="center">
<a href="prestamo-registra.jsp" class="btn btn-secondary">+ Nuevo Préstamo</a>
<br>


<form action="ServletPrestamo?tipo=buscarcod" id="frmbuscarcod" method="post" class="row g-4">
<div class="col-auto">
<input type="text" name="cod1" class="form-control" required placeholder="Código de Préstamo">
</div>
<div class="d-inline-flex align-items-center">
<input type="submit" value="Consultar" class="btn btn-dark">
<img title="Consultar" src="img/search.png" style="margin-left: 5px;">
</div>
</form>

<br>

<div class="d-flex justify-content-between align-items-center w-100">
<form action="ServletPrestamo?tipo=listar" id="frmlistar" method="post">
<button class="btn btn-info d-inline-flex align-items-center">
Mostrar Lista <img title="Inicio" src="img/eye24.png" style="margin-left: 5px;"></button>
</form>
<form action="menu.jsp" id="frmregresar" method="post">
<button class="btn btn-danger d-inline-flex align-items-center">
<img title="Inicio" src="img/home30.png">Inicio</button>
</form>
</div>

<br>




<table border="2" align="center" class="table table-striped">
<thead>

<tr>
<th scope="col">CÓDIGO DE PRÉSTAMO</th>
<th scope="col">CÓDIGO DEL LIBRO</th>
<th scope="col">TÍTULO DEL LIBRO</th>
<th scope="col">NOMBRE DE USUARIO</th>
<th scope="col">APELLIDO DE USUARIO</th>
<th scope="col">FECHA DE PRÉSTAMO</th>
<th scope="col">FECHA DE DEVOLUCIÓN</th>
<th scope="col">ESTADO</th>
<th colspan="2" scope="col">ACCIONES</th>
</tr>
</thead>
<tbody>

<%
	if (da != null) {
		for (Prestamo p : da) {
%>

<tr>
<td scope="row"><%=p.getCod_prestamo()%></td>
<td><%=p.getCod_libro()%></td>
<td><%=p.getLibro()%></td> <!-- título de libro -->
<td><%=p.getNombre()%></td>
<td><%=p.getApellido()%></td>
<td><%=p.getFecha_prestamo()%></td>
<td><%=p.getFecha_devolucion()%></td>
<td><%=p.getEstado()%></td>

<td colspan="2" align="center">
<a href="ServletPrestamo?tipo=buscar&cod=<%=p.getCod_prestamo()%>">
<img title="Editar" src="img/edit.png"></a>
<a href="ServletPrestamo?tipo=eliminar&cod=<%=p.getCod_prestamo()%>">
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