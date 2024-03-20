<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.AppointmentManagementSystemJSP.model.AppointmentModel" %>
<%@ page import="com.example.AppointmentManagementSystemJSP.entity.Appointment" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Appointment Details</title>
</head>
<body>
    <h1>Appointment Details</h1>
    <br><br>
   <h3 style="color: blue;">${msg}</h3>

    <div>
        <strong>ID :</strong> ${appointment.id}<br><br>
        <strong>Patient:</strong> ${appointment.patient.id}<br><br>
        <strong>DoctorName:</strong> ${appointment.doctor.name}<br><br>
        <strong>DoctorId:</strong> ${appointment.doctor.id}<br><br>
        <strong>Start Date Time :</strong> ${appointment.startDateTime}<br><br>
        <strong>End Date Time :</strong> ${appointment.endDateTime}<br><br>
        <strong>Type :</strong> ${appointment.type}<br><br>
        <strong>Status :</strong> ${appointment.status}<br><br>
        <strong>Payment :</strong> ${appointment.payment}<br><br>
    </div>
   <form action="/paymentDone" method="post">
       <input type="hidden" id="appointmentId" name="appointmentId" value="${appointment.id}">
       <input type="hidden" id="patientId" name="patientId" value="${patientId}">
       <label for="paymentMode">Payment Mode:</label>
       <select id="paymentMode" name="paymentMode">
           <option></option>
           <option value="creditCard">Credit Card</option>
           <option value="debitCard">Debit Card</option>
           <option value="netBanking">Net Banking</option>
           <option value="paypal">PayPal</option>
       </select>
      <button type="submit">Make Payment</button>

   </form>


</body>
</html>
