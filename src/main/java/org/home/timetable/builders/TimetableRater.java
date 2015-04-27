package org.home.timetable.builders;

import org.home.timetable.builders.constraints.IConstraint;
import org.home.timetable.model.Timetable;

import java.util.ArrayList;
import java.util.List;

public class TimetableRater implements IConstraint {

    List<IConstraint> constraints = new ArrayList<IConstraint>();


    public TimetableRater() {
    }

    public void addConstraint (IConstraint constraint) {
        constraints.add(constraint);
    }


    @Override
    public int rate (Timetable timetable) {

        int total = 0;

        for (IConstraint constraint : constraints) {

            int rate = constraint.rate(timetable);

            if (rate == -1) {
                return -1;
            }

            total += rate;

        }


        return total;

    }

}
