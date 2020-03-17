package gr.teacher.teacherservice.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional

public class TeacherService {

    @Autowired
    TeacherDao teacherDao;

    public int createTeacher(String name, String surname, String email, String password){
        System.out.println(isEmailAvailable(email));
        if(!isEmailAvailable(email))
            return 1;
        teacherDao.create(new Teacher(name,surname,email,password));
        return 0;
    }

    public Boolean isEmailAvailable(String email){
        return teacherDao.isEmailAvailable(email);
    }

    public Teacher findTeacher(String email, String password){
        return teacherDao.findByEmailAndPassword(email, password);
    }

}
