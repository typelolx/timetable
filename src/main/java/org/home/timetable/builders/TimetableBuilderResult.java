package org.home.timetable.builders;

import org.home.timetable.model.Request;
import org.home.timetable.model.Timetable;

import java.util.List;

public class TimetableBuilderResult {

    Timetable timetable;

    List<Request> unplacedRequest;

    public TimetableBuilderResult() {
    }

    public Timetable getTimetable() {
        return timetable;
    }

    public List<Request> getUnplacedRequest() {
        return unplacedRequest;
    }
}
