package gr.teacher.teacherservice.cancelledLesson;

import gr.teacher.teacherservice.extraLesson.ExtraLesson;
import gr.teacher.teacherservice.lesson.Lesson;
import gr.teacher.teacherservice.teacher.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CancelledLessonService {

    @Autowired
    CancelledLessonDao cancelledLessonDao;

    public void create(CancelledLesson cancelledLesson){
        cancelledLessonDao.create(cancelledLesson);
    }

    public List<CancelledLesson> getAll(Teacher teacher){
       return cancelledLessonDao.getAll(teacher);
    }
    public List<CancelledLesson> getAllCancelledByLesson(Lesson lesson){
       return cancelledLessonDao.getAllCancelledByLesson(lesson);
    }

    public void delete(CancelledLesson cancelledLesson){
        cancelledLessonDao.setClazz(CancelledLesson.class);
        cancelledLessonDao.delete(cancelledLesson.getId());
    }
}
