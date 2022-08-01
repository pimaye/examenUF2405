<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="modelo.Departamento" %>
<%@page import="java.util.List"%>
<jsp:include page="/plantillas/cabecera.jsp"></jsp:include>

<%
	List<Departamento> listaDepartamentos = (List<Departamento>)
	              request.getAttribute("listaDepartamentos");
%>

<!--  incio del cuerpo de la página -->
<h1>Nuevo Empleado</h1>
<form action="empleados" method="post">
<input type="hidden" name="opcion" value="insertar" />
	<div>
		<label for="codEmpleado">Código empleado: </label>
		<input type="text" name="codEmpleado" id="codEmpleado" />
	</div>

	<div>
		<label for="codDepartamento">Departamento:</label>
		<select name="codDepartamento" id="codDepartamento">
			<% for (Departamento d: listaDepartamentos) { %>
			<option value="<%=d.getCodDepartamento()%>"><%=d.getNombre() %></option>
			<%} %>
			
		</select>
	</div>
	<div>
		<label for="telefono">Extensión telefónica: </label>
		<input type="text" name="telefono" id="telefono" />
	</div>
	<div>
		<label for="fechaNacimiento">Fecha Nacimiento: </label>
		<input type="date" name="fechaNacimiento" id="fechaNacimiento" />
	</div>
	<div>
		<label for="fechaIngreso">Fecha Ingreso: </label>
		<input type="date" name="fechaIngreso" id="fechaIngreso" />
	</div>
	
	<div>
		<label for="salario">Salario: </label>
		<input type="text" name="salario" id="salario" />
	</div>

	<div>
		<label for="comision">Comisión: </label>
		<input type="number" name="comision" id="comision" min="0" max="500"/>
	</div>
	<div>
		<label for="numHijos">Número de hijos: </label>
		<input type="number" name="numHijos" id="numHijos" min="0" max="10"/>
	</div>
	<div>
		<label for="nombre">Nombre: </label>
		<input type="text" name="nombre" id="nombre" />
	</div>
	<input type="submit" value="enviar">
</form>




<jsp:include page="/plantillas/pie.jsp"></jsp:include>