package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Empleado;
import utilidades.ConexionBD;

public class EmpleadoDAOJDBC implements EmpleadoDAO {

	private ConexionBD conexion;
	private Statement consulta = null;
	private PreparedStatement consultaPreparada = null;
	private ResultSet resultado = null;
	
	public EmpleadoDAOJDBC() {
		conexion = new ConexionBD();
	}
	
	@Override
	public List<Empleado> getEmpleados() {
		List<Empleado> listaEmpleados = new ArrayList<Empleado>();
		Connection con = conexion.getConexion();
		
		try {
			consulta = con.createStatement();
			resultado = consulta.executeQuery("select * from Empleados");
			while (resultado.next()) {
				int codEmpleado = resultado.getInt("cod_empleado");
				int codDepartamento = resultado.getInt("cod_departamento");
				int telefono = resultado.getInt("telefono");
				Date fechaNacimiento = resultado.getDate("fecha_nacimiento");
				Date fechaIngreso = resultado.getDate("fecha_ingreso");
				int salario = resultado.getInt("salario");
				int comision = resultado.getInt("comision");
				short numHijos = resultado.getShort("num_hijos");
				String nombre = resultado.getString("nombre");
				
				Empleado emp = new Empleado(codEmpleado, codDepartamento, telefono, fechaNacimiento, 
						fechaIngreso, salario, comision, numHijos, nombre);
				

				listaEmpleados.add(emp);
			}
			System.out.println("Añadidos todos los empleados: ");
			System.out.println(listaEmpleados);
		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta sobre Empleados: "+e.getMessage());
		} finally {
			try {
				resultado.close();
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}

		
		return listaEmpleados;
	}

	@Override
	public Empleado getEmpleado(int codEmpleado) {
		Connection con = conexion.getConexion();
		Empleado emp = null;
		
		try {
			consultaPreparada = con.prepareStatement("select * from empleados"
					+ " where cod_empleado = ?");
			consultaPreparada.setInt(1, codEmpleado);
			resultado=consultaPreparada.executeQuery();
			
			// Bucle para recorrer todas las filas que devuelve la consulta
			if (resultado.next()) {
				int codDepartamento = resultado.getInt("cod_departamento");
				int telefono = resultado.getInt("telefono");
				Date fechaNacimiento = resultado.getDate("fecha_nacimiento");
				Date fechaIngreso = resultado.getDate("fecha_ingreso");
				int salario = resultado.getInt("salario");
				int comision = resultado.getInt("comision");
				short numHijos = resultado.getShort("num_hijos");
				String nombre = resultado.getString("nombre");
				
				emp = new Empleado(codEmpleado, codDepartamento, telefono, fechaNacimiento, 
						fechaIngreso, salario, comision, numHijos, nombre);
				
				System.out.println("Empleado encontrado: "+emp );
			}
			
		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta sobre un empleado: "
		         +e.getMessage());
		} finally {
			try {
				resultado.close();
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}
		return emp;
	}

	@Override
	public int insertarEmpleado(Empleado emp) {
		Connection con = conexion.getConexion();
		int resultado=0;
		
		try {
			consultaPreparada = con.prepareStatement("INSERT INTO empleados "
					+ "VALUES "
					+ "(?,?,?,?,?,?,?,?,?)");
			
			consultaPreparada.setInt(1, emp.getCodEmpleado());
			consultaPreparada.setInt(2, emp.getCodDepartamento());
			consultaPreparada.setInt(3, emp.getTelefono());
			consultaPreparada.setDate(4, emp.getFechaNacimiento());
			consultaPreparada.setDate(5, emp.getFechaIngreso());
			consultaPreparada.setInt(6, emp.getSalario());
			consultaPreparada.setInt(7, emp.getComision());
			consultaPreparada.setShort(8, emp.getNumHijos());
			consultaPreparada.setString(9, emp.getNombre());
			
			resultado=consultaPreparada.executeUpdate();
			System.out.println("Empleado insertado: ");
			System.out.println(emp);

		} catch (SQLException e) {
			System.out.println("Error al realizar la inserción del empleado: " + emp
		        +e.getMessage());
		} finally {
			try {
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}
		return resultado;
	}

	@Override
	public int actualizarEmpleado(Empleado emp) {
		Connection con = conexion.getConexion();
		int resultado=0;
		
		try {
			consultaPreparada = con.prepareStatement("UPDATE empleados\r\n"
					+ "SET cod_departamento=?,\r\n"
					+ "    telefono=?, \r\n"
					+ "    fecha_nacimiento=?, \r\n"
					+ "    fecha_ingreso=?, \r\n"
					+ "    salario=?, \r\n"
					+ "    comision=?, \r\n"
					+ "    num_hijos=?, \r\n"
					+ "    nombre=?\r\n"
					+ "WHERE cod_empleado=?");
			

			consultaPreparada.setInt(1, emp.getCodDepartamento());
			consultaPreparada.setInt(2, emp.getTelefono());
			consultaPreparada.setDate(3, emp.getFechaNacimiento());
			consultaPreparada.setDate(4, emp.getFechaIngreso());
			consultaPreparada.setInt(5, emp.getSalario());
			consultaPreparada.setInt(6, emp.getComision());
			consultaPreparada.setShort(7, emp.getNumHijos());
			consultaPreparada.setString(8, emp.getNombre());
			consultaPreparada.setInt(9, emp.getCodEmpleado());
			resultado=consultaPreparada.executeUpdate();
			
			System.out.println("Empleado actualizado: ");
			System.out.println(emp);

		} catch (SQLException e) {
			System.out.println("Error al realizar la actualizacion del empleado: "
					+e.getMessage());
		} finally {
			try {
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}
		return resultado;
	}

	@Override
	public int eliminarEmpleado(int codEmpleado) {
		Connection con = conexion.getConexion();
		int resultado=0;
		
		try {
			consultaPreparada = con.prepareStatement("DELETE FROM empleados\r\n"
					+ "WHERE cod_empleado = ?");
			
			consultaPreparada.setInt(1, codEmpleado);
			resultado=consultaPreparada.executeUpdate();
			System.out.println("Empleado borrado correctamente: "+codEmpleado);

		} catch (SQLException e) {
			System.out.println("Error al realizar el borrado de empleado: "+e.getMessage());
		} finally {
			try {
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}
		return resultado;
	}
		

}















