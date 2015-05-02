package org.home.timetable;

import org.home.timetable.model.*;

public class TestSupport {

    public static Repo createRepo (int teachersCount, int groupsCount,
                            int lessonsCount,
                            int typesCount, int requestsCount, int roomCount) {

        Repo repo = new Repo();

        Teacher[] tPool = new Teacher[teachersCount];
        for (int i = 0; i < teachersCount; i++) {
            Teacher teacher = new Teacher();
            teacher.setName("teacher_" + i);
            tPool[i] = teacher;
        }

        Group[] gPool = new Group[groupsCount];
        for (int i = 0; i < groupsCount; i++) {
            Group group= new Group();
            int stg = rnd(1, 11);
            group.setDesc("grp_" + i + "_" + stg);
            group.setStage(stg);
            group.setSize(15);
            gPool[i] = group;
        }

        String[] typesPool = new String[typesCount];
        for (int i = 0; i < typesCount; i++) {
            String type = "type_" + i;
            typesPool[i] = type;
        }

        Lesson[] lPool = new Lesson[lessonsCount];
        for (int i = 0; i < lessonsCount; i++) {
            Lesson lesson = new Lesson("lsn_" + i, rnd(typesPool));
            lPool[i] = lesson;
        }


        Room[] rooms = new Room[roomCount];
        for (int i = 0; i < roomCount; i++) {
            Room room = new Room("room_" + i, 20, rnd(typesPool));
            rooms[i] = room;
        }
        repo.addRooms(rooms);


        Request[] requests = new Request[requestsCount];

        for (int i = 0; i < requestsCount; i++) {
            Request request = new Request();
            request.setTeacher(rnd(tPool));
            request.setLesson(rnd(lPool));
            request.setGroup(rnd(gPool));
            request.setHours(rnd(0,3));
            request.setHalf(1);
            requests[i] = request;
        }

        repo.addRequests(requests);


        return repo;
    }


    private static int rnd (int s, int e) {
        return ((int) Math.round(s + (e - s) * Math.random()));
    }

    private static <T> T rnd(T[] array) {
        return array[rnd(0,array.length - 1)];
    }


}
