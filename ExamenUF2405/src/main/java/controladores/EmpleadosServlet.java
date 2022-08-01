package controladores;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import dao.DepartamentoDAO;
import dao.DepartamentoDAOJDBC;
import dao.EmpleadoDAO;
import dao.EmpleadoDAOJDBC;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Departamento;
import modelo.Empleado;

/**
 * Servlet implementation class LibrosServlet
 */
@WebServlet("/empleados")
public class EmpleadosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpleadosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcion = request.getParameter("opcion");
		if (opcion ==null || opcion.equals("listado")) {
			mostrarListado(request,response);
		} else if (opcion.equals("nuevo")) {
			mostrarFormulario(request,response);
		} else if (opcion.equals("eliminar")) {
			eliminar(request,response);
		} else if (opcion.equals("editar")) {
			editar(request,response);
		}
	}

	private void editar(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int codEmpleado = Integer.parseInt(request.getParameter("codEmpleado"));
		
		EmpleadoDAO dao = new EmpleadoDAOJDBC();
		Empleado emp = dao.getEmpleado(codEmpleado);
		request.setAttribute("emp", emp);
		DepartamentoDAO daoDpto= new DepartamentoDAOJDBC();
		List<Departamento> lista = daoDpto.getDepartamentos();
		request.setAttribute("listaDepartamentos", lista);
		request.getRequestDispatcher("/empleados/editar.jsp").forward(request, response);
	
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int codEmpleado = Integer.parseInt(request.getParameter("codEmpleado"));
		EmpleadoDAO dao = new EmpleadoDAOJDBC();
		dao.eliminarEmpleado(codEmpleado);
		mostrarListado(request, response);
	}

	private void mostrarFormulario(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {
		DepartamentoDAO daoCen= new DepartamentoDAOJDBC();
		List<Departamento> lista = daoCen.getDepartamentos();
		request.setAttribute("listaDepartamentos", lista);
		request.getRequestDispatcher("/empleados/insertar.jsp").forward(request, response);
	}

	private void mostrarListado(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		EmpleadoDAO dao = new EmpleadoDAOJDBC();
		List<Empleado> lista = dao.getEmpleados();
		request.setAttribute("listaEmpleados", lista);
		request.getRequestDispatcher("/empleados/listado.jsp").forward(request, response);
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcion = request.getParameter("opcion");
		if (opcion !=null && opcion.equals("insertar")) {
			insertar(request,response);
		} else if (opcion!=null && opcion.equals("editar")) {
			actualizar(request,response);
		}
	}

	private void actualizar(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int codEmpleado = Integer.parseInt(request.getParameter("codEmpleado"));
		int codDepartamento = Integer.parseInt(request.getParameter("codDepartamento"));
		int telefono = Integer.parseInt(request.getParameter("telefono")); 
		Date fechaNacimiento = new Date(request.getDateHeader("fechaNacimiento"));
		Date fechaIngreso = new Date(request.getDateHeader("fechaIngreso"));
		int salario = Integer.parseInt(request.getParameter("salario"));
		int comision = Integer.parseInt(request.getParameter("comision")); 
		short numHijos = Short.parseShort(request.getParameter("numHijos")); 
		String nombre = request.getParameter("nombre");
		
		
		Empleado emp = new Empleado(codEmpleado, codDepartamento, telefono, fechaNacimiento, 
				fechaIngreso, salario, comision, numHijos, nombre);
		
		EmpleadoDAO dao = new EmpleadoDAOJDBC();
		dao.actualizarEmpleado(emp);
		mostrarListado(request, response);
		
	}

	private void insertar(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int codEmpleado = Integer.parseInt(request.getParameter("codEmpleado"));
		int codDepartamento = Integer.parseInt(request.getParameter("codDepartamento"));
		int telefono = Integer.parseInt(request.getParameter("telefono")); 
		Date fechaNacimiento = new Date(request.getDateHeader("fechaNacimiento"));
		Date fechaIngreso = new Date(request.getDateHeader("fechaIngreso"));
		int salario = Integer.parseInt(request.getParameter("salario"));
		int comision = Integer.parseInt(request.getParameter("comision")); 
		short numHijos = Short.parseShort(request.getParameter("numHijos")); 
		String nombre = request.getParameter("nombre");
		
		
		Empleado emp = new Empleado(codEmpleado, codDepartamento, telefono, fechaNacimiento, 
				fechaIngreso, salario, comision, numHijos, nombre);
		
		EmpleadoDAO dao = new EmpleadoDAOJDBC();
		dao.insertarEmpleado(emp);
		mostrarListado(request, response);
		
	}

}