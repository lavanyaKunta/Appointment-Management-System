<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>


<%@ page import="com.example.AppointmentManagementSystemJSP.model.AppointmentModel" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Doctor UpComing Appointments</title>
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

    <h1>Doctor UpComing Appointments</h1>
    <table border="1">
        <tr>
            <th>AppointmentID</th>
            <th>DoctorId</th>
            <th>PatientId</th>
            <th>startDateTime</th>
            <th>endDateTime</th>
            <th>AppointmentType</th>
            <th>status</th>
            <th>changeStatus</th>

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
             <form action="changeAppointmentStatus" method="get">
                <input type="hidden" id="doctorId" name="doctorId" value="${id}" required>
                 <input type="hidden" id="appointmentId" name="appointmentId" value="<%= appointment.getId() %>" required>

                <select id="status" name="status">
                   <option>chose..</option>
                  <option value="CONFIRMED">CONFIRMED</option>
                  <option value="REJECTED">REJECTED</option>
                  <option value="COMPLETED">COMPLETED</option>
                </select>
                <input type="submit" value="ChangeStatus">
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