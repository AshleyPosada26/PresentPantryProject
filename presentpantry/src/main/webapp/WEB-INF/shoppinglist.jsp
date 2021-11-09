<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
	<link rel="stylesheet" href="/css/style.css"/>
	<meta charset="UTF-8">
	<title>Voila! Your Shopping List</title>
</head>
<body>
<h1>Voila! Your Shopping List is Ready!</h1>
<a href="/pantry">Go Back to My Pantry</a>
<a href="/items/new">Add Item to Shopping List</a>
<table class="table table-dark">
<tr>
<th>Item Name</th>
<th>Qty</th>
<th>Action</th>
</tr>
<tbody>
<c:forEach items="${allItems}" var="item">
<tr>
<td>${item.name}</td>
<td>${item.quantity}</td>
<td>
<a href="/items/${item.id}/edit">Edit</a> | <form action="/delete/${item.id}" method="post"> 
<input type="hidden" name="_method" value="delete"/> 
<button>Delete</button>
</form>
</td>
</tr>
</c:forEach>
</tbody>
</table>
</body>
</body>
</body>
</html>