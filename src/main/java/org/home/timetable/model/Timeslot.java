package org.home.timetable.model;

import com.sun.tools.corba.se.idl.constExpr.Times;

import java.util.Arrays;
import java.util.List;

public enum Timeslot {

    S1, S2, S3, S4, S5, S6, S7;

    public static List<Timeslot> all(){

        return Arrays.asList(S1, S2, S3, S4, S5, S6, S7);

    }

    public static List<Timeslot> firstHalf() {
        return Arrays.asList(S1, S2, S3, S4);
    }

    public static List<Timeslot> secondHalf () {
        return Arrays.asList(S5, S6, S7);
    }

    Timeslot() {
    }

    public static List<Timeslot> all(int half) {

        if (half == 1) {
            return firstHalf();
        } else if (half == 2) {
            return secondHalf();
        }

        return all();

    }
}
