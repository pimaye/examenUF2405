<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="modelo.Departamento" %>
<%@page import="java.util.List" %>
<jsp:include page="/plantillas/cabecera.jsp"></jsp:include>

<a href="?opcion=nuevo" class="boton">Insertar Departamento</a>
<h1>Listado de Departamentos</h1>
<% List<Departamento> lista = (List<Departamento>) request.getAttribute("listaDepartamentos"); 
   if (lista==null) {
%>
<h2>No hay datos de departamentos registrados</h2>
<% } else {%>
<table class="estilo-tabla">
	<tr>
		<th>Código Departamento</th>
		<th>Código Centro</th>
		<th>Tipo dirección</th>
		<th>Presupuesto</th>
		<th>Nombre</th>
		<th>Editar</th>
		<th>Eliminar</th>
	</tr>
	<% 
		for (Departamento dpto:lista) {
			%>
			<tr>
				<td><%=dpto.getCodDepartamento() %></td>
				<td><%=dpto.getCodCentro() %></td>
				<td><%=dpto.getTipoDir() %></td>
				<td><%=dpto.getPresupuesto() %></td>
				<td><%=dpto.getNombre() %></td>
				<td><a href="departamentos?opcion=editar&codDepartamento=<%=dpto.getCodDepartamento()%>">Editar</a></td>
				<td><a href="departamentos?opcion=eliminar&codDepartamento=<%=dpto.getCodDepartamento()%>">X</a></td>
			</tr>
			<%
		}
}
	%>
	
</table>


<jsp:include page="/plantillas/pie.jsp"></jsp:include>