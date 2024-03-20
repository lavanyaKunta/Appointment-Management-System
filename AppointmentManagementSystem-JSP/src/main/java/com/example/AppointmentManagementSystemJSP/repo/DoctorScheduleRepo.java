package com.example.AppointmentManagementSystemJSP.repo;
import com.example.AppointmentManagementSystemJSP.entity.DoctorSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface DoctorScheduleRepo extends JpaRepository<DoctorSchedule,Long> {
    List<DoctorSchedule> findByDoctorIdAndStartDateTimeLessThanEqualAndEndDateTimeGreaterThanEqual(Long doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<DoctorSchedule> findByDoctorId(Long doctorId);


    DoctorSchedule getScheduleById(Long scheduleId);



    List<DoctorSchedule> findAllSchedulesByDoctorId(Long doctorId);

    DoctorSchedule findByDoctorIdAndStartDateTimeAndEndDateTime(Long id, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
