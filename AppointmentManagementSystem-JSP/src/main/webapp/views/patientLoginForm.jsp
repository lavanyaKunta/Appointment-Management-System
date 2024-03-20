<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://www.springframework.org/tags/form"  prefix="form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Patient Login</title>
</head>
<body>
    <h1>Patient Login</h1>

    <form:form action="patientLogin" modelAttribute="patientLoginModel">
           <label>UserName:</label><br>
           <form:input path="username"/><br>
           <form:errors path="username" style="color: red;"/><br>

           <label>Password:</label><br>
           <form:input type="password" path="password"/><br>
           <form:errors path="password" style="color: red;"/><br>

           <input type="submit" value="login">
        </form:form>
</body>
</html>

