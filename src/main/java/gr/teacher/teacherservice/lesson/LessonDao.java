package gr.teacher.teacherservice.lesson;

import gr.teacher.teacherservice.Dao;
import gr.teacher.teacherservice.teacher.Teacher;

import java.util.List;


public interface LessonDao extends Dao<Lesson> {
    List<Lesson> findAllLessonsByTeacher(Teacher teacher);
}
