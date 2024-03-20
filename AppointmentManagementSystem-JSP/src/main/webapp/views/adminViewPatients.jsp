<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>


<%@ page import="com.example.AppointmentManagementSystemJSP.model.PatientModel" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Patients</title>
     <style>
            table {
                width: 100%;
                border-collapse: collapse;
            }
            th, td {
                border: 1px solid #dddddd;
                text-align: left;
                padding: 8px;
            }
            th {
                background-color: #f2f2f2;
            }
        </style>
</head>
<body>
    <h1>Patients</h1>
    <table border="1">
        <tr>
            <th>PatientID</th>
            <th>PatientName</th>
            <th>Age</th>
            <th>mobileNo</th>
        </tr>
        <%
            List<PatientModel> patientsList = (List<PatientModel>) request.getAttribute("patients");
            if(patientsList != null) {
                for(PatientModel patient : patientsList) {
        %>
        <tr>
            <td><%= patient.getId() %></td>
            <td><%= patient.getName() %></td>
            <td><%= patient.getAge() %></td>
            <td><%= patient.getMobileNo() %></td>

        </tr>
        <%
                }
            }
        %>
    </table>

</body>
</html>