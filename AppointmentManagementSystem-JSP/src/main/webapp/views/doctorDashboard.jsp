<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Doctor Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;

        }

        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            margin-bottom: 20px;
        }

        .menu-item {
            margin-bottom: 10px;

        }

        .menu-item form {
            display: inline-block;
        }

        .menu-item input[type="submit"] {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .menu-item input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Doctor Dashboard</h1>
        <div class="menu-item">
            <form action="createDoctorSchedule" method="get">
                <input type="hidden" id="id" name="doctorId" value="${id}" required>
                <input type="submit" value="Create Schedule">
            </form>
        </div>
        <div class="menu-item">
            <form action="viewSchedules" method="get">
                <input type="hidden" id="doctorId" name="doctorId" value="${id}" required>
                <input type="submit" value="View Schedules">
            </form>
        </div>
        <div class="menu-item">
              <form action="viewUpcomingSchedules" method="get">
                   <input type="hidden" id="doctorId" name="doctorId" value="${id}" required>
                   <input type="submit" value="View UpComing Schedules">
              </form>
        </div>
        <div class="menu-item">
            <form action="viewDoctorUpComingAppointments" method="get">
                <input type="hidden" id="doctorId" name="doctorId" value="${id}" required>
                <input type="submit" value="View Doctor Appointments">
            </form>
        </div>
      <!--  <div class="menu-item">
            <form action="changeAppointmentStatus" method="get">
                <input type="hidden" id="doctorId" name="doctorId" value="${id}" required>
                <input type="submit" value="Change Appointment Status">
            </form>
        </div> -->

    </div>
</body>
</html>
