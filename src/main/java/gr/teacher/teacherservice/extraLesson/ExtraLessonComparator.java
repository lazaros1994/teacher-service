package gr.teacher.teacherservice.extraLesson;

import gr.teacher.teacherservice.lesson.Lesson;

import java.util.Comparator;

public class ExtraLessonComparator implements Comparator<ExtraLesson> {

    public int compare(ExtraLesson l1, ExtraLesson l2) {
        int value1 = l1.getStartHour().compareTo(l2.getStartHour());
        if (value1 == 0) {
            return l1.getStartMinute().compareTo(l2.getStartMinute());
        }
        return value1;
    }

}
