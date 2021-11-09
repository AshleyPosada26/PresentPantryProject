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
<div class="container">
<div class="row">
<div class="column">
<h1>Welcome to Present Pantry!</h1>
<h3>Ever realize you forgot that bottle of ketchup as soon as you returned home from the grocery store?</h3>
<h2>Well, those days are in the past! Present Pantry provides a clever way of monitoring the items in your pantry so you never forget to stock up on the essentials. </h2>
<form:form action="/register" method="POST" modelAttribute="user">
<div class="form-group">
<form:label path="firstName">First Name</form:label>
<form:errors path="firstName"/>
<form:input class="form-control" path="firstName"/>
</div>
<div class="form-group">
<form:label path="lastName">Last Name</form:label>
<form:errors path="lastName"/>
<form:input class="form-control" path="lastName"/>
</div>
<div class="form-group">
<form:label path="email">Email</form:label>
<form:errors path="email"/>
<form:input type="email" class="form-control" path="email"/>
</div>
<div class="form-group">
<form:label path="password">Password</form:label>
<form:errors path="password"/>
<form:password class="form-control" path="password"/>
</div>
<div class="form-group">
<form:label path="confirmPassword">Confirm Password</form:label>
<form:errors path="confirmPassword"/>
<form:password class="form-control" path="confirmPassword"/>
</div>
<div class="form-group">
<a href="/login">Already Have an Account? Login Here!</a>
</div>
<input class="btn btn-warning" type="submit" value="Get Tracking!"/>
</form:form>
</div>
</div>
</div>
</body>
</html>