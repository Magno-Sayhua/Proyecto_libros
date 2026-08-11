<%@page import="entidad.Prestamo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/font-awesome.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<link rel="stylesheet" href="css/estilos.css">


<title>ACTUALIZAR PRÉSTAMO</title>
</head>
<body class="body-oscuro">

	<%
		Prestamo p = (Prestamo) request.getAttribute("registro");
		if (p == null){
			p = new Prestamo();
		}
	%>
	
	<%-- Alerta de error --%>
<% 
    String msjErrorA = (String) request.getAttribute("mensajeErrorA");
    if (msjErrorA != null) { 
%>
    <div class="alert alert-danger alert-dismissible fade show my-3" role="alert">
        <strong>¡Aviso!</strong> <%= msjErrorA %>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
<% } %>

<h1 align="center">Actualizar Datos</h1>
	<br>
	<div class="container">
	
	<div class="text-center mb-3">
        <form action="menu.jsp" id="frmregresar" method="post" style="display: inline-block;">
            <button class="btn btn-secondary d-inline-flex align-items-center">
                <img title="Inicio" src="img/home30.png" style="margin-right: 5px">Inicio
            </button>
        </form>
    </div>
		<form action="ServletPrestamo?tipo=actualizar" name="frmactualizar"
			method="post">
			<input type="hidden" name="txt_cod" value="<%=p.getCod_prestamo()%>">
			<table border="1" align="center" class="table">
				
				<!-- COD DE LIBRO -->
					<tr>
						<td>Código de Libro</td>
						<td><input type="text" name="txt_cod_libro" value="<%= p.getCod_libro() != 0 ? p.getCod_libro() : "" %>" class="form-control" required></td>
					</tr>

					<!-- NOMBRE -->
					<tr>
						<td>Nombre de Usuario</td>
						<td><input type="text" name="txt_nom" value="<%=p.getNombre() != null ? p.getNombre() : ""%>" class="form-control" required></td>
					</tr>

					<!-- APELLIDO -->
					<tr>
						<td>Apellido de Usuario</td>
						<td><input type="text" name="txt_ape" value="<%=p.getApellido() != null ? p.getApellido() : ""%>" class="form-control" required></td>
					</tr>

					<!-- FECHA DE PRESTAMO -->
					<tr>
						<td>Fecha de Préstamo</td>
						<td><input type="date" name="txt_fec_p" value="<%=p.getFecha_prestamo() != null ? p.getFecha_prestamo() : ""%>" class="form-control" required></td>
					</tr>

					<!-- FECHA DE DEVOLUCIÓN -->
					<tr>
						<td>Fecha de Devolución</td>
						<td><input type="date" name="txt_fec_d" value="<%=p.getFecha_devolucion() != null ? p.getFecha_devolucion() : ""%>" class="form-control" required></td>
					</tr>


				<!--ESTADOS -->
				<tr>
					<td>Estado</td>
					<td><select name="txt_estado" class="form-control">
							<option value="" <%= p.getEstado() == null ? "selected" : "" %>>Seleccionar</option>
							<option value="Activo"
								<%= "Activo".equals(p.getEstado()) ? "selected" : "" %>>
								Activo</option>
							<option value="Devuelto"
								<%= "Devuelto".equals(p.getEstado()) ? "selected" : "" %>>
								Devuelto</option>
							<option value="Vencido"
								<%= "Vencido".equals(p.getEstado()) ? "selected" : "" %>>
								Vencido</option>
					</select></td>
					</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit"
						value="Actualizar" class="btn btn-success"></td>
				</tr>
					</table>
		</form>
					</body>
</html>