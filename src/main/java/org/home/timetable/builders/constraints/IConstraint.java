package org.home.timetable.builders.constraints;

import org.home.timetable.model.Timetable;

public interface IConstraint {

    public int rate(Timetable timetable);

}
