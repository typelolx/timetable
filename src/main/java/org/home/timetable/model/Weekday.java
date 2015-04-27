package org.home.timetable.model;

import java.util.Arrays;
import java.util.List;

public enum Weekday {

    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY;


    public static List<Weekday> all(){

        return Arrays.asList(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY);

    }

    Weekday() {
    }
}
