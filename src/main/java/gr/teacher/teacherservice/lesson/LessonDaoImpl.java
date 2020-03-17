package gr.teacher.teacherservice.lesson;

import gr.teacher.teacherservice.JpaDao;
import gr.teacher.teacherservice.teacher.Teacher;
import gr.teacher.teacherservice.teacher.TeacherDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class LessonDaoImpl extends JpaDao<Lesson> implements LessonDao {

    @PersistenceContext
    protected EntityManager em;

    public List<Lesson> findAllLessonsByTeacher(Teacher teacher) {

        return em.createQuery("select l from Lesson l where l.teacher=:teacher", Lesson.class)
                .setParameter("teacher", teacher)
                .getResultList();

    }
}
