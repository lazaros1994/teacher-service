package gr.teacher.teacherservice.cancelledLesson;

import gr.teacher.teacherservice.extraLesson.ExtraLesson;
import gr.teacher.teacherservice.extraLesson.ExtraLessonService;
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

    @Autowired
    ExtraLessonService extraLessonService;

    public void create(CancelledLesson cancelledLesson){
        cancelledLessonDao.create(cancelledLesson);
    }

    public List<CancelledLesson> getAll(Teacher teacher){
       return cancelledLessonDao.getAll(teacher);
    }
    public List<CancelledLesson> getAllCancelledByLesson(Lesson lesson){
       return cancelledLessonDao.getAllCancelledByLesson(lesson);
    }

    public Boolean delete(CancelledLesson cancelledLesson){
        /*να τσεκάρω εάν το μάθημα μπορεί να γίνει undo, δηλαδή αν υπάρχει κάποιο εξτρα lesson εκει, χρησιμοιώντας την fromCancelledtoExtraLesson για να τσεκάρω μετά σαν extra lesson*/
        if(extraLessonService.isTimeAvailable(fromCancelledtoExtraLesson(cancelledLesson))){
            cancelledLessonDao.setClazz(CancelledLesson.class);
            cancelledLessonDao.delete(cancelledLesson.getId());
            return true;
        }
        else{
            return false;
        }

    }

    public ExtraLesson fromCancelledtoExtraLesson(CancelledLesson cancelledLesson){
        ExtraLesson extraLesson = new ExtraLesson();
        extraLesson.setCourse(cancelledLesson.getLesson().getCourse());
        extraLesson.setDay(cancelledLesson.getDay());
        extraLesson.setEndHour(cancelledLesson.getLesson().getEndHour());
        extraLesson.setEndMinute(cancelledLesson.getLesson().getEndMinute());
        extraLesson.setStartHour(cancelledLesson.getLesson().getStartHour());
        extraLesson.setStartMinute(cancelledLesson.getLesson().getStartMinute());
        extraLesson.setEuroPerHour(cancelledLesson.getLesson().getEuroPerHour());
        extraLesson.setTeacher(cancelledLesson.getLesson().getTeacher());
        extraLesson.setYear(cancelledLesson.getYear());
        extraLesson.setMonth(cancelledLesson.getMonth());
        extraLesson.setStudentName(cancelledLesson.getLesson().getStudentName());
        extraLesson.setStudentSurname(cancelledLesson.getLesson().getStudentSurname());
        return extraLesson;
    }
}
