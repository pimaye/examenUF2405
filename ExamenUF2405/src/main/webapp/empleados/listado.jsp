<%@page import="modelo.Empleado"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List" %>
<jsp:include page="/plantillas/cabecera.jsp"></jsp:include>

<a href="?opcion=nuevo" class="boton">Insertar Empleado</a>

<h1>Listado de Empleados</h1>
<% List<Empleado> lista = (List<Empleado>) request.getAttribute("listaEmpleados"); 
   if (lista==null) {
%>
<h2>No hay datos de empleados registrados</h2>
<% } else {%>
<table class="estilo-tabla">
	<tr>
		<th>Código Empleado:</th>
		<th>Código Departamento</th>
		<th>Ext. Teléfono</th>
		<th>Fecha Nacimiento</th>
		<th>fecha Ingreso</th>
		<th>Salario</th>
		<th>Comisión</th>
		<th>Número hijos</th>
		<th>Nombre</th>
		<th>Editar</th>
		<th>Eliminar</th>
	</tr>
	<% 
		for (Empleado emp:lista) {
			%>
			<tr>
				<td><%=emp.getCodEmpleado() %></td>
				<td><%=emp.getCodDepartamento() %></td>
				<td><%=emp.getTelefono()%></td>
				<td><%=emp.getFechaNacimiento()%></td>
				<td><%=emp.getFechaIngreso() %></td>
				<td><%=emp.getSalario() %></td>
				<td><%=emp.getComision() %></td>
				<td><%=emp.getNumHijos() %></td>
				<td><%=emp.getNombre() %></td>
				<td><a href="empleados?opcion=editar&codEmpleado=<%=emp.getCodEmpleado()%>">Editar</a></td>
				<td><a href="empleados?opcion=eliminar&codEmpleado=<%=emp.getCodEmpleado()%>">X</a></td>
			</tr>
			<%
		}
}
	%>
	
</table>


<jsp:include page="/plantillas/pie.jsp"></jsp:include>