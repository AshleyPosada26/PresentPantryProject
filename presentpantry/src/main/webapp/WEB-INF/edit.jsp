<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<link rel="stylesheet" href="/css/style.css"/>
	<meta charset="UTF-8">
	<title>Edit Item in Pantry</title>
</head>
<body>
<h1>Edit ${item.name}</h1>
<a href="/pantry">Go Back to My Pantry</a>
<form:form action="/items/${item.id}/edit" method="POST" modelAttribute="item">
<p>
<form:label path="name">Item Name</form:label>
<form:errors path="name"/>
<form:input path="name"/>
</p>
<p>
<form:label path="quantity">Item Quantity</form:label>
<form:errors path="quantity"/>
<form:input path="quantity"/>
</p>
<p>
<form:hidden value="${ user_name.id }" path="creator"/>
</p>
<input type="hidden" name="_method" value="put">
<button>Update Item</button>
</form:form>
<form action="/delete/${item.id}" method="post">
<input type="hidden" name="_method" value="delete"/>
<button>Delete</button>
</form>
</body>
</body>
</html>