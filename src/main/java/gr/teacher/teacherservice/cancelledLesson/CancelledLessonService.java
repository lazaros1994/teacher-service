package gr.teacher.teacherservice.cancelledLesson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CancelledLessonService {

    @Autowired
    CancelledLessonDao cancelledLessonDao;

    public void create(CancelledLesson cancelledLesson){
        cancelledLessonDao.create(cancelledLesson);
    }
}
