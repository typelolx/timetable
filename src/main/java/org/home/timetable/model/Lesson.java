package org.home.timetable.model;

public class Lesson {

    private final String name;
    private final String type;

    public Lesson(String name, String type) {
        this.name = name;
        this.type = type;
    }


    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
