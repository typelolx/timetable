package org.home.timetable;

import org.boris.xlloop.reflect.XLFunction;
import org.home.timetable.builders.RequestComparatorConfig;
import org.home.timetable.builders.TimetableBuilder;
import org.home.timetable.builders.TimetableBuilderResult;
import org.home.timetable.builders.TimetableRater;
import org.home.timetable.builders.constraints.NoClashesConstraint;
import org.home.timetable.builders.constraints.NoDupLessonsConstraint;
import org.home.timetable.builders.constraints.RoomTypeConstraint;
import org.home.timetable.builders.constraints.TimeslotConstraint;
import org.home.timetable.model.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ExcelLoader {


    private static final Repo REPO = new Repo();

    private static final Config CONFIG = new Config();

    private static final HashMap<Integer, TimetableBuilderResult> RESUTLS = new HashMap<Integer, TimetableBuilderResult>();
    private static Integer resultsCounter = 0;

    @XLFunction(name = "LoadData",
            help = "Build timetable",
            args = {"requests", "groups", "rooms", "config", "weekdays setup", "timeslots setup"},
            argHelp = {
                    "'Group', 'Teacher', 'Lesson', 'LessonType', 'Hours'",
                    "'desc', 'size', 'stage'",
                    "'Name', 'Capacity', 'Type'",
                    "'Room weight', 'Hours weight', 'Teacher weight', 'Group weight'",
                    "Mon, Tue, Wed, Thu, Fri",
                    "1,2,3,4,5,6,7"
            },
            category = "Timetable")
    public static String loadData(Object[][] requests,
                                  Object[][] groups,
                                  Object[][] rooms,
                                  Object[] config,
                                  Object[] weekdaysConf,
                                  Object[] timeslotsConf) {
        try {

            REPO.clean();

            for (Object[] groupConfig : groups) {

                Group group = new Group();
                group.setDesc(toStr(groupConfig[0]));
                group.setSize(toInt(groupConfig[1]));
                group.setStage(toInt(groupConfig[2]));

                System.out.println("Group : " + group);
                REPO.addGroups(group);

            }

            for (Object[] roomConfig : rooms) {
                Room room = new Room(
                        toStr(roomConfig[0]),
                        toInt(roomConfig[1]),
                        toStr(roomConfig[2])
                );

                System.out.println("Room : " + room);
                REPO.addRooms(room);
            }

            RequestComparatorConfig comparatorConfig = new RequestComparatorConfig(
                    toInt(config[0]),
                    toInt(config[1]),
                    toInt(config[2]),
                    toInt(config[3])
            );

            System.out.println("Config : " + comparatorConfig);
            CONFIG.setComparatorConfig(comparatorConfig);


            int[] timeslotsConfig = {
                    toInt(timeslotsConf[0]),
                    toInt(timeslotsConf[1]),
                    toInt(timeslotsConf[2]),
                    toInt(timeslotsConf[3]),
                    toInt(timeslotsConf[4]),
                    toInt(timeslotsConf[5]),
                    toInt(timeslotsConf[6])
            };
            System.out.println("Timeslot config : " + Arrays.toString(timeslotsConfig));
            CONFIG.setTimeslotsConf(timeslotsConfig);

            int[] weekdaysConfig = {
                    toInt(weekdaysConf[0]),
                    toInt(weekdaysConf[1]),
                    toInt(weekdaysConf[2]),
                    toInt(weekdaysConf[3]),
                    toInt(weekdaysConf[4])
            };
            System.out.println("weekday config : " + Arrays.toString(weekdaysConfig));
            CONFIG.setWeekdaysConf(weekdaysConfig);


            for (Object[] requestConfig : requests) {

                String groupDesc = toStr(requestConfig[0]);
                Group group = REPO.getGroupByDesc(groupDesc);
                if (group == null) {
                    throw new IllegalArgumentException("No group for : " + groupDesc);
                }

                Teacher teacher = new Teacher();
                teacher.setName(toStr(requestConfig[1]));

                Lesson lesson = new Lesson(toStr(requestConfig[2]), toStr(requestConfig[3]));

                int hours = toInt(requestConfig[4]);

                int half = toInt(requestConfig[5]);

                Request request = new Request();
                request.setGroup(group);
                request.setLesson(lesson);
                request.setTeacher(teacher);
                request.setHours(hours);
                request.setHalf(half);

                System.out.println("Request : " + request);
                REPO.addRequests(request);

            }

            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }


    @XLFunction(name = "BuildTimetable",
            help = "Build timetable",
            category = "Timetable")
    public static String buildTimeTable() {


        TimeslotConstraint timeslotConstraint = new TimeslotConstraint();
        timeslotConstraint.setTimeslotRates(CONFIG.getTimeslotsConf());
        timeslotConstraint.setWeekdayRates(CONFIG.getWeekdaysConf());


        TimetableRater rater = new TimetableRater();
        rater.addConstraint(new NoClashesConstraint());
        rater.addConstraint(new TimeslotConstraint());
        rater.addConstraint(new RoomTypeConstraint());
        rater.addConstraint(new NoDupLessonsConstraint());

        TimetableBuilder builder = new TimetableBuilder();

        builder.setRequestComparatorConfig(CONFIG.getComparatorConfig());
        builder.setRepo(REPO);
        builder.setRater(rater);

        TimetableBuilderResult result = builder.build();

        System.out.println("--- --- ---");
        System.out.println("Unplaced : ");
        System.out.println(result.getUnplacedRequest());

        System.out.println("--- --- ---");
        System.out.println("Timetable : ");
        System.out.println(result.getTimetable().beautyView());


        RESUTLS.put(++resultsCounter, result);

        return "OK : " + String.valueOf(resultsCounter)
                 + " : " + "Не размещено : " + result.getUnplacedRequest().size();

    }

    @XLFunction(name = "DisplayTimetable",
            help = "Display timetable",
            category = "Timetable")
    public static String[][] displayTimeTable(Object number) {

        if (number == null) {
            return new  String[][] {{"Не указан номер расписания!"}};
        }

        TimetableBuilderResult result = RESUTLS.get(toInt(number));

        if (result == null) {
            return new String[][]{{"Нет результата с номером : " + number}};
        }


        List<Group> allGroups = REPO.getGroups();
        List<Weekday> allWeekdays = Weekday.all();
        List<Timeslot> allTimeslots = Timeslot.all();


        String[][] display = new String
                [allGroups.size()]
                [allWeekdays.size() * allTimeslots.size()];


        for (Position position : result.getTimetable().getPositions()) {

            int grIndex = allGroups.indexOf(position.getGroup());

            String text = position.getLesson().getName() + " : " +
                    position.getTeacher().getName() + " : " +
                    position.getRoom().getName();

            int slotIndex = allTimeslots.size() * allWeekdays.indexOf(position.getWeekday()) +
                    allTimeslots.indexOf(position.getTimeslot());


            display[grIndex][slotIndex] = text;


        }


        return display;

    }


    private static int toInt(Object o) {
        return ((Number) o).intValue();
    }

    private static String toStr(Object o) {
        return String.valueOf(o);
    }


}
