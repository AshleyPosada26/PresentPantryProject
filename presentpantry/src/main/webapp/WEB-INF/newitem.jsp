<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page isErrorPage="true"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Add Item</title>
</head>
<body>
<h1>Add Item to Pantry</h1>	
<hr>
<a href="/pantry">Go Back to My Pantry</a>
<form:form method="POST" action="/newItem" modelAttribute="item">
<p>
<form:label path="name">Item Name</form:label>
<form:errors path="name"/>
<form:input path="name" value="Enter new item here"/>
</p>
<p>
<form:label path="quantity">Item Quantity</form:label>
<form:errors path="quantity"/>
<form:input path="quantity" value="Enter quantity here"/>
</p>
<p>
<form:hidden value="${ user_name.id }" path="creator"/>
</p>
<button>Add Item to Pantry!</button>
</form:form>
<h1>Add Item to Shopping List</h1>	
<hr>
<form:form method="POST" action="/updatedShoppingList" modelAttribute="item">
<p>
<form:label path="name">Item Name</form:label>
<form:errors path="name"/>
<form:input path="name" value="Enter new item here"/>
</p>
<p>
<form:label path="quantity">Item Quantity</form:label>
<form:errors path="quantity"/>
<form:input path="quantity" value="Enter quantity here"/>
</p>
<p>
<form:hidden value="${ user_name.id }" path="creator"/>
</p>
<button>Add Item to Shopping List!</button>
</form:form>
</body>
</body>
</html>