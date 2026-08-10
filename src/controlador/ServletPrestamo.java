package controlador;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Prestamo;
import modelo.ModeloPrestamo;

@WebServlet("/ServletPrestamo")
public class ServletPrestamo extends HttpServlet {
	ModeloPrestamo m = new ModeloPrestamo();
	private static final long serialVersionUID = 1L;
       
   
    public ServletPrestamo() {
        super();
        
    }

    //SERVICE
	protected void service (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	
	//BUSCARXCOD
	private void buscarcod(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dato = request.getParameter("cod1");
		
		// Validar que no llegue vacío o solo espacios
		if (dato == null || dato.trim().isEmpty()) {
			request.setAttribute("mensajeErrorP", "Por favor, ingrese un código de libro.");
			request.getRequestDispatcher("prestamo-lista.jsp").forward(request, response);
			return;
		}
		
		try {
		// Convertir a entero
		int codigo = Integer.parseInt(dato);
		
		// Consultar base de datos
		List<Prestamo> info = new ModeloPrestamo().buscarPrestamoxcod(codigo);
		
		// Validar si la lista está vacía (libro no encontrad)
		if (info == null || info.isEmpty()) {
			request.setAttribute("mensajeErrorP", "El código de libro " + codigo + " no existe en la lista.");
		} else {
		// Si el libro existe, enviamos la lista encontrada
			request.setAttribute("data", info);
		}
		
		request.getRequestDispatcher("prestamo-lista.jsp").forward(request, response);
			
		} catch (NumberFormatException e) {
		// Se ejecuta si el usuario escribe letras en el código
			request.setAttribute("mensajeErrorP", "El código ingresado debe ser un número entero.");
			request.getRequestDispatcher("prestamo-lista.jsp").forward(request, response);
		}
	}

	
	//ELIMINAR
	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dato = request.getParameter("cod");
		int codigo = Integer.parseInt(dato);
		m.eliminarPrestamo(codigo);
		request.getRequestDispatcher("ServletPrestamo?tipo=listar").forward(request, response);
		
	}

	//ACTUALIZAR
	private void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Prestamo obj = new Prestamo();
		String cod = request.getParameter("txt_cod");
		String cod_libro = request.getParameter("txt_cod_libro");
		String nom = request.getParameter("txt_nom");
		String ape = request.getParameter("txt_ape");
		String fecp = request.getParameter("txt_fec_p");
		String fecd = request.getParameter("txt_fec_d");
		String estado = request.getParameter("txt_estado");
		
			obj.setCod_libro(Integer.parseInt(cod_libro));
			obj.setCod_prestamo(Integer.parseInt(cod));
			obj.setNombre(nom);
			obj.setApellido(ape);
			obj.setFecha_prestamo(LocalDate.parse(fecp));
			obj.setFecha_devolucion(LocalDate.parse(fecd));
			obj.setEstado(estado);
		
		
			int estado2 = m.actualizarPrestamo(obj);
			if (estado2 != -1)
				listar(request, response);
			else
				response.sendRedirect("error.html");
	}

	
	//REGISTRAR
	private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Prestamo obj = new Prestamo();
		String cod_libro = request.getParameter("txt_cod_libro");
		String nom = request.getParameter("txt_nom");
		String ape = request.getParameter("txt_ape");
		String fecp = request.getParameter("txt_fec_p");
		String fecd = request.getParameter("txt_fec_d");
		String estado = request.getParameter("txt_estado");
		
		// 1. Validar que los campos requeridos no estén vacíos
		if (cod_libro == null || cod_libro.trim().isEmpty() ||
		nom == null || nom.trim().isEmpty() ||
		ape == null || ape.trim().isEmpty() ||
	    fecp == null || fecp.trim().isEmpty() ||
		fecd == null || fecd.trim().isEmpty() ||
		estado == null || estado.trim().isEmpty() || estado.equals("Seleccionar"))
		{
						
        request.setAttribute("mensajeErrorP", "Por favor, complete todos los campos obligatorios.");
	    request.getRequestDispatcher("prestamo-registra.jsp").forward(request, response);
		return;
		}
		
		// 2. Validar formato de fecha
		
		LocalDate fechaPrestamo = null; 
		LocalDate fechaDevolucion = null;
		
		try {
			fechaPrestamo = LocalDate.parse(fecp);
		} catch (Exception e) {
			request.setAttribute("mensajeErrorP", "La fecha ingresada es inválida. No se permiten letras (Formato: YYYY-MM-DD).");
			request.getRequestDispatcher("prestamo-registra.jsp").forward(request, response);
		}
		
		try {
			fechaDevolucion = LocalDate.parse(fecd);
		} catch (Exception e) {
			request.setAttribute("mensajeErrorP", "La fecha ingresada es inválida. No se permiten letras (Formato: YYYY-MM-DD).");
		}
		
		// 3. Validar código de libro (solo números enteros)
		
		int codLibro = 0;
		
		try {
			codLibro = Integer.parseInt(cod_libro);
			if (codLibro < 0) {
				request.setAttribute("mensajeErrorP", "El código de libro no puede ser número negativo.");
				request.getRequestDispatcher("prestamo-registra.jsp").forward(request, response);
				return;
			}
		}
		 catch (Exception e) {
			 	request.setAttribute("mensajeErrorP", "El campo código de libro no permite letras ni caracteres especiales.");
			 	request.getRequestDispatcher("prestamo-registra.jsp").forward(request, response);
			 	return;
		 	}
		
		// 4. Si todas las validaciones pasan, se guarda el objeto
			
		obj.setCod_libro(codLibro);
		obj.setNombre(nom);
		obj.setApellido(ape);
		obj.setFecha_prestamo(fechaPrestamo);
		obj.setFecha_devolucion(fechaDevolucion);
		obj.setEstado(estado);
		
		int estadoP = m.registrarPrestamo(obj);
		if (estadoP != -1) {
			listar(request, response);
		} else {
			request.setAttribute("mensajeErrorP", "Error de base de datos al intentar registrar el libro.");
			request.getRequestDispatcher("prestamo-registra.jsp").forward(request, response);
			}
		}
	
	//BUSCAR
	private void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dato = request.getParameter("cod");
		int codigo = Integer.parseInt(dato);
		Prestamo x = m.buscarPrestamo(codigo);
		request.setAttribute("registro", x);
		request.getRequestDispatcher("prestamo-actualiza.jsp").forward(request, response);
		
	}

	
	//LISTAR
	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Prestamo> info = new ModeloPrestamo().listar();
		request.setAttribute("data", info);
		request.getRequestDispatcher("prestamo-lista.jsp").forward(request, response);
		
	}

}
