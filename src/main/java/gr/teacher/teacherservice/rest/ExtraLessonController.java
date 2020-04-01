package gr.teacher.teacherservice.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.teacher.teacherservice.extraLesson.ExtraLesson;
import gr.teacher.teacherservice.extraLesson.ExtraLessonService;
import gr.teacher.teacherservice.lesson.Lesson;
import gr.teacher.teacherservice.teacher.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@Controller
@RequestMapping("extralesson")
public class ExtraLessonController {

    @Autowired
    private ExtraLessonService extraLessonService;

    @PostMapping("create")
    public ResponseEntity<String> createExtraLesson(@RequestBody ExtraLesson extraLesson) {
        System.out.println("extra controller");
        int response;
        try {
            response = extraLessonService.create(extraLesson);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create new extraLesson", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (response == 0)
            return new ResponseEntity<>("ExtraLesson created successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("This time is not available in your program(Extra Lesson)", HttpStatus.OK);

    }

    @GetMapping("getAll")
    public ResponseEntity<List<ExtraLesson>> getExtraLessons(@RequestParam String teacherString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Teacher teacher = mapper.readValue(teacherString, new TypeReference<Teacher>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        List<ExtraLesson> extraLessonList;

        try {
            extraLessonList = extraLessonService.findAllExtraLessonsByTeacher(teacher);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(extraLessonList, HttpStatus.OK);
    }

    @DeleteMapping("delete")
    public ResponseEntity<ExtraLesson> deleteExtraLesson(@RequestParam String extraLessonString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ExtraLesson extraLesson = mapper.readValue(extraLessonString, new TypeReference<ExtraLesson>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        System.out.println(extraLesson.getId());
        System.out.println(extraLesson.getCourse());
        System.out.println("ok");
        try {
            extraLessonService.delete(extraLesson);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(extraLesson, HttpStatus.OK);
    }
}
