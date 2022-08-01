package dao;

import java.util.List;

import modelo.Departamento;

public interface DepartamentoDAO {

	List<Departamento> getDepartamentos();
	Departamento getDepartamento(int codDepartamento);
	int insertarDepartamento(Departamento Departamento);
	int actualizarDepartamento(Departamento Departamento);
	int eliminarDepartamento(int codDepartamento);
	
}
