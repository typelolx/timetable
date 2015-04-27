package org.home.timetable.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Repo {


    private List<Group> groups;
    private List<Room>  rooms;

    private List<Request> requests;

    public Repo() {
        groups = new ArrayList<Group>();
        rooms = new ArrayList<Room>();
        requests = new ArrayList<Request>();
    }

    public List<Group> getGroups() {
        return Collections.unmodifiableList(groups);
    }

    public Group getGroupByDesc (String desc) {
        for (Group group : groups) {
            if (group.getDesc().equals(desc)) {
                return group;
            }
        }

        return null;
    }

    public void addGroups (Group... groups) {
        Collections.addAll(this.groups, groups);
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Request> getRequests() {
        return (requests);
    }

    public void addRooms (Room... rooms) {
        Collections.addAll(this.rooms, rooms);
    }

    public void addRequests (Request... requests) {
        Collections.addAll(this.requests, requests);
    }

    public List<Room> getRoomsForType (String type) {
        List<Room> filteredRooms = new ArrayList<Room>();

        for (Room room : rooms) {
            if (room.getType() == null || room.getType().equals(type)) {
                filteredRooms.add(room);
            }
        }

        return filteredRooms;
    }

    public List<Request> getTeacherLessons(Teacher teacher) {

        ArrayList<Request> filteredRequests = new ArrayList<Request>();

        for (Request request : requests) {
            if (request.getTeacher().equals(teacher)){
                filteredRequests.add(request);
            }
        }

        return filteredRequests;
    }

    public void clean() {
        groups = new ArrayList<Group>();
        rooms = new ArrayList<Room>();
        requests = new ArrayList<Request>();
    }
}
