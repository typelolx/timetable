package org.home.timetable.builders;

import org.home.timetable.builders.constraints.IConstraint;
import org.home.timetable.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class TimetableBuilder {


    private Repo repo;
    private RequestComparatorConfig requestComparatorConfig;
    private IConstraint rater;


    public TimetableBuilder() {
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }

    public void setRequestComparatorConfig(RequestComparatorConfig requestComparatorConfig) {
        this.requestComparatorConfig = requestComparatorConfig;
    }

    public void setRater(IConstraint rater) {
        this.rater = rater;
    }

    public TimetableBuilderResult build() {

        // target timetable

        Timetable timetable = new Timetable();

        List<Request> unplaced = new ArrayList<Request>();


        // 1. sort request
        List<Request> requests = repo.getRequests();

        RequestComparator requestComparator = new RequestComparator();
        requestComparator.setComparatorConfig(requestComparatorConfig);
        requestComparator.setRepo(repo);

        Collections.sort(requests, requestComparator);


        // 2. for each request in list
        // find a best position in timetable


        for (Request request : requests) {

            for (int i = 0; i < request.getHours(); i++) {

                // for all posible positions
                List<Position> positions = createAllPositions(request);

                // results
                TreeMap<Integer, Position> results = new TreeMap<Integer, Position>();

                for (Position position : positions) {

                    timetable.addPosition(position);

                    int rate = rater.rate(timetable);

                    if (rate != -1) {
                        // may be
                        results.put(rate, position);
                    }

                    timetable.removePosition(position);

                }

                if (results.size() == 0) {

                    // no available place

                    unplaced.add(request);

                } else {
                    Position bestPosition = results.lastEntry().getValue();

                    timetable.addPosition(bestPosition);
                }
            }

        }


        TimetableBuilderResult result = new TimetableBuilderResult();
        result.timetable = timetable;
        result.unplacedRequest = unplaced;

        return result;
    }


    private List<Position> createAllPositions(Request request) {

        ArrayList<Position> positions = new ArrayList<Position>();

        for (Weekday weekday : Weekday.all()) {
            for (Timeslot timeslot : Timeslot.all()) {
                for (Room room : repo.getRooms()) {
                    Position position = new Position();

                    position.setWeekday(weekday);
                    position.setTimeslot(timeslot);

                    position.setGroup(request.getGroup());
                    position.setLesson(request.getLesson());
                    position.setTeacher(request.getTeacher());
                    position.setRoom(room);

                    positions.add(position);
                }
            }
        }


        return positions;
    }


}
