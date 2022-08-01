package modelo;

import java.sql.Date;
import java.util.Objects;

public class Empleado {

	private int codEmpleado;
	private int codDepartamento;
	private int telefono;
	private Date fechaNacimiento;
	private Date fechaIngreso;
	private int salario;
	private int comision;
	private short numHijos;
	private String nombre;
	
	
	public Empleado() {
		super();
	}


	public Empleado(int codEmpleado, int codDepartamento, int telefono, 
			Date fechaNacimiento, Date fechaIngreso,
			int salario, int comision, short numHijos, String nombre) {
		super();
		this.codEmpleado = codEmpleado;
		this.codDepartamento = codDepartamento;
		this.telefono = telefono;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaIngreso = fechaIngreso;
		this.salario = salario;
		this.comision = comision;
		this.numHijos = numHijos;
		this.nombre = nombre;
	}


	public int getCodEmpleado() {
		return codEmpleado;
	}


	public void setCodEmpleado(int codEmpleado) {
		this.codEmpleado = codEmpleado;
	}


	public int getCodDepartamento() {
		return codDepartamento;
	}


	public void setCodDepartamento(int codDepartamento) {
		this.codDepartamento = codDepartamento;
	}


	public int getTelefono() {
		return telefono;
	}


	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}


	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public Date getFechaIngreso() {
		return fechaIngreso;
	}


	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}


	public int getSalario() {
		return salario;
	}


	public void setSalario(int salario) {
		this.salario = salario;
	}


	public int getComision() {
		return comision;
	}


	public void setComision(int comision) {
		this.comision = comision;
	}


	public short getNumHijos() {
		return numHijos;
	}


	public void setNumHijos(short numHijos) {
		this.numHijos = numHijos;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	@Override
	public String toString() {
		return "Empleado [codEmpleado=" + codEmpleado + ", codDepartamento=" + codDepartamento + ", telefono="
				+ telefono + ", fechaNacimiento=" + fechaNacimiento + ", fechaIngreso=" + fechaIngreso + ", salario="
				+ salario + ", comision=" + comision + ", numHijos=" + numHijos + ", nombre=" + nombre + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(codEmpleado);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return codEmpleado == other.codEmpleado;
	}
	
	
	
}
