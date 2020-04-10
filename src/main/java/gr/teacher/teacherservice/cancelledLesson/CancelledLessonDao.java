package gr.teacher.teacherservice.cancelledLesson;

import gr.teacher.teacherservice.Dao;
import gr.teacher.teacherservice.lesson.Lesson;
import gr.teacher.teacherservice.teacher.Teacher;

import java.util.List;

public interface CancelledLessonDao extends Dao<CancelledLesson> {
    List<CancelledLesson> getAll(Teacher teacher);

    List<CancelledLesson> getAllCancelledByLesson(Lesson lesson);
}
