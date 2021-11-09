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
	<title>Present Pantry</title>
</head>
<body>
<h1>Welcome ${user.firstName}!</h1>
<h2>Here's a Peek Inside Your Pantry...</h2>
<a href="/logout">Logout</a>
<a href="/items/new">Add Item to Pantry</a>
<a href="/shoppingList">Go to My Shopping List</a>
<table class="table table-dark">
<tr>
<th>Item Name</th>
<th>Qty</th>
<th>Added By</th>
<th>Action</th>
</tr>
<tbody>
<c:forEach items="${allItems}" var="item">
<tr>
<td>${item.name}</td>
<td>${item.quantity}</td>
<td>${item.creator.firstName}</td>
<%-- <td>${item.likers.size()}</td> --%>
<td>
<c:choose>
<c:when test="${item.staplers.contains(user)}">
<a href="/${item.id}/unstaple">Unstaple Item</a>
</c:when>
<c:when test="${item.creator.id == user.id}">
<a href="${item.id}/staple">Staple Item</a> 
</c:when>
</c:choose>
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
</html>