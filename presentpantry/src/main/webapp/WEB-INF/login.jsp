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
<div class="column">
<h1>Welcome Back to Present Pantry!</h1>
<p>${loginError}</p>
<form action="/login" method="POST">
<label>Email:</label>
<input type="email" name="lemail">
<label>Password:</label>
<input type="password" name="lpassword">
<a href="/">Don't Have an Account Yet? Register Here!</a>
<button>Login!</button>
</form>
</div>
</body>
</html>