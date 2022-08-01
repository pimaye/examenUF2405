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

public class CentroDAOJDBC implements DepartamentoDAO {

	private ConexionBD conexion;
	private Statement consulta = null;
	private PreparedStatement consultaPreparada = null;
	private ResultSet resultado = null;
	
	public CentroDAOJDBC() {
		conexion = new ConexionBD();
	}
	
	@Override
	public List<Centro> getCentro() {
		List<Centro> listaCentro = new ArrayList<Centro>();
		Connection con = conexion.getConexion();
		
		try {
			consulta = con.createStatement();
			resultado = consulta.executeQuery("select * from centros");
			while (resultado.next()) {
			
				int codCentro = resultado.getInt("cod_centro");
				String nombre = resultado.getString("nombre");
				String direccion = resultado.getString("direccion");
				
				
				
				Centro centro = new Centro(codCentro, nombre, direccion);
				
				

				listaCentro.add(centro);
			}
			System.out.println("Consulta centros: ");
			System.out.println(listaCentro);
		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta sobre consulta centros: "+e.getMessage());
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

		
		return listaCentro;
	}

	

	@Override
	public int eliminarCentro(int codCentro) {
		Connection con = conexion.getConexion();
		int resultado=0;
		
		try {
			consultaPreparada = con.prepareStatement("DELETE FROM Centro\r\n"
					+ "WHERE cod_centro = ?");
			
			consultaPreparada.setInt(1, codCentro);
			resultado=consultaPreparada.executeUpdate();
			System.out.println("Centro borrado correctamente: "+codCentro);

		} catch (SQLException e) {
			System.out.println("Error al realizar el borrado de Centro: "+e.getMessage());
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
