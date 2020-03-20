package gr.teacher.teacherservice.rest;

import gr.teacher.teacherservice.extraLesson.ExtraLesson;
import gr.teacher.teacherservice.extraLesson.ExtraLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("extralesson")
public class ExtraLessonController {

    @Autowired
    private ExtraLessonService extraLessonService;

    @PostMapping("create")
    public ResponseEntity<String> createExtraLesson(@RequestBody ExtraLesson extraLesson) {
        System.out.println("eftase kai edw");
        try {
            extraLessonService.create(extraLesson);
        } catch (Exception e) {
            return new ResponseEntity<>("Error in creating extraLesson", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("ExtraLesson created successfully", HttpStatus.OK);

    }
}
