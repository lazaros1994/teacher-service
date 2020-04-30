package gr.teacher.teacherservice.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.teacher.teacherservice.mail.MailService;
import gr.teacher.teacherservice.teacher.Teacher;
import gr.teacher.teacherservice.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.Type;

@Controller
@RequestMapping("teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private MailService mailService;

    @PostMapping("create")
    public ResponseEntity<String> createUser(@RequestParam String name, @RequestParam String surname, @RequestParam String email, @RequestParam String password) {
        int answer;

        try {
            answer = teacherService.createTeacher(name, surname, email, password);
        } catch (Exception e) {
            return new ResponseEntity<String>("Error in creating teacher", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (answer == 1)
            return new ResponseEntity<String>("This email is not available", HttpStatus.OK);
        else
            return new ResponseEntity<String>("Teacher created successfully", HttpStatus.OK);

    }

    @GetMapping("find")
    public ResponseEntity<Teacher> findTeacher(@RequestParam String email, @RequestParam String password) {
        Teacher teacher;
        try {
            teacher = teacherService.findTeacher(email, password);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @GetMapping("getHoursThisMonth")
    public ResponseEntity<float[]> getHoursThisMonth(@RequestParam String teacherString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Teacher teacher = mapper.readValue(teacherString, new TypeReference<Teacher>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        float[] hoursAndEuroThisMonth = new float[3];

        try {
            hoursAndEuroThisMonth = teacherService.getHoursAndEuroThisMonth(teacher);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(hoursAndEuroThisMonth, HttpStatus.OK);
    }

    @PutMapping("updatePersonalInfo")
    public ResponseEntity<Teacher> updatePersonalInfo(@RequestBody Teacher teacher) {
        try {
            teacher = teacherService.updateTeacher(teacher);
        } catch (Exception e) {
            return new ResponseEntity<>(teacher, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(teacher, HttpStatus.OK);

    }

    @GetMapping("forgotPassword")
    public ResponseEntity<Teacher> forgotPassword(@RequestParam String email){
        try{
            mailService.sendSimpleMessage(email);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

}
