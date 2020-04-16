package gr.teacher.teacherservice.teacher;

import gr.teacher.teacherservice.cancelledLesson.CancelledLesson;
import gr.teacher.teacherservice.cancelledLesson.CancelledLessonDao;
import gr.teacher.teacherservice.extraLesson.ExtraLesson;
import gr.teacher.teacherservice.extraLesson.ExtraLessonDao;
import gr.teacher.teacherservice.lesson.Lesson;
import gr.teacher.teacherservice.lesson.LessonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.*;

@Service
@Transactional

public class TeacherService {

    @Autowired
    TeacherDao teacherDao;

    @Autowired
    LessonDao lessonDao;

    @Autowired
    ExtraLessonDao extraLessonDao;

    @Autowired
    CancelledLessonDao cancelledLessonDao;

    public int createTeacher(String name, String surname, String email, String password) {
        System.out.println(isEmailAvailable(email));
        if (!isEmailAvailable(email))
            return 1;
        teacherDao.create(new Teacher(name, surname, email, password));
        return 0;
    }

    public Boolean isEmailAvailable(String email) {
        return teacherDao.isEmailAvailable(email);
    }

    public Teacher findTeacher(String email, String password) {
        return teacherDao.findByEmailAndPassword(email, password);
    }

    public Teacher updateTeacher(Teacher teacher) {

        teacherDao.update(teacher);
        teacherDao.setClazz(Teacher.class);

        return teacherDao.findOne(teacher.getId());
    }

    public float[] getHoursAndEuroThisMonth(Teacher teacher) {
        float[] hoursAndEuroThisMonth = new float[3];

        float hours = 0;
        float euro = 0;
        float euroPerHour = 0;

        List<Lesson> lessonList = lessonDao.findAllLessonsByTeacher(teacher);
        List<ExtraLesson> extraLessonList = extraLessonDao.findAllExtraLessonsByTeacher(teacher);
        List<CancelledLesson> cancelledLessonList = cancelledLessonDao.getAll(teacher);

        Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);

        YearMonth yearMonthObject = YearMonth.of(year, month + 1);
        int daysInMonth = yearMonthObject.lengthOfMonth();

        List<Date> dateList = new ArrayList<Date>();

        for (int i = 1; i <= daysInMonth; i++) {
            c.set(year, month, i, 0, 0);
            dateList.add(c.getTime());
        }
        for (Date date : dateList) {                                     //gia kathe mera tou mhna
            SimpleDateFormat formatter = new SimpleDateFormat("EEE");

            for (Lesson lesson : lessonList) {
                if (lesson.getDay().equals(formatter.format(date))) {
                    hours = hours + (((float) Integer.parseInt(lesson.getEndHour()) + ((float) Integer.parseInt(lesson.getEndMinute()) / 60)) -
                            ((float) Integer.parseInt(lesson.getStartHour()) + ((float) Integer.parseInt(lesson.getStartMinute()) / 60)));
                    euro = euro + lesson.getEuroPerHour() * (((float) Integer.parseInt(lesson.getEndHour()) + ((float) Integer.parseInt(lesson.getEndMinute()) / 60)) -
                            ((float) Integer.parseInt(lesson.getStartHour()) + ((float) Integer.parseInt(lesson.getStartMinute()) / 60)));
                    for (CancelledLesson cancelledLesson : cancelledLessonList) {

                        if (lesson.getId() == cancelledLesson.getLesson().getId() && cancelledLesson.getYear() == (1900 + date.getYear()) && cancelledLesson.getMonth() == date.getMonth()
                                && cancelledLesson.getDay() == date.getDate()) {
                            hours = hours - (((float) Integer.parseInt(lesson.getEndHour()) + ((float) Integer.parseInt(lesson.getEndMinute()) / 60)) -
                                    ((float) Integer.parseInt(lesson.getStartHour()) + ((float) Integer.parseInt(lesson.getStartMinute()) / 60)));
                            euro = euro - lesson.getEuroPerHour() * (((float) Integer.parseInt(lesson.getEndHour()) + ((float) Integer.parseInt(lesson.getEndMinute()) / 60)) -
                                    ((float) Integer.parseInt(lesson.getStartHour()) + ((float) Integer.parseInt(lesson.getStartMinute()) / 60)));
                        }
                    }
                }
            }

            for (ExtraLesson extraLesson : extraLessonList) {
                if (extraLesson.getYear() == 1900 + date.getYear() && extraLesson.getMonth() == date.getMonth() && extraLesson.getDay() == date.getDate()) {
                    hours = hours + (((float) Integer.parseInt(extraLesson.getEndHour()) + ((float) Integer.parseInt(extraLesson.getEndMinute()) / 60)) -
                            ((float) Integer.parseInt(extraLesson.getStartHour()) + ((float) Integer.parseInt(extraLesson.getStartMinute()) / 60)));
                    euro = euro + extraLesson.getEuroPerHour() * (((float) Integer.parseInt(extraLesson.getEndHour()) + ((float) Integer.parseInt(extraLesson.getEndMinute()) / 60)) -
                            ((float) Integer.parseInt(extraLesson.getStartHour()) + ((float) Integer.parseInt(extraLesson.getStartMinute()) / 60)));
                }
            }
        }
        DecimalFormat df = new DecimalFormat("#.##");
        hours = Float.parseFloat(df.format(hours));
        euro = Float.parseFloat(df.format(euro));
        euroPerHour = Float.parseFloat(df.format(euro / hours));
        hoursAndEuroThisMonth[0] = hours;
        hoursAndEuroThisMonth[1] = euro;
        hoursAndEuroThisMonth[2] = euroPerHour;

        return hoursAndEuroThisMonth;
    }


}














