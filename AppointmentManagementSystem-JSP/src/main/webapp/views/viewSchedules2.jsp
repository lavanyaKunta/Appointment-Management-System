<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>


<%@ page import="com.example.AppointmentManagementSystemJSP.model.DoctorScheduleModel" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Doctor Schedules</title>
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

    <h1>Doctor Schedules</h1>
    <h3  style="color: red">${msg}</h3>
    <table border="1">
        <tr>
            <th>ScheduleID</th>
            <th>startDateTime</th>
            <th>endDateTime</th>
            <th>status</th>
            <th>Appointment</th>

        </tr>
        <%
            List<DoctorScheduleModel> scheduleList = (List<DoctorScheduleModel>) request.getAttribute("schedules");
            if(scheduleList != null) {
                for(DoctorScheduleModel schedule : scheduleList) {
        %>
        <tr>
            <td><%= schedule.getId() %></td>
            <td><%= schedule.getStartDateTime() %></td>
            <td><%= schedule.getEndDateTime() %></td>
            <td><%= schedule.getStatus() %></td>
            <td>
           <form action="createAppointment" method="get">
            <input type="hidden" id="id" name="patientId" value="${patientId}" required>
            <input type="hidden" id="id" name="doctorId" value="${doctorId}" required>
            <input type="hidden" id="id" name="scheduleId" value="<%= schedule.getId() %>" required>

            <input type="submit" value="Create Appointment">
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