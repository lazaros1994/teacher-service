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

    public Teacher findByEmailAndPassword(String email, String password){

        Teacher teacher = em.createQuery("select t from Teacher t where t.email=:email and t.password=:password ", Teacher.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getSingleResult();
        return teacher;

    }

}
