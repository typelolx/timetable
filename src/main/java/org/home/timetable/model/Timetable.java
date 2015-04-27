package org.home.timetable.model;

import java.util.ArrayList;
import java.util.List;

public class Timetable {

    private List<Position> positions = new ArrayList<Position>();


    public Timetable() {
    }

    public void addPosition(Position position) {
        positions.add(position);
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void removePosition(Position position) {
        positions.remove(position);
    }



    public String beautyView () {

        StringBuilder builder = new StringBuilder();


        for (Position position : positions) {

            String s = String.format("%-10s %-3s %-10s %-7s %-10s %-7s",
                    position.getWeekday(),
                    position.getTimeslot(),
                    position.getGroup().getDesc(),
                    position.getLesson().getName(),
                    position.getTeacher().getName(),
                    position.getRoom().getName());

            builder.append(s).append("\n");
        }

        return builder.toString();

    }

}
