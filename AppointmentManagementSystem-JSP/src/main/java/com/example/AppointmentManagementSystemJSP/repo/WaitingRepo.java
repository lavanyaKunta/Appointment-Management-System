package com.example.AppointmentManagementSystemJSP.repo;

import com.example.AppointmentManagementSystemJSP.entity.WaitingAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
@Repository
public interface WaitingRepo extends JpaRepository<WaitingAppointment,Long> {
    @Query("SELECT wa FROM WaitingAppointment wa WHERE wa.doctor.id = :doctorId " +
            "AND wa.startDateTime = :startDateTime AND wa.endDateTime = :endDateTime " +
            "ORDER BY wa.id ASC")
    WaitingAppointment findFirstByDoctorIdAndStartDateTimeAndEndDateTime(
            @Param("doctorId") Long doctorId,
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime
    );
//    WaitingAppointment findByDoctorIdAndStartDateTimeAndEndDateTime(Long id, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
