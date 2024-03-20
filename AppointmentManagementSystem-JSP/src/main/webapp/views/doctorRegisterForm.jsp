<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Doctor Registration</title>
</head>
<body>
<h3 style="color: blue;">${msg}</h3>

    <h1>Doctor Registration</h1>
    <form:form action="registerDoctor" modelAttribute="doctorModel">

        <label>Username: </label>
        <form:input path="username"/><br>
        <form:errors path="username" style="color: red;"/><br>

        <label>Password:</label><br>
        <form:input path="password"/><br>
        <form:errors path="password" style="color: red;"/><br>

        <label>Name:</label><br>
        <form:input path="name"/><br>
        <form:errors path="name" style="color: red;"/><br>

        <label>Specialty:</label><br>
        <!-- <form:input path="specialty"/><br> -->
        <select id="specialty" name="specialty">
            <option ></option>
            <option value="RMP">RMP</option>
            <option value="DENTIST">DENTIST</option>
            <option value="General Practitioner">General Practitioner</option>
            <option value="Pediatrician">Pediatrician</option>
            <option value="Internal Medicine Physician">Internal Medicine Physician</option>
            <option value="Cardiologist">Cardiologist</option>
            <option value="Dermatologist">Dermatologist</option>
            <option value="Orthopedic Surgeon">Orthopedic Surgeon</option>
            <option value="Obstetrician-Gynecologist (OB-GYN)">Obstetrician-Gynecologist (OB-GYN)</option>
            <option value="Ophthalmologist">Ophthalmologist</option>
            <option value="ENT Specialist">ENT Specialist</option>
            <option value="Psychiatrist">Psychiatrist</option>
            <option value="Neurologist">Neurologist</option>
            <option value="Pulmonologist">Pulmonologist</option>
        </select>
        <form:errors path="specialty" style="color: red;"/><br><br>

        <label>Location:</label><br>
         <select id="location" name="location">
              <option></option>
             <option value="Nizamabad">Nizamabad</option>
             <option value="Hyderabad">Hyderabad</option>
             <option value="Karimnagar">Karimnagar</option>
             <option value="Vizag">Vizag</option>
             <option value="Bangalore">Bengalour</option>
        </select><br>

        <form:errors path="location" style="color: red;"/><br><br>

        <label>Hospital Name:</label><br>
        <!-- <form:input path="hospitalName"/><br> -->
         <select id="hospitalName" name="hospitalName">
             <option></option>
            <option value="Apollo Hospitals">Apollo Hospitals</option>
            <option value="Yashoda Hospitals">Yashoda Hospitals</option>
            <option value="Continental Hospitals">Continental Hospitals</option>
            <option value="Care Hospitals">Care Hospitals</option>
            <option value="KIMS Hospitals">KIMS Hospitals</option>
            <option value="Global Hospitals">Global Hospitals</option>
            <option value="Sunshine Hospitals">Sunshine Hospitals</option>
            <option value="Virinchi Hospitals">Virinchi Hospitals</option>
            <option value="Medicover Hospitals">Medicover Hospitals</option>

         </select><br>
        <form:errors path="hospitalName" style="color: red;"/><br>

        <label>Mobile No:</label><br>
        <form:input path="mobileNo"/><br>
        <form:errors path="mobileNo" style="color: red;"/><br>

        <label>Appointment Fee:</label><br>
          <form:input path="appointmentFee"/><br>
          <form:errors path="appointmentFee" style="color: red;"/><br>

        <input type="submit" value="Register">
    </form:form>
</body>
</html>
