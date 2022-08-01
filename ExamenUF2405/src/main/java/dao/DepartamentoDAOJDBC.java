package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Departamento;
import utilidades.ConexionBD;

public class DepartamentoDAOJDBC implements DepartamentoDAO {

	private ConexionBD conexion;
	private Statement consulta = null;
	private PreparedStatement consultaPreparada = null;
	private ResultSet resultado = null;
	
	public DepartamentoDAOJDBC() {
		conexion = new ConexionBD();
	}
	
	@Override
	public List<Departamento> getDepartamentos() {
		List<Departamento> listaDepartamentos = new ArrayList<Departamento>();
		Connection con = conexion.getConexion();
		
		try {
			consulta = con.createStatement();
			resultado = consulta.executeQuery("select * from departamentos");
			while (resultado.next()) {
				int codDepartamento = resultado.getInt("cod_departamento");
				int codCentro = resultado.getInt("cod_centro");
				String tipoDir = resultado.getString("tipo_dir");
				int presupuesto = resultado.getInt("presupuesto");
				String nombre = resultado.getString("nombre");
				
				Departamento emp = new Departamento(codDepartamento, codCentro, tipoDir, presupuesto, nombre);
				
				

				listaDepartamentos.add(emp);
			}
			System.out.println("Añadidos todos los departamentos: ");
			System.out.println(listaDepartamentos);
		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta sobre departamentos: "+e.getMessage());
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

		
		return listaDepartamentos;
	}

	@Override
	public Departamento getDepartamento(int codDepartamento) {
		Connection con = conexion.getConexion();
		Departamento dpto = null;
		
		try {
			consultaPreparada = con.prepareStatement("select * from Departamentos"
					+ " where cod_Departamento = ?");
			consultaPreparada.setInt(1, codDepartamento);
			resultado=consultaPreparada.executeQuery();
			
			// Bucle para recorrer todas las filas que devuelve la consulta
			if (resultado.next()) {
				

				int codCentro = resultado.getInt("cod_centro");
				String tipoDir = resultado.getString("tipo_dir");
				int presupuesto = resultado.getInt("presupuesto");
				String nombre = resultado.getString("nombre");
				
				dpto = new Departamento(codDepartamento, codCentro, tipoDir, presupuesto, nombre);
				System.out.println("Departamento obtenido: "+dpto);
			}
			
		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta sobre un Departamento: "
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
		return dpto;
	}

	@Override
	public int insertarDepartamento(Departamento dpto) {
		Connection con = conexion.getConexion();
		int resultado=0;
		
		try {
			consultaPreparada = con.prepareStatement("INSERT INTO Departamentos "
					+ "VALUES (?,?,?,?,?)");
			
			consultaPreparada.setInt(1, dpto.getCodDepartamento());
			consultaPreparada.setInt(2, dpto.getCodCentro());
			consultaPreparada.setString(3, dpto.getTipoDir());
			consultaPreparada.setInt(4, dpto.getPresupuesto());
			consultaPreparada.setString(5, dpto.getNombre());
			
			resultado=consultaPreparada.executeUpdate();
			System.out.println("Departamento insertado: ");
			System.out.println(dpto);

		} catch (SQLException e) {
			System.out.println("Error al realizar la inserción del departamento: " + dpto
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
	public int actualizarDepartamento(Departamento dpto) {
		Connection con = conexion.getConexion();
		int resultado=0;
		
		try {
			consultaPreparada = con.prepareStatement("UPDATE departamentos\r\n"
					+ "SET cod_centro=?,\r\n"
					+ "    tipo_dir=?, \r\n"
					+ "    presupuesto=?, \r\n"
					+ "    nombre=? \r\n"
					+ "WHERE cod_departamento=?");
			

			consultaPreparada.setInt(1, dpto.getCodCentro());
			consultaPreparada.setString(2, dpto.getTipoDir());
			consultaPreparada.setInt(3, dpto.getPresupuesto());
			consultaPreparada.setString(4, dpto.getNombre());
			consultaPreparada.setInt(5, dpto.getCodDepartamento());
			resultado=consultaPreparada.executeUpdate();
			
			System.out.println("Departamento actualizado: ");
			System.out.println(dpto);

		} catch (SQLException e) {
			System.out.println("Error al realizar la actualizacion del departamento: "+consultaPreparada
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
	public int eliminarDepartamento(int codDepartamento) {
		Connection con = conexion.getConexion();
		int resultado=0;
		
		try {
			consultaPreparada = con.prepareStatement("DELETE FROM Departamentos\r\n"
					+ "WHERE cod_departamento = ?");
			
			consultaPreparada.setInt(1, codDepartamento);
			resultado=consultaPreparada.executeUpdate();
			System.out.println("Departamento borrado correctamente: "+codDepartamento);

		} catch (SQLException e) {
			System.out.println("Error al realizar el borrado de Departamento: "+e.getMessage());
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
