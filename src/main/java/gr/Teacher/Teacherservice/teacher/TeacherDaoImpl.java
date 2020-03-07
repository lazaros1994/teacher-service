package gr.Teacher.Teacherservice.teacher;


import gr.Teacher.Teacherservice.Dao;
import gr.Teacher.Teacherservice.JpaDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Repository
public class TeacherDaoImpl extends JpaDao<Teacher> implements TeacherDao {

    @PersistenceContext
    protected EntityManager em;

    public void hello(){
        System.out.println("hello");
    }
}
