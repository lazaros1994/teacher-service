package gr.teacher.teacherservice.cancelledLesson;

import gr.teacher.teacherservice.JpaDao;
import gr.teacher.teacherservice.extraLesson.ExtraLesson;
import gr.teacher.teacherservice.teacher.Teacher;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CancelledLessonDaoImpl extends JpaDao<CancelledLesson> implements CancelledLessonDao {

    @PersistenceContext
    EntityManager em;

    public List<CancelledLesson> getAll(Teacher teacher){
        return em.createQuery("select c from CancelledLesson c where c.lesson.teacher =: teacher", CancelledLesson.class)
                .setParameter("teacher", teacher)
                .getResultList();
    }}
