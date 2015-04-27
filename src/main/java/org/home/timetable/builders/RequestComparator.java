package org.home.timetable.builders;

import org.home.timetable.model.Repo;
import org.home.timetable.model.Request;

public class RequestComparator implements java.util.Comparator<Request> {


    private RequestComparatorConfig comparatorConfig;
    private Repo repo;

    public RequestComparator() {
    }

    public void setComparatorConfig(RequestComparatorConfig comparatorConfig) {
        this.comparatorConfig = comparatorConfig;
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }

    @Override
    public int compare(Request o1, Request o2) {

        double o1Weight = weight(new RequestValues(o1));

        double o2Weight = weight(new RequestValues(o2));

        return (int) Math.signum( (o2Weight - o1Weight) );
    }

    private double weight ( RequestValues values ){

        return
                values.hours * comparatorConfig.getHoursWeight() +
                        (1d / values.rooms) * comparatorConfig.getRoomWeight() +
                        values.teacherLessons * comparatorConfig.getTeacherWeight() +
                        (1d/ values.groupStage) * comparatorConfig.getGroupWeight();

    }


    private class RequestValues {

        private int hours;
        private int rooms;
        private int teacherLessons;
        private int groupStage;

        public RequestValues( Request request) {

            this.hours = request.getHours();

            this.rooms = repo.getRoomsForType(request.getLesson().getType()).size();

            this.teacherLessons = repo.getTeacherLessons(request.getTeacher()).size();

            this.groupStage = request.getGroup().getStage();

        }
    }
}
