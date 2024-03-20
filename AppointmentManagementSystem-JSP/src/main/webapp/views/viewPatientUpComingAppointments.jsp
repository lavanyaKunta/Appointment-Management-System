<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>


<%@ page import="com.example.AppointmentManagementSystemJSP.model.AppointmentModel" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Patient UpComing Appointments</title>
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

    <h1>Patient UpComing Appointments</h1>
    <table border="1">
        <tr>
            <th>AppointmentID</th>
            <th>DoctorId</th>
            <th>PatientId</th>
            <th>startDateTime</th>
            <th>endDateTime</th>
            <th>AppointmentType</th>
            <th>status</th>
            <th>cancelAppointment</th>
            <th>Payment</th>

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
            <td>
            <form action="cancelAppointment" method="get">
                <input type="hidden" id="patientId" name="patientId" value="${id}" required>
                <input type="hidden" id="appointmentId" name="id" value="<%= appointment.getId() %>" required>
                <input type="submit" value="cancelAppointment">
            </form>
            </td>
            <td>
                <form action="makePayment" method="get">
                 <input type="hidden" id="patientId" name="patientId" value="${id}" required>
                  <input type="hidden" id="appointmentId" name="appointmentId" value="<%= appointment.getId() %>" required>
                  <input type="submit" value="pay">
                 </form>
            </td>

        </tr>
        <%
                }
            }
        %>
    </table>

</body>
</html>