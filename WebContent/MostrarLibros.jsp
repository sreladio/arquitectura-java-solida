<%@ page language="java" 
         contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"%>

<%@ page import = "java.util.List" %>    	 
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "com.arquitecturajava.Libro" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Lista de libros</title>
</head>
<body>

	<form action="FiltrarCategoria">
		<select name="categoria">
			<option value="seleccionar">seleccionar</option>
			<c:forEach var="categoria" items="${listaDeCategorias}">
				<option value="${categoria}">${categoria}</option>
			</c:forEach>
		</select>
		<input type="submit" value="filtrar">
	</form>
	
	<br>
	<c:forEach var="libro" items="${listaDeLibros}"> 
		${libro.isbn}  
		${libro.titulo}  
		${libro.categoria} 
		<a href="BorrarLibro?isbn=${libro.isbn}">Borrar</a> 
		<a href="FormularioEditarLibro?isbn=${libro.isbn}">Editar</a>
		<br/>
	</c:forEach>
	<br>
	<a href="FormularioInsertarLibro">Insertar Libro</a>
</body>
</html>