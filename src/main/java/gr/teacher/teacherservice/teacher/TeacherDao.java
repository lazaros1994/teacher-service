package gr.teacher.teacherservice.teacher;

import gr.teacher.teacherservice.Dao;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherDao extends Dao<Teacher> {
    Teacher findByEmailAndPassword(String email, String password);
    Boolean isEmailAvailable(String email);
}
