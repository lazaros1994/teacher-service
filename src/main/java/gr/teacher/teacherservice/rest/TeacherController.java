package gr.teacher.teacherservice.rest;


import gr.teacher.teacherservice.teacher.Teacher;
import gr.teacher.teacherservice.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("create")
    public ResponseEntity<String> createUser(@RequestParam String name, @RequestParam String surname, @RequestParam String email, @RequestParam String password) {

        try {
            teacherService.createTeacher(name, surname, email, password);
        } catch (Exception e) {
            return new ResponseEntity<String>("Error in creating teacher", HttpStatus.INTERNAL_SERVER_ERROR);
        }

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
        System.out.println(teacher.getName());
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

}
