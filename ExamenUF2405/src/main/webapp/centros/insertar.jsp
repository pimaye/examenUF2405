<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:include page="/plantillas/cabecera.jsp"></jsp:include>



<!--  incio del cuerpo de la página -->
<h1>Nuevo Centro</h1>
<form action="centros" method="post">
<input type="hidden" name="opcion" value="insertar" />
	<div>
		<label for="codCentro">Código Centro: </label>
		<input type="text" name="codCentro" id="codCentro" />
	</div>
	<div>
		<label for="nombre">Título: </label>
		<input type="text" name="nombre" id="nombre" />
	</div>
	<div>
		<label for="direccion">Dirección: </label>
		<input type="text" name="direccion" id="direccion" />
	</div>
	<input type="submit" value="enviar">
</form>




<jsp:include page="/plantillas/pie.jsp"></jsp:include>