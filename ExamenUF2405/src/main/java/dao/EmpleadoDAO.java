package dao;

import java.util.List;

import modelo.Empleado;

public interface EmpleadoDAO {

	List<Empleado> getEmpleados();
	Empleado getEmpleado(int codEmpleado);
	int insertarEmpleado(Empleado emp);
	int actualizarEmpleado(Empleado emp);
	int eliminarEmpleado(int codEmpleado);
	
}
