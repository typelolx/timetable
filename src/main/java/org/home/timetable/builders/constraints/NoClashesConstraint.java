package org.home.timetable.builders.constraints;

import org.home.timetable.model.*;

import java.util.*;

public class NoClashesConstraint implements IConstraint {


    public NoClashesConstraint() {
    }

    @Override
    public int rate(Timetable timetable) {

        Set<String> roomsClashes = new HashSet<String>();
        Set<String> groupsClashes = new HashSet<String>();
        Set<String> teachersClashes = new HashSet<String>();


        List<Position> positions = timetable.getPositions();

        for (Position position : positions) {

            String key = position.getWeekday() + ":" + position.getTimeslot();

            String roomKey = key + position.getRoom().getName();
            String groupKey = key + position.getGroup().getDesc();
            String teacherKey = key + position.getTeacher().getName();

            if (roomsClashes.contains(roomKey)) {
                return -1;
            } else {
                roomsClashes.add(roomKey);
            }

            if (groupsClashes.contains(groupKey)) {
                return -1;
            } else {
                groupsClashes.add(groupKey);
            }

            if (teachersClashes.contains(teacherKey)) {
                return -1;
            } else {
                teachersClashes.add(teacherKey);
            }


        }


        return 0;
    }
}
