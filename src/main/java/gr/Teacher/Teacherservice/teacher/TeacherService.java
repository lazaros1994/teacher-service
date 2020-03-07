package gr.Teacher.Teacherservice.teacher;

import gr.Teacher.Teacherservice.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional

public class TeacherService {

    @Autowired
    TeacherDao teacherDao;

//    @Autowired
//    public void setDao(Dao<Teacher> daoToSet) {
//        dao = daoToSet;
//        dao.setClazz(Teacher.class);
//    }

    public void createTeacher(String name, String surname, String email, String password){
        teacherDao.create(new Teacher(name,surname,email,password));
    }

    public Teacher findTeacher(String email, String password){
        return teacherDao.findByEmailAndPassword(email, password);
    }

}
