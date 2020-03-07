package gr.Teacher.Teacherservice.teacher;

import gr.Teacher.Teacherservice.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional

public class TeacherService {


    @Autowired
    private Dao<Teacher> dao;

    @Autowired
    public void setDao(Dao<Teacher> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Teacher.class);
    }

    public void createTeacher(String name, String surname, String email, String password){
//        teacherDao.create(name,surname,email,password);
        System.out.println("edw1");
        Teacher teacher = new Teacher(name,surname,email,password);
        System.out.println("edw2");
        dao.create(teacher);
        System.out.println("edw3");
    }

}
