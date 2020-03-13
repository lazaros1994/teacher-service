package gr.teacher.teacherservice.teacher;


import gr.teacher.teacherservice.Dao;

public interface TeacherDao extends Dao<Teacher> {
    Teacher findByEmailAndPassword(String email, String password);
}
