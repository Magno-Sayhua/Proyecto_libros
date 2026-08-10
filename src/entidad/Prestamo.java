package entidad;

import java.time.LocalDate;

public class Prestamo {

	
	//ATRIBUTOS PRIVADOS
	private int cod_prestamo, cod_libro; // aqui le puse cod_libro porque asi esta en la base de datos
	private String libro, nombre, apellido; //añadi libro para titulo
	private LocalDate fecha_prestamo, fecha_devolucion;
	private String estado;
	public int getCod_prestamo() {
		return cod_prestamo;
	}
	public void setCod_prestamo(int cod_prestamo) {
		this.cod_prestamo = cod_prestamo;
	}
	public int getCod_libro() {
		return cod_libro;
	}
	public void setCod_libro(int cod_libro) {
		this.cod_libro = cod_libro;
	}
	public String getLibro() {
		return libro;
	}
	public void setLibro(String libro) {
		this.libro = libro;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public LocalDate getFecha_prestamo() {
		return fecha_prestamo;
	}
	public void setFecha_prestamo(LocalDate fecha_prestamo) {
		this.fecha_prestamo = fecha_prestamo;
	}
	public LocalDate getFecha_devolucion() {
		return fecha_devolucion;
	}
	public void setFecha_devolucion(LocalDate fecha_devolucion) {
		this.fecha_devolucion = fecha_devolucion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	} 
	
	
	
	
}
