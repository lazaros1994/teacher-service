package gr.teacher.teacherservice.extraLesson;

import gr.teacher.teacherservice.Dao;
import gr.teacher.teacherservice.lesson.Lesson;
import gr.teacher.teacherservice.teacher.Teacher;

import java.util.List;

public interface ExtraLessonDao extends Dao<ExtraLesson> {
    List<ExtraLesson> findAllExtraLessonsByTeacher(Teacher teacher);
}
