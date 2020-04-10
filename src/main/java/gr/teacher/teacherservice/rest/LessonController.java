package gr.teacher.teacherservice.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.teacher.teacherservice.cancelledLesson.CancelledLesson;
import gr.teacher.teacherservice.cancelledLesson.CancelledLessonService;
import gr.teacher.teacherservice.extraLesson.ExtraLesson;
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

    @Autowired
    private CancelledLessonService cancelledLessonService;

    @PostMapping("create")
    public ResponseEntity<String> createLesson(@RequestBody Lesson lesson) {
        int response;
        try {
            response = lessonService.createLesson(lesson);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create new lesson", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (response == 0)
            return new ResponseEntity<>("Lesson created successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("This time is not available in your program", HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Lesson>> getLessons(@RequestParam String teacherString) throws JsonProcessingException {
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

    @PostMapping("cancel")
    ResponseEntity<String> createCancelledLesson(@RequestBody CancelledLesson cancelledLesson) {
        try {
            cancelledLessonService.create(cancelledLesson);
        } catch (Exception e){
            return new ResponseEntity<>("Error in creating new Cancelled lesson", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Successfully created new Cancelled lesson", HttpStatus.OK);
    }

    @GetMapping("getAllCancelled")
    ResponseEntity<List<CancelledLesson>> getAllCancelledLessons(@RequestParam String teacherString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Teacher teacher = mapper.readValue(teacherString, new TypeReference<Teacher>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        List<CancelledLesson> cancelledLessonList;

        try {
            cancelledLessonList = cancelledLessonService.getAll(teacher);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(cancelledLessonList, HttpStatus.OK);
    }

    @DeleteMapping("deleteCancelled")
    public ResponseEntity<CancelledLesson> deleteExtraLesson(@RequestParam String lessonString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        CancelledLesson cancelledLesson = mapper.readValue(lessonString, new TypeReference<CancelledLesson>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });

        try {
            cancelledLessonService.delete(cancelledLesson);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(cancelledLesson, HttpStatus.OK);
    }

    @DeleteMapping("delete")
    public ResponseEntity<Lesson> deleteLesson(@RequestParam String lessonString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Lesson lesson = mapper.readValue(lessonString, new TypeReference<Lesson>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        try {
            List<CancelledLesson> cancelledLessonList = cancelledLessonService.getAllCancelledByLesson(lesson);
            for(CancelledLesson cancelledLesson: cancelledLessonList){
                cancelledLessonService.delete(cancelledLesson);
            }
            lessonService.delete(lesson);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(lesson, HttpStatus.OK);
    }

}
