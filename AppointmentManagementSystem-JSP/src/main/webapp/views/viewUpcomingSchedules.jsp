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
    <h3 style="color: blue;">${msg}</h3>
    <h1>Doctor Schedules</h1>
    <table border="1">
        <tr>
            <th>ScheduleID</th>
            <th>startDateTime</th>
            <th>endDateTime</th>
            <th>status</th>
            <th>Delete Schedule</th>

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
            <td><form action="cancelSchedule" method="get">
                 <input type="hidden" id="doctorId" name="doctorId" value="<%= schedule.getDoctor().getId() %>" required>

                  <input type="hidden" id="scheduleId" name="scheduleId" value="<%= schedule.getId() %>" required>

                 <input type="submit" value="cancelSchedule">
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