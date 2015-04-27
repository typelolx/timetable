package org.home.timetable.model;

public class Room {

    private final String name;
    private final Number capacity;
    private final String type;


    public Room(String name, Number capacity, String type) {
        this.name = name;
        this.capacity = capacity;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Number getCapacity() {
        return capacity;
    }

    public String getType() {
        return type;
    }


    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", type='" + type + '\'' +
                '}';
    }
}
