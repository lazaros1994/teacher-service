package gr.teacher.teacherservice.lesson;

import gr.teacher.teacherservice.teacher.Teacher;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "teacher_has_lesson")
public class Lesson implements Serializable {

    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "teacher", referencedColumnName = "id", nullable = false)
    private Teacher teacher;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "student_surname")
    private String studentSurname;

    @Column(name = "day")
    private String day;

    @Column(name = "start_hour")
    private String startHour;

    @Column(name = "start_minute")
    private String startMinute;

    @Column(name = "end_hour")
    private String endHour;

    @Column(name = "end_minute")
    private String endMinute;

    @Column(name = "course")
    private String course;

    @Column(name = "euro_per_hour")
    private int euroPerHour;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getStartMinute() {
        return startMinute;
    }

    public void setStartMinute(String startMinute) {
        this.startMinute = startMinute;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    public String getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(String endMinute) {
        this.endMinute = endMinute;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getEuroPerHour() {
        return euroPerHour;
    }

    public void setEuroPerHour(int euroPerHour) {
        this.euroPerHour = euroPerHour;
    }

}
