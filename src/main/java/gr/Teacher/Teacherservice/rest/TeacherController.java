package gr.Teacher.Teacherservice.rest;


import gr.Teacher.Teacherservice.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
            return new ResponseEntity<>("Failed to create new Teacher", HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<>("Teacher created", HttpStatus.OK);

    }

}
