package org.home.timetable.model;

import java.util.Arrays;
import java.util.List;

public enum Timeslot {

    S1, S2, S3, S4, S5, S6, S7;

    public static List<Timeslot> all(){

        return Arrays.asList(S1, S2, S3, S4, S5, S6, S7);

    }

    Timeslot() {
    }
}
