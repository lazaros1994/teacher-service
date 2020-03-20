package gr.teacher.teacherservice.cancelledLesson;

import gr.teacher.teacherservice.JpaDao;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CancelledLessonDaoImpl extends JpaDao<CancelledLesson> implements CancelledLessonDao {

    @PersistenceContext
    EntityManager em;
}
