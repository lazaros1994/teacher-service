package gr.teacher.teacherservice.extraLesson;

import gr.teacher.teacherservice.JpaDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class ExtraLessonDaoImpl extends JpaDao<ExtraLesson> implements ExtraLessonDao {


    @PersistenceContext
    protected EntityManager em;
}
