<%@page import="entidad.Almacen"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/font-awesome.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<link rel="stylesheet" href="css/estilos.css">


<title>ACTUALIZAR ALMACÉN</title>
</head>
<body class="body-oscuro">
	<%
		Almacen p = (Almacen) request.getAttribute("registro");
		if (p == null) {
			p = new Almacen();
		}
	%>

	<h1 align="center">Actualizar Datos</h1>
	
	<%-- MUESTRA LA ALERTA DE ERROR SI EXISTE EL ATRIBUTO --%>
	
	<%
		String msjErrorA = (String) request.getAttribute("mensajeErrorA");
		if (msjErrorA != null){
	%>		
		<div class="alert alert-danger alert-dismissible fade show my-3" role="alert">
				<strong><i class="fa fa-exclamation-triangle"></i> ¡Aviso!</strong> <%= msjErrorA %>
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
	<%}%>
	
	
	<div class="container">
	
	  <div class="text-center mb-3">
        <form action="menu.jsp" id="frmregresar" method="post" style="display: inline-block;">
            <button class="btn btn-secondary d-inline-flex align-items-center">
                <img title="Inicio" src="img/home30.png" style="margin-right: 5px">Inicio
            </button>
        </form>
    </div>
	
		<form action="ServletAlmacen?tipo=actualizar" name="frmactualizar"
			method="post">
			<input type="hidden" name="txt_cod" value="<%=p.getCod_almacen()%>">
			<table border="1" align="center" class="table">
				<tr>
					<td>Títulos</td>
					<td><input type="text" name="txt_tit" value="<%= p.getTitulo() != null ? p.getTitulo() : "" %>" class="form-control" required></td>
				</tr>
				<tr>
					<td>Autores</td>
					<td><input type="text" name="txt_aut" value="<%= p.getAutor() != null ? p.getAutor() : "" %>" class="form-control" required></td>
				</tr>
				<tr>
					<td>Fecha de Ingreso</td>
					<td><input type="date" name="txt_fec" value="<%= p.getFecha_ingreso() != null ? p.getFecha_ingreso(): "" %>" class="form-control" required></td>
				</tr>
				<tr>
					<td>Stock</td>
					<td><input type="text" name="txt_sto" value="<%= p.getStock()%>" class="form-control" required></td>
				</tr>
				<!-- Todo lo que se ha hecho en categorías es un tipo de operación para que cuando se quiera actualizar, no se coloque automáticamente el "Seleccionar",
				sino que se quede en la categoría que estuvo puesto -->
				<tr>
					<td>Categorías</td>
					<td>
					<select type="text" id="demo" name="txt_cat" class="form-control" required>
					
						<option value="">Seleccionar</option>

						<option value="Novela Contemporánea" <%= "Novela Contemporánea".equals(p.getCategoria()) ? "selected" : "" %>>Novela Contemporánea</option>

						<option value="Poesía y Lírica"<%= "Poesía y Lírica".equals(p.getCategoria()) ? "selected" : "" %>>Poesía y Lírica</option>

						<option value="Relato Histórico"<%= "Relato Histórico".equals(p.getCategoria()) ? "selected" : "" %>>Relato Histórico</option>

						<option value="Ensayo y Crítica"<%= "Ensayo y Crítica".equals(p.getCategoria()) ? "selected" : "" %>>Ensayo y Crítica</option>

						<option value="Literatura Indigenista"<%= "Literatura Indigenista".equals(p.getCategoria()) ? "selected" : "" %>>Literatura Indigenista</option>
					</select>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit"
						value="Actualizar" class="btn btn-success"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>