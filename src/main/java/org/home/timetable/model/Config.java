package org.home.timetable.model;

import org.home.timetable.builders.RequestComparatorConfig;

public class Config {

    private RequestComparatorConfig comparatorConfig;

    private int[] weekdaysConf;
    private int[] timeslotsConf;

    public Config() {

        comparatorConfig = new RequestComparatorConfig(1, 1, 1, 1);

    }


    public RequestComparatorConfig getComparatorConfig() {
        return comparatorConfig;
    }

    public void setComparatorConfig(RequestComparatorConfig comparatorConfig) {
        this.comparatorConfig = comparatorConfig;
    }

    public void setWeekdaysConf(int[] weekdaysConf) {
        this.weekdaysConf = weekdaysConf;
    }

    public void setTimeslotsConf(int[] timeslotsConf) {
        this.timeslotsConf = timeslotsConf;
    }

    public int[] getWeekdaysConf() {
        return weekdaysConf;
    }

    public int[] getTimeslotsConf() {
        return timeslotsConf;
    }
}
