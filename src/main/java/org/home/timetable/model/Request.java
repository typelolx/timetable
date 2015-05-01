package org.home.timetable.model;

public class Request {


    private Teacher teacher;
    private Lesson lesson;
    private Group group;
    private Integer hours;
    private Integer half;


    public Request() {
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public Group getGroup() {
        return group;
    }

    public Integer getHours() {
        return hours;
    }

    public Integer getHalf() {
        return half;
    }

    public void setHalf(Integer half) {
        this.half = half;
    }

    @Override
    public String toString() {
        return "Request{" +
                "teacher=" + teacher +
                ", lesson=" + lesson +
                ", group=" + group +
                ", hours=" + hours +
                ", half=" + half +
                '}';
    }
}
