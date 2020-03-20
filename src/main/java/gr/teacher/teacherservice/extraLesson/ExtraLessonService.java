package gr.teacher.teacherservice.extraLesson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ExtraLessonService {

    @Autowired
    ExtraLessonDao extraLessonDao;

    public void create(ExtraLesson extraLesson){
        extraLessonDao.create(extraLesson);
    }
}
