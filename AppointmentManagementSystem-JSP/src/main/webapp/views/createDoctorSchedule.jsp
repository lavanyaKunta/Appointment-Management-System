<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Schedule</title>
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
        <h2>Create Schedule</h2>
         <h3  style="color: red">${msg}</h3>
        <form:form action="submitSchedule" modelAttribute="doctorScheduleModel">
             <div class="form-group">
                  <input id="doctor" name="doctor" value="${id}" required>
             </div>

             <div class="form-group">
                <label for="startDateTime">StartDateTime:</label>
                 <form:input path="startDateTime" type="datetime-local" id="startDateTime" name="startDateTime" /><br>
                 <form:errors path="startDateTime" style="color: red;"/><br>
            </div>

            <div class="form-group">
                <label for="endDateTime">EndDateTime:</label>
                <form:input path="endDateTime" type="datetime-local" id="endDateTime" name="endDateTime" /><br>
                <form:errors path="endDateTime" style="color: red;"/><br>
            </div>

            <div class="form-group">
                <input type="submit" value="Create Schedule">
            </div>
        </form:form>
    </div>
</body>
</html>
