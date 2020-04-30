package gr.teacher.teacherservice.mail;

import gr.teacher.teacherservice.teacher.Teacher;
import gr.teacher.teacherservice.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    private TeacherService teacherService;

    public void sendSimpleMessage(String email) {

        Teacher teacher = teacherService.findTeacherByEmail(email);

        if (teacher != null) {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(email);
            mail.setSubject("Teacher Organiser Επανάκτηση κωδικού");
            mail.setText("Ο κωδικός είναι: " + teacher.getPassword());

            emailSender.send(mail);
            System.out.println("to mail estalh");
        } else {
            throw new IllegalArgumentException("There is no found teacher with this email.");
        }
    }
}
