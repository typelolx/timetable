package org.home.timetable.builders;

public class RequestComparatorConfig {


    private final int roomWeight;
    private final int hoursWeight;
    private final int teacherWeight;
    private final int groupWeight;


    public RequestComparatorConfig(int roomWeight, int hoursWeight, int teacherWeight, int groupWeight) {
        this.roomWeight = roomWeight;
        this.hoursWeight = hoursWeight;
        this.teacherWeight = teacherWeight;
        this.groupWeight = groupWeight;
    }

    public int getTeacherWeight() {
        return teacherWeight;
    }

    public int getRoomWeight() {
        return roomWeight;
    }

    public int getHoursWeight() {
        return hoursWeight;
    }

    public int getGroupWeight() {
        return groupWeight;
    }


    @Override
    public String toString() {
        return "RequestComparatorConfig{" +
                "roomWeight=" + roomWeight +
                ", hoursWeight=" + hoursWeight +
                ", teacherWeight=" + teacherWeight +
                ", groupWeight=" + groupWeight +
                '}';
    }
}
