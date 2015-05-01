package org.home.timetable.builders.constraints;

import org.home.timetable.model.Position;
import org.home.timetable.model.Timetable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by typelol on 01/05/15.
 */
public class NoDupLessonsConstraint implements IConstraint {
    @Override
    public int rate(Timetable timetable) {


        List<Position> positions = timetable.getPositions();


        Set<String> keys = new HashSet<String>();

        for (Position position : positions) {


            String key = position.getWeekday() + ":" +
                    position.getTimeslot() + ":" +
                    position.getGroup() + ":"  +
                    position.getLesson().getName();

            keys.add(key);
        }





        return keys.size();
    }
}
