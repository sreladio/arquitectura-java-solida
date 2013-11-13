<%@ page language="java" 
	   	 contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"%>
    	 
<%@ page import = "com.arquitecturajava.Libro" %>
<%@ page import = "java.util.List" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Formulario Edición Libro</title>
</head>
<body>

	<form id="formularioEdicion" action="SalvarLibro">
		<fieldset>
		<legend>Formulario edición libro</legend>
			<p>
				<label for="isbn">ISBN:</label>
				<input id="isbn" type="text" name="isbn" value="${libro.isbn}" >
			</p>
			
			<p>
				<label for="titulo">Titulo:</label>
				<input id="titulo" type="text" name="titulo" value="${libro.titulo}">
			</p>
			
			<p>
				<label for="categoria">Categoria:</label>
				<select name="categoria">
					<c:forEach var="categoria" items="${listaDeCategorias}">
						<option value="${categoria}">${categoria}</option>
					</c:forEach>
				</select>
			</p>
			
			<p>
				<input type="submit" value="Salvar">
			</p>
		</fieldset>
	</form>
</body>
</html>