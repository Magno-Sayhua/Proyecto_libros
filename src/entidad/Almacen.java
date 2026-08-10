package entidad;

import java.time.LocalDate;

public class Almacen {

	// Atributos Privados	
	private int cod_almacen;
	private String titulo, autor;
	private LocalDate fecha_ingreso;
	private int stock;
	private String categoria;
	/* Para crear los Getters y setters de forma rápida, seleccionamos todas las variables private con el mouse, le damos click derecho
	   y de ahí vamos a Source para darle click a "Generate Getters and Setters"*/
	// Getters y setters
	public int getCod_almacen() {
		return cod_almacen;
	}
	public void setCod_almacen(int cod_almacen) {
		this.cod_almacen = cod_almacen;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public LocalDate getFecha_ingreso() {
		return fecha_ingreso;
	}
	public void setFecha_ingreso(LocalDate fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
