package com.tvz.diplomski.wisey.Appointment;

import com.tvz.diplomski.wisey.Appointment.model.AppointmentDTO;
import com.tvz.diplomski.wisey.Appointment.model.FlowChartDTO;
import com.tvz.diplomski.wisey.AppointmentStatus.AppointmentStatus;
import com.tvz.diplomski.wisey.AppointmentStatus.AppointmentStatusRepository;
import com.tvz.diplomski.wisey.Student.Student;
import com.tvz.diplomski.wisey.Student.StudentRepository;
import com.tvz.diplomski.wisey.Subject.Subject;
import com.tvz.diplomski.wisey.Subject.SubjectRepository;
import com.tvz.diplomski.wisey.Volunteer.Volunteer;
import com.tvz.diplomski.wisey.Volunteer.VolunteerRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Data
public class AppointmentServiceImpl implements AppointmentService{

    private final AppointmentRepository appointmentRepository;
    private final SubjectRepository subjectRepository;
    private final AppointmentStatusRepository appointmentStatusRepository;
    private final VolunteerRepository volunteerRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, SubjectRepository subjectRepository, AppointmentStatusRepository appointmentStatusRepository, VolunteerRepository volunteerRepository, StudentRepository studentRepository) {
        this.appointmentRepository = appointmentRepository;
        this.subjectRepository = subjectRepository;
        this.appointmentStatusRepository = appointmentStatusRepository;
        this.volunteerRepository = volunteerRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<AppointmentDTO> findAll() {
        return appointmentRepository.findAll().stream().map(this::mapAppointmentToDTO).collect(Collectors.toList());
    }

    @Override
    public FlowChartDTO findAllData() throws ParseException {
        List<AppointmentDTO> appointments = appointmentRepository.findAll().stream().map(this::mapAppointmentToDTO).collect(Collectors.toList());

        return sortData(appointments);
    }

    @Override
    public Optional<AppointmentDTO> create(AppointmentRequest appointmentRequest) {
        Appointment appointment = null;
        try {
            appointment = mapRequestToAppointment(appointmentRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.of(appointmentRepository.save(appointment)).map(this::mapAppointmentToDTO);
    }

    @Override
    public void deleteById(Long idAppointment) {
        appointmentRepository.deleteById(idAppointment);
    }

    private AppointmentDTO mapAppointmentToDTO(Appointment appointment) {

        Student student = appointment.getStudents().get(0);
        String dates[] = takeDates(appointment);
        String whichSchool = "";
        if(student.getSchool().startsWith("OŠ") || student.getSchool().startsWith("SŠ")) {
            whichSchool = student.getSchool().substring(0,2);
        }
        String studentInfo = student.getLastName()
                            + " " + student.getFirstName()
                            + ", " + student.getGrade() + ". razred " + whichSchool;
        String volunteerInfo = appointment.getVolunteer().getFirstName()
                                + " " + appointment.getVolunteer().getLastName();

        return new AppointmentDTO(
                appointment.getIdAppointment(),
                dates[0],
                dates[1],
                dates[2],
                studentInfo,
                appointment.getReservedHours(),
                appointment.getSubject().getSubjectName(),
                volunteerInfo
        );
    }

    private Appointment mapRequestToAppointment(AppointmentRequest appointmentRequest) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar dateFrom = Calendar.getInstance();
        Calendar dateTo = Calendar.getInstance();

        try {
            dateFrom.setTime(sdf.parse(appointmentRequest.getDateFrom()));
            dateTo.setTime(sdf.parse(appointmentRequest.getDateTo()));
        } catch (Exception e) {
            e.printStackTrace();
        }


        Subject subject = subjectRepository.findByIdSubject(appointmentRequest.getIdSubject());
        AppointmentStatus appointmentStatus = appointmentStatusRepository.findByIdAppointmentStatus(appointmentRequest.getIdAppointmentStatus());
        Volunteer volunteer = volunteerRepository.findByIdVolunteer(appointmentRequest.getIdVolunteer());
        List<Student> students = studentRepository.findAllByIdStudent(appointmentRequest.getIdStudent());

        return new Appointment(
                appointmentRequest.getIdAppointment(),
                dateFrom,
                dateTo,
                subject,
                appointmentStatus,
                volunteer,
                students,
                appointmentRequest.getReservedHours()
        );
    }

    private String[] takeDates(Appointment appointment) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        appointment.getFrom().add(Calendar.HOUR_OF_DAY,-1);
        appointment.getTo().add(Calendar.HOUR_OF_DAY,-1);

        Date calendarFrom = appointment.getFrom().getTime();
        Date calendarTo = appointment.getTo().getTime();

        String appTmpDateFrom = sdf.format(calendarFrom);
        String appTmpDateTo = sdf.format(calendarTo);

        String appointmentDate = appTmpDateFrom.substring(0,10);
        String timeFrom = appTmpDateFrom.substring(10,16);
        String timeTo = appTmpDateTo.substring(10,16);

        return new String[] {appointmentDate, timeFrom, timeTo};
    }

    private FlowChartDTO sortData(List<AppointmentDTO> appointments) throws ParseException {

        Date dateField[] = new Date[appointments.size()];
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        
        for(int i=0; i<appointments.size(); i++) {
            dateField[i] = sdf.parse(appointments.get(i).getAppointmentDate());
        }
        Arrays.sort(dateField);

        ArrayList<String> tmpDates = new ArrayList<>();
        for(int i=0; i<dateField.length; i++) {
            tmpDates.add(sdf.format(dateField[i]));
        }

        ArrayList<Integer> numberOfStudents = new ArrayList<>();
        ArrayList<String> dates = new ArrayList<>();
        int counter = 0;

        for(int i=0; i<tmpDates.size(); i++) {
            if(!dates.contains(tmpDates.get(i))) {
                dates.add(tmpDates.get(i));
            }
        }

        for(int i=0; i<dates.size(); i++) {
            for(int j=0; j<appointments.size();j++) {
                if(appointments.get(j).getAppointmentDate().equals(dates.get(i))) {
                    counter ++;
                }
            }
            numberOfStudents.add(counter);
            counter = 0;
        }

        return new FlowChartDTO(
                dates,
                numberOfStudents
        );
    }
}
