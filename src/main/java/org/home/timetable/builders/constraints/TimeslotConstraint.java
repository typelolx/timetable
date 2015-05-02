package org.home.timetable.builders.constraints;

import org.home.timetable.model.Position;
import org.home.timetable.model.Timeslot;
import org.home.timetable.model.Timetable;
import org.home.timetable.model.Weekday;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeslotConstraint implements IConstraint {


    private Map<Weekday, Integer> weekdayRates;
    private Map<Timeslot, Integer> timeslotRates;

    public TimeslotConstraint() {

        weekdayRates = new HashMap<Weekday, Integer>();
        weekdayRates.put(Weekday.MONDAY, 2);
        weekdayRates.put(Weekday.TUESDAY, 3);
        weekdayRates.put(Weekday.WEDNESDAY, 4);
        weekdayRates.put(Weekday.THURSDAY, 1);
        weekdayRates.put(Weekday.FRIDAY, 0);


        timeslotRates = new HashMap<Timeslot, Integer>();
        timeslotRates.put(Timeslot.S1, 6);
        timeslotRates.put(Timeslot.S2, 5);
        timeslotRates.put(Timeslot.S3, 4);
        timeslotRates.put(Timeslot.S4, 3);
        timeslotRates.put(Timeslot.S5, 2);
        timeslotRates.put(Timeslot.S6, 1);
        timeslotRates.put(Timeslot.S7, 1);
        timeslotRates.put(Timeslot.S8, 1);
        timeslotRates.put(Timeslot.S9, 1);
        timeslotRates.put(Timeslot.S10, 1);
        timeslotRates.put(Timeslot.S11, 1);
        timeslotRates.put(Timeslot.S12, 1);
        timeslotRates.put(Timeslot.S13, 1);


    }

    public void setWeekdayRates(int[] weekdayRates) {
        this.weekdayRates = new HashMap<Weekday, Integer>();
        this.weekdayRates.put(Weekday.MONDAY,    weekdayRates[0]);
        this.weekdayRates.put(Weekday.TUESDAY,   weekdayRates[1]);
        this.weekdayRates.put(Weekday.WEDNESDAY, weekdayRates[2]);
        this.weekdayRates.put(Weekday.THURSDAY,  weekdayRates[3]);
        this.weekdayRates.put(Weekday.FRIDAY,    weekdayRates[4]);
    }

    public void setTimeslotRates(int[] timeslotRates) {
        this.timeslotRates = new HashMap<Timeslot, Integer>();
        this.timeslotRates.put(Timeslot.S1, timeslotRates[0]);
        this.timeslotRates.put(Timeslot.S2, timeslotRates[1]);
        this.timeslotRates.put(Timeslot.S3, timeslotRates[2]);
        this.timeslotRates.put(Timeslot.S4, timeslotRates[3]);
        this.timeslotRates.put(Timeslot.S5, timeslotRates[4]);
        this.timeslotRates.put(Timeslot.S6, timeslotRates[5]);
        this.timeslotRates.put(Timeslot.S7, timeslotRates[6]);
        this.timeslotRates.put(Timeslot.S8, timeslotRates[7]);
        this.timeslotRates.put(Timeslot.S9, timeslotRates[8]);
        this.timeslotRates.put(Timeslot.S10, timeslotRates[9]);
        this.timeslotRates.put(Timeslot.S11, timeslotRates[10]);
        this.timeslotRates.put(Timeslot.S12, timeslotRates[11]);
        this.timeslotRates.put(Timeslot.S13, timeslotRates[12]);
    }


    @Override
    public int rate(Timetable timetable) {

        List<Position> positions = timetable.getPositions();

        int rate = 0;

        for (Position position : positions) {

            rate += timeslotRates.get(position.getTimeslot());

            rate += weekdayRates.get(position.getWeekday());


        }


        return rate;
    }
}
