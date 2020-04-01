package gr.teacher.teacherservice.extraLesson;

import gr.teacher.teacherservice.JpaDao;
import gr.teacher.teacherservice.lesson.Lesson;
import gr.teacher.teacherservice.teacher.Teacher;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ExtraLessonDaoImpl extends JpaDao<ExtraLesson> implements ExtraLessonDao {


    @PersistenceContext
    protected EntityManager em;

    public List<ExtraLesson> findAllExtraLessonsByTeacher(Teacher teacher) {

        return em.createQuery("select e from ExtraLesson e where e.teacher=:teacher", ExtraLesson.class)
                .setParameter("teacher", teacher)
                .getResultList();

    }
}
