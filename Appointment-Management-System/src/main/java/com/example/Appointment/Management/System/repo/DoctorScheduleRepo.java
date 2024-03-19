package com.example.Appointment.Management.System.repo;

import com.example.Appointment.Management.System.entity.Doctor;
import com.example.Appointment.Management.System.entity.DoctorSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DoctorScheduleRepo extends JpaRepository<DoctorSchedule,Long> {
    List<DoctorSchedule> findByDoctorId(Long doctorId);

    List<DoctorSchedule> findByDoctorIdAndStartDateTimeLessThanEqualAndEndDateTimeGreaterThanEqual(Long id, LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<DoctorSchedule> findByDoctorIdAndStartDateTimeBetween(Long doctorId, LocalDateTime startOfDay, LocalDateTime endOfDay);
}
