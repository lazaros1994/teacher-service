package gr.teacher.teacherservice.teacher;


import gr.teacher.teacherservice.JpaDao;
import gr.teacher.teacherservice.lesson.Lesson;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    public Boolean isEmailAvailable(String email) {
        List<Teacher> teacherList = em.createQuery("select t from Teacher t where t.email=:email", Teacher.class)
                .setParameter("email", email)
                .getResultList();

        return teacherList.isEmpty();
    }
}
