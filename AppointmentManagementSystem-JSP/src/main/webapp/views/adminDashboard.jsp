<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
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
        <h1>Admin Dashboard</h1>
        <div class="menu-item">

            <form action="doctorRegisterForm" >
            <input type="hidden" id="id" name="adminId" value="${id}" required>
             <input type="submit" value="addDoctor">
            </form>
        </div>
        <div class="menu-item">
            <form action="adminViewDoctors" method="get">
                <input type="submit" value="View Doctors">
            </form>
        </div>
        <div class="menu-item">
            <form action="adminViewPatients" method="get">

                <input type="submit" value="View Patients">
            </form>
        </div>
    </div>
</body>
</html>
