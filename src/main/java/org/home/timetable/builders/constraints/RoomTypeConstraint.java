package org.home.timetable.builders.constraints;

import org.home.timetable.model.Position;
import org.home.timetable.model.Timetable;

public class RoomTypeConstraint implements IConstraint {


    public RoomTypeConstraint() {
    }

    @Override
    public int rate(Timetable timetable) {

        int rate = 0;

        for (Position position : timetable.getPositions()) {

            String lessonType = position.getLesson().getType();
            String roomType = position.getRoom().getType();



            if (!lessonType.equals(roomType)) {
                return -1;
            }

        }

        return rate;
    }
}
