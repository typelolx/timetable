package org.home.timetable.builders;

import org.home.timetable.TestSupport;
import org.home.timetable.builders.constraints.*;
import org.junit.Test;

public class TimetableBuilderTest {


    @Test
    public void simpleRun () {


        TimetableBuilder builder = new TimetableBuilder();

        TimetableRater rater = new TimetableRater();

        rater.addConstraint(new NoClashesConstraint());
        rater.addConstraint(new NoDupLessonsConstraint());
        rater.addConstraint(new RoomTypeConstraint());
        rater.addConstraint(new TeacherInOneDayConstraint());
        rater.addConstraint(new TimeslotConstraint());


        builder.setRepo(TestSupport.createRepo(5, 10, 5, 2, 20, 10));
        builder.setRequestComparatorConfig(new RequestComparatorConfig(1, 1, 1, 1));
        builder.setRater(rater);


        long start = System.currentTimeMillis();

        TimetableBuilderResult build = builder.build();


        long end = System.currentTimeMillis();


        System.out.println("Timetable built in " + (end - start) + "ms");


        System.out.println(build.timetable.beautyView());

        System.out.println();
        System.out.println("Unplaced : ");
        System.out.println(build.unplacedRequest.size());

    }



}