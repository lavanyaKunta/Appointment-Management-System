<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Appointment Management System</title>
</head>
<body>

        <h1> Admin </h1>
                <form:form action="adminRegisterForm">
                    <input type="submit" value="Register" />
                </form:form>
                <br>
        <form:form action="adminLoginForm">
            <input type="submit" value="Login" />
        </form:form>
        <br>
        <h1> Patient </h1>
        <form:form action="patientRegisterForm">
            <input type="submit" value="Register" />
        </form:form>
        <br>
        <form:form action="patientLoginForm">
            <input type="submit" value="Login" />
        </form:form>
        <h1> Doctor</h1>
     <!--   <form:form action="doctorRegisterForm">
            <input type="submit" value="Register" />
        </form:form> -->
        <br>
        <form:form action="doctorLoginForm">
            <input type="submit" value="Login" />
        </form:form>
</body>
</html>
