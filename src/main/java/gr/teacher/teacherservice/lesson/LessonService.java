package gr.teacher.teacherservice.lesson;


import gr.teacher.teacherservice.extraLesson.ExtraLesson;
import gr.teacher.teacherservice.teacher.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class LessonService {

    @Autowired
    LessonDao lessonDao;

    public int createLesson(Lesson lesson) {
        if (isTimeAvailable(lesson)) {
            lessonDao.create(lesson);
            return 0;
        } else {
            return 1;
        }
    }

    public Boolean isTimeAvailable(Lesson lesson) {
        float startTime = (float) Integer.parseInt(lesson.getStartHour()) + ((float) Integer.parseInt(lesson.getStartMinute()) / 60);
        float endTime = (float) Integer.parseInt(lesson.getEndHour()) + ((float) Integer.parseInt(lesson.getEndMinute()) / 60);
        List<Lesson> lessonList = findAllLessonsByTeacher(lesson.getTeacher());
        for (Lesson l : lessonList) {
            if (lesson.getDay().equals(l.getDay())) {
                float tempStartTime = (float) Integer.parseInt(l.getStartHour()) + ((float) Integer.parseInt(l.getStartMinute()) / 60);
                float tempEndTime = (float) Integer.parseInt(l.getEndHour()) + ((float) Integer.parseInt(l.getEndMinute()) / 60);
                if ((startTime > tempStartTime && startTime < tempEndTime) || (endTime > tempStartTime && endTime < tempEndTime)
                        || (startTime <= tempStartTime && endTime >= tempEndTime)) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<Lesson> findAllLessonsByTeacher(Teacher teacher) {
        List<Lesson> lessonList = lessonDao.findAllLessonsByTeacher(teacher);
        Collections.sort(lessonList, new LessonComparator());
        return lessonList;
    }

    public void delete(Lesson lesson){

        lessonDao.setClazz(Lesson.class);
        lessonDao.delete(lesson.getId());
    }

}

