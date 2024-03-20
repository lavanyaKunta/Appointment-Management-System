<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Patient Registration</title>
</head>
<body>
<h3 style="color: blue;">${msg}</h3>

    <h1>Patient Registration</h1>
    <form:form action="registerPatient" modelAttribute="patientModel">

        <label>Username: </label>
        <form:input path="username"/><br>
        <form:errors path="username" style="color: red;"/><br>

        <label>Password:</label><br>
        <form:input type="password" path="password"/><br>
        <form:errors path="password" style="color: red;"/><br>

        <label>Name:</label><br>
        <form:input path="name"/><br>
        <form:errors path="name" style="color: red;"/><br>

         <label>Age:</label><br>
          <form:input type="number" path="age"/><br>
          <form:errors path="age" style="color: red;"/><br>


        <label>Mobile No:</label><br>
        <form:input path="mobileNo"/><br>
        <form:errors path="mobileNo" style="color: red;"/><br>

        <input type="submit" value="Register">
    </form:form>
</body>
</html>
