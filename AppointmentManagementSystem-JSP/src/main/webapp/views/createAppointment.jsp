<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Appointment</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        .container {
            max-width: 500px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            font-weight: bold;
        }

        input[type="datetime-local"],
        input[type="number"] {
            width: 100%;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        input[type="submit"] {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Create Appointment</h2>
         <h3  style="color: red">${msg}</h3>
        <form:form action="submitAppointment" modelAttribute="appointmentModel">


             <div class="form-group">
                  <input  type="hidden" id="doctor" name="doctor" value="${doctorId}" required>
             </div>

              <div class="form-group">
                  <input type="hidden" id="patient" name="patient" value="${patientId}" required>
              </div>

               <div class="form-group">
                  <input type="hidden" id="scheduleId" name="id" value="${scheduleId}" required>
               </div>

               <label for="type">Appointment Type:</label>

                   <select id="type" name="type">
                                      <option></option>
                                      <option value="CONSULTATION"> CONSULTATION</option>
                                      <option value="FOLLOW_UP">FOLLOW_UP</option>
                                      <option value="ROUTINE_CHECKUP">ROUTINE_CHECKUP</option>
                                      <option value="DIAGNOSTIC_TEST">DIAGNOSTIC_TEST</option>
                                      <option value="SURGERY">SURGERY</option>
                                      <option value="OTHER">OTHER</option>
                                      </select><br><br>

              <label for="status">Appointment Status:</label>
               <input type="text" id="status" name="status" value="REQUESTED" readonly><br><br>


            <div class="form-group">
                <input  type="hidden" id="doctor" name="doctor" value="${doctorId}" required>
                <input type="hidden" id="patient" name="patient" value="${patientId}" required>
                <input type="hidden" id="scheduleId" name="id" value="${scheduleId}" required>
                <input type="submit" value="Create Schedule">
            </div>
        </form:form>
    </div>
</body>
</html>
