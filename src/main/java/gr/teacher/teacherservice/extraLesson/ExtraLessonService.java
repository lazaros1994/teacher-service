package gr.teacher.teacherservice.extraLesson;

import gr.teacher.teacherservice.cancelledLesson.CancelledLesson;
import gr.teacher.teacherservice.cancelledLesson.CancelledLessonService;
import gr.teacher.teacherservice.lesson.Lesson;
import gr.teacher.teacherservice.lesson.LessonComparator;
import gr.teacher.teacherservice.lesson.LessonService;
import gr.teacher.teacherservice.teacher.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class ExtraLessonService {

    @Autowired
    ExtraLessonDao extraLessonDao;

    @Autowired
    LessonService lessonService;

    @Autowired
    CancelledLessonService cancelledLessonService;

    public int create(ExtraLesson extraLesson){

        if (isTimeAvailable(extraLesson) && isTimeAvailableForLessons(extraLesson)) {
            extraLessonDao.create(extraLesson);
            return 0;
        } else {
            return 1;
        }
    }

    public void delete(ExtraLesson extraLesson){
        extraLessonDao.setClazz(ExtraLesson.class);
        extraLessonDao.delete(extraLesson.getId());
    }

    Boolean isTimeAvailable(ExtraLesson extraLesson) {
        float startTime = (float) Integer.parseInt(extraLesson.getStartHour()) + ((float) Integer.parseInt(extraLesson.getStartMinute()) / 60);
        float endTime = (float) Integer.parseInt(extraLesson.getEndHour()) + ((float) Integer.parseInt(extraLesson.getEndMinute()) / 60);
        List<ExtraLesson> extraLessonList = findAllExtraLessonsByTeacher(extraLesson.getTeacher());
        for (ExtraLesson e : extraLessonList) {
            if (extraLesson.getDay() == (e.getDay()) && extraLesson.getMonth() == (e.getMonth()) && extraLesson.getYear() == (e.getYear())) {
                float tempStartTime = (float) Integer.parseInt(e.getStartHour()) + ((float) Integer.parseInt(e.getStartMinute()) / 60);
                float tempEndTime = (float) Integer.parseInt(e.getEndHour()) + ((float) Integer.parseInt(e.getEndMinute()) / 60);
                if ((startTime > tempStartTime && startTime < tempEndTime) || (endTime > tempStartTime && endTime < tempEndTime)
                        || (startTime <= tempStartTime && endTime >= tempEndTime)) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<ExtraLesson> findAllExtraLessonsByTeacher(Teacher teacher) {
        List<ExtraLesson> extraLessonList = extraLessonDao.findAllExtraLessonsByTeacher(teacher);

        Collections.sort(extraLessonList, new ExtraLessonComparator());
        return extraLessonList;
    }

    public Lesson lessonFromExtraLesson(ExtraLesson extraLesson){
        Lesson lesson = new Lesson();
        lesson.setCourse(extraLesson.getCourse());
        Date date = new Date(extraLesson.getYear(), extraLesson.getMonth(), extraLesson.getDay() -1);
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("E", Locale.ENGLISH); // the day of the week abbreviated
        lesson.setDay(simpleDateformat.format(date));
        lesson.setEndHour(extraLesson.getEndHour());
        lesson.setEndMinute(extraLesson.getEndMinute());
        lesson.setStartHour(extraLesson.getStartHour());
        lesson.setStartMinute(extraLesson.getStartMinute());
        lesson.setEuroPerHour(extraLesson.getEuroPerHour());
        lesson.setStudentName(extraLesson.getStudentName());
        lesson.setStudentSurname(extraLesson.getStudentSurname());
        lesson.setTeacher(extraLesson.getTeacher());
        System.out.println(lesson);
        return lesson;
    }

    public Boolean isTimeAvailableForLessons(ExtraLesson extraLesson) {
        System.out.println("lesson");
        float startTime = (float) Integer.parseInt(extraLesson.getStartHour()) + ((float) Integer.parseInt(extraLesson.getStartMinute()) / 60);
        float endTime = (float) Integer.parseInt(extraLesson.getEndHour()) + ((float) Integer.parseInt(extraLesson.getEndMinute()) / 60);
        List<Lesson> lessonList = lessonService.findAllLessonsByTeacher(extraLesson.getTeacher());
        List<CancelledLesson> cancelledLessonList = cancelledLessonService.getAll(extraLesson.getTeacher());
        System.out.println("akurwmena mathimata:" + cancelledLessonList);
        for (Lesson l : lessonList) {
            System.out.println(extraLesson.getDay());
            System.out.println(l.getDay());
            Date date = new Date(extraLesson.getYear(), extraLesson.getMonth(), extraLesson.getDay() -1);
            SimpleDateFormat simpleDateformat = new SimpleDateFormat("E", Locale.ENGLISH); // the day of the week abbreviated
            if (simpleDateformat.format(date).equals(l.getDay())) {
                float tempStartTime = (float) Integer.parseInt(l.getStartHour()) + ((float) Integer.parseInt(l.getStartMinute()) / 60);
                float tempEndTime = (float) Integer.parseInt(l.getEndHour()) + ((float) Integer.parseInt(l.getEndMinute()) / 60);
                if ((startTime > tempStartTime && startTime < tempEndTime) || (endTime > tempStartTime && endTime < tempEndTime)
                        || (startTime <= tempStartTime && endTime >= tempEndTime)) {
                   for (CancelledLesson cancelledLesson: cancelledLessonList){
                       if (cancelledLesson.getLesson().getId() == l.getId()){
                           if(cancelledLesson.getDay() == extraLesson.getDay() && cancelledLesson.getMonth() == extraLesson.getMonth()
                           && cancelledLesson.getYear() == extraLesson.getYear()){
                                return true;
                           }
                       }
                   }

                    return false;
                }
            }
        }
        return true;
    }
}
