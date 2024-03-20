<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>


<%@ page import="com.example.AppointmentManagementSystemJSP.model.AppointmentModel" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Patient Appointments History</title>
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
    <h3 style="color: blue;">${msg}</h3>

    <h1>Patient Appointments History</h1>
    <table border="1">
        <tr>
            <th>AppointmentID</th>
            <th>DoctorId</th>
            <th>PatientId</th>
            <th>startDateTime</th>
            <th>endDateTime</th>
            <th>AppointmentType</th>
            <th>status</th>

        </tr>
        <%
            List<AppointmentModel> appointmentList = (List<AppointmentModel>) request.getAttribute("appointments");
            if(appointmentList != null) {
                for(AppointmentModel appointment : appointmentList) {
        %>
        <tr>
            <td><%= appointment.getId() %></td>
            <td><%= appointment.getDoctor().getId() %></td>
            <td><%= appointment.getPatient().getId() %></td>
            <td><%= appointment.getStartDateTime() %></td>
            <td><%= appointment.getEndDateTime() %></td>
            <td><%= appointment.getType() %></td>
            <td><%= appointment.getStatus() %></td>

        </tr>
        <%
                }
            }
        %>
    </table>

</body>
</html>