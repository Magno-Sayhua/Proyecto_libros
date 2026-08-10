package controlador;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Almacen;
import modelo.ModeloAlmacen;


@WebServlet("/ServletAlmacen")
public class ServletAlmacen extends HttpServlet {
	ModeloAlmacen m = new ModeloAlmacen();
	private static final long serialVersionUID = 1L;

	
    public ServletAlmacen() {
        // TODO Auto-generated constructor stub
    }

    //SERVICE
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo = request.getParameter("tipo");
		if (tipo.equals("listar"))
			listar(request, response);
		else if (tipo.equals("buscar"))
			buscar(request, response);
		else if (tipo.equals("registrar"))
			registrar(request, response);
		else if (tipo.equals("actualizar"))
			actualizar(request, response);
		else if (tipo.equals("eliminar"))
			eliminar(request, response);
		else if (tipo.equals("buscarcod"))
			buscarcod(request, response);
	}

	
	// BUSCARXCOD
	private void buscarcod(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String dato = request.getParameter("cod1");

	    // 1. Validar que no llegue vacío o solo espacios
	    if (dato == null || dato.trim().isEmpty()) {
	        request.setAttribute("mensajeError", "Por favor, ingrese un código de libro.");
	        request.getRequestDispatcher("almacen-lista.jsp").forward(request, response);
	        return;
	    }

	    try {
	        // 2. Convertir a entero
	        int codigo = Integer.parseInt(dato);

	        // 3. Consultar la base de datos
	        List<Almacen> info = new ModeloAlmacen().buscarAlmacenxcod(codigo);

	        // 4. Validar si la lista está vacía (Libro no encontrado)
	        if (info == null || info.isEmpty()) {
	            request.setAttribute("mensajeError", "El código de libro " + codigo + " no existe en la lista.");
	        } else {
	            // Si el libro existe, enviamos la lista encontrada
	            request.setAttribute("data", info);
	        }

	        request.getRequestDispatcher("almacen-lista.jsp").forward(request, response);

	    } catch (NumberFormatException e) {
	        // Se ejecuta si el usuario escribe letras en el código
	        request.setAttribute("mensajeError", "El código ingresado debe ser un número entero.");
	        request.getRequestDispatcher("almacen-lista.jsp").forward(request, response);
	    }
	}
	
	
	//ELIMINAR
	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dato = request.getParameter("cod");
		int codigo = Integer.parseInt(dato);
		m.eliminarAlmacen(codigo);
		request.getRequestDispatcher("ServletAlmacen?tipo=listar").forward(request, response);
	}
	
	
	//ACTUALIZAR
	private void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Almacen obj = new Almacen();
		String cod = request.getParameter("txt_cod");
		String tit = request.getParameter("txt_tit");
		String aut = request.getParameter("txt_aut");
		String fec = request.getParameter("txt_fec");
		String sto = request.getParameter("txt_sto");
		String cat = request.getParameter("txt_cat");
		
		obj.setCod_almacen(Integer.parseInt(cod));
		obj.setTitulo(tit);
		obj.setAutor(aut);
		obj.setFecha_ingreso(LocalDate.parse(fec));
		obj.setStock(Integer.parseInt(sto));
		obj.setCategoria(cat);
		int estado = m.actualizarAlmacen(obj);
		if (estado != -1)
			listar(request, response);
		else
			response.sendRedirect("error.html");		
	}
	
	
	// REGISTRAR
		private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String tit = request.getParameter("txt_tit");
			String aut = request.getParameter("txt_aut");
			String fec = request.getParameter("txt_fec");
			String sto = request.getParameter("txt_sto");
			String cat = request.getParameter("txt_cat");

			// 1. Validar que los campos requeridos no estén vacíos
			if (tit == null || tit.trim().isEmpty() ||
			    aut == null || aut.trim().isEmpty() ||
			    fec == null || fec.trim().isEmpty() ||
			    sto == null || sto.trim().isEmpty() ||
			    cat == null || cat.trim().isEmpty() || cat.equals("Seleccionar")) {

				request.setAttribute("mensajeError", "Por favor, complete todos los campos obligatorios.");
				request.getRequestDispatcher("almacen-registra.jsp").forward(request, response);
				return;
			}

			LocalDate fechaIngreso = null;
			int stockVal = 0;

			// 2. Validar formato de Fecha
			try {
				fechaIngreso = LocalDate.parse(fec);
			} catch (Exception e) {
				request.setAttribute("mensajeError", "La fecha ingresada es inválida. No se permiten letras (Formato: YYYY-MM-DD).");
				request.getRequestDispatcher("almacen-registra.jsp").forward(request, response);
				return;
			}

			// 3. Validar formato de Stock (solo números enteros)
			try {
				stockVal = Integer.parseInt(sto);
				if (stockVal < 0) {
					request.setAttribute("mensajeError", "El stock no puede ser un número negativo.");
					request.getRequestDispatcher("almacen-registra.jsp").forward(request, response);
					return;
				}
			} catch (NumberFormatException e) {
				request.setAttribute("mensajeError", "El campo Stock no permite letras ni caracteres especiales.");
				request.getRequestDispatcher("almacen-registra.jsp").forward(request, response);
				return;
			}

			// 4. Si todas las validaciones pasan, se guarda el objeto
			Almacen obj = new Almacen();
			obj.setTitulo(tit);
			obj.setAutor(aut);
			obj.setFecha_ingreso(fechaIngreso);
			obj.setStock(stockVal);
			obj.setCategoria(cat);

			int estado = m.registrarAlmacen(obj);
			if (estado != -1) {
				listar(request, response);
			} else {
				request.setAttribute("mensajeError", "Error de base de datos al intentar registrar el libro.");
				request.getRequestDispatcher("almacen-registra.jsp").forward(request, response);
			}
		}


	//BUSCAR
	private void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dato = request.getParameter("cod");
		int codigo = Integer.parseInt(dato);
		Almacen x = m.buscarAlmacen(codigo);
		request.setAttribute("registro", x);
		request.getRequestDispatcher("almacen-actualiza.jsp").forward(request, response);
	}

	
	//LISTAR
	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Almacen> info = new ModeloAlmacen().listar();
		request.setAttribute("data", info);
		request.getRequestDispatcher("almacen-lista.jsp").forward(request, response);
	}

	

}
