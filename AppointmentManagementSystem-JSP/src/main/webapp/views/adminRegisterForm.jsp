<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Registration</title>
</head>
<body>
<h3 style="color: blue;">${msg}</h3>

    <h1>Admin Registration</h1>
    <form:form action="registerAdmin" modelAttribute="adminModel">

        <label>Username: </label>
        <form:input path="username"/><br>
        <form:errors path="username" style="color: red;"/><br>

        <label>Password:</label><br>
        <form:input type="password" path="password"/><br>
        <form:errors path="password" style="color: red;"/><br>

        <input type="submit" value="Register">
    </form:form>
</body>
</html>
