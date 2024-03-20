<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Choose Specialty and Location</title>

</head>
<body>
    <div class="container">
        <h2>Choose Specialty and Location</h2>
        <form action="viewDoctorsBasedOnSpecialtyAndLocation" >
        <input type="hidden" id="patientId" name="patientId" value="${patientId}" required>
            <div class="form-group">
                <label for="specialty">Select Doctor Specialty:</label>

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
            </div><br><br>

            <!-- Dropdown for Location -->
            <div class="form-group">
                <label for="location">Select Location:</label>
                <label for="location">Select Location:</label>
                                <select id="location" name="location">
                                    <option value="Nizamabad">Nizamabad</option>
                                    <option value="Hyderabad">Hyderabad</option>
                                    <option value="Karimnagar">Karimnagar</option>
                                    <option value="Vizag">Vizag</option>
                                    <option value="Bangalore">Bengalour</option>
                                </select>


            </div>


            <input type="hidden" id="doctorId" name="doctorId" value="${id}" required>
            <button type="submit">View Doctors</button>
        </form>
    </div>
</body>
</html>
