<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>


<%@ page import="com.example.AppointmentManagementSystemJSP.model.DoctorModel" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Doctors</title>
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
    <h1>Doctors</h1>
    <table border="1">
        <tr>
            <th>DoctorID</th>
            <th>DoctorName</th>
            <th>Specialty</th>
            <th>Location</th>
            <th>HospitalName</th>
            <th>MobileNo</th>
            <th>ViewUpComingSchedules</th>

        </tr>
        <%
            List<DoctorModel> doctorsList = (List<DoctorModel>) request.getAttribute("doctors");
            if(doctorsList != null) {
                for(DoctorModel doctor : doctorsList) {
        %>
        <tr>
            <td><%= doctor.getId() %></td>
            <td><%= doctor.getName() %></td>
            <td><%= doctor.getSpecialty() %></td>
            <td><%= doctor.getLocation() %></td>
            <td><%= doctor.getHospitalName() %></td>
            <td><%= doctor.getMobileNo() %></td>
            <td><form action="viewDoctorUpComingSchedules" method="get">
                <input type="hidden" id="doctorId" name="doctorId" value="<%= doctor.getId() %>">
                 <input type="hidden" id="patientId" name="patientId" value="${patientId}">
                <input type="submit" value="View DoctorUpComing Schedules">
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