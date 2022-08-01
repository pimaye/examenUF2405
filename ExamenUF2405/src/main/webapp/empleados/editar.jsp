<%@page import="modelo.Empleado"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="modelo.Departamento" %>
<%@page import="java.util.List"%>

<jsp:include page="/plantillas/cabecera.jsp"></jsp:include>

<%
	List<Departamento> listaDepartamentos = (List<Departamento>)
	              request.getAttribute("listaDepartamentos");

	Empleado emp = (Empleado) request.getAttribute("emp");

%>

<!--  incio del cuerpo de la página -->
<h1>Modificar empleado</h1>
<form action="empleados" method="post">
	<input type="hidden" name="opcion" value="editar" />
	<div>
		<label for="codEmpleado">Código empleado: </label>
		<input type="text" name="codEmpleado" id="codEmpleado" value="<%=emp.getCodEmpleado()%>"/>
	</div>

	<div>
		<label for="codDepartamento">Departamento:</label>
		<select name="codDepartamento" id="codDepartamento">
			<% for (Departamento d: listaDepartamentos) { 
				if (d.getCodDepartamento()==emp.getCodDepartamento()){
					out.println("<option value='"+emp.getCodDepartamento()+"' selected>"
					            +d.getNombre()+ "</option>" );
				} else {
					out.println("<option value='"+emp.getCodDepartamento()+"'>"
				            +d.getNombre()+ "</option>" );
				}
			}
			%>
		</select>
	</div>
	<div>
		<label for="telefono">Extensión telefónica: </label>
		<input type="text" name="telefono" id="telefono" value="<%=emp.getTelefono() %>"/>
	</div>
	<div>
		<label for="fechaNacimiento">Fecha Nacimiento: </label>
		<input type="date" name="fechaNacimiento" id="fechaNacimiento" value="<%=emp.getFechaNacimiento() %>" />
	</div>
	<div>
		<label for="fechaIngreso">Fecha Ingreso: </label>
		<input type="date" name="fechaIngreso" id="fechaIngreso" value="<%=emp.getFechaIngreso() %>"/>
	</div>
	
	<div>
		<label for="salario">Salario: </label>
		<input type="text" name="salario" id="salario" value="<%=emp.getSalario()%>"/>
	</div>

	<div>
		<label for="comision">Comisión: </label>
		<input type="number" name="comision" id="comision" min="0" max="500" value="<%=emp.getComision()%>"/>
	</div>
	<div>
		<label for="numHijos">Número de hijos: </label>
		<input type="number" name="numHijos" id="numHijos" min="0" max="10" value="<%=emp.getNumHijos()%>"/>
	</div>
	<div>
		<label for="nombre">Nombre: </label>
		<input type="text" name="nombre" id="nombre" value="<%= emp.getNombre()%>"/>
	</div>
	<input type="submit" value="enviar">
</form>




<jsp:include page="/plantillas/pie.jsp"></jsp:include>