package gr.Teacher.Teacherservice.teacher;


import gr.Teacher.Teacherservice.Dao;

public interface TeacherDao extends Dao<Teacher> {
    Teacher findByEmailAndPassword(String email, String password);
}
