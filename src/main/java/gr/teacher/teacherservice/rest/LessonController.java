package gr.teacher.teacherservice.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.teacher.teacherservice.lesson.Lesson;
import gr.teacher.teacherservice.lesson.LessonService;
import gr.teacher.teacherservice.teacher.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@Controller
@RequestMapping("lesson")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @PostMapping("create")
    public ResponseEntity<String> createLesson(@RequestBody Lesson lesson) {
        int response;
        try {
            response = lessonService.createLesson(lesson);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create new lesson", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(response == 0)
            return new ResponseEntity<>("Lesson created successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("This time is not available in your program", HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Lesson>> getLessons(@RequestParam String teacherString) throws JsonProcessingException {
        System.out.println(teacherString);
        ObjectMapper mapper = new ObjectMapper();
        Teacher teacher = mapper.readValue(teacherString, new TypeReference<Teacher>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        List<Lesson> lessonList;

        try {
            lessonList = lessonService.findAllLessonsByTeacher(teacher);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(lessonList, HttpStatus.OK);
    }

}
