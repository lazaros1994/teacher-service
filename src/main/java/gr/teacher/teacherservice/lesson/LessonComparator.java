package gr.teacher.teacherservice.lesson;

import java.util.Comparator;

public class LessonComparator implements Comparator<Lesson> {
    public int compare(Lesson l1, Lesson l2) {
        int value1 = l1.getStartHour().compareTo(l2.getStartHour());
        if (value1 == 0) {
            return l1.getStartMinute().compareTo(l2.getStartMinute());
        }
        return value1;
    }
}

