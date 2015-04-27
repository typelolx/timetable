package org.home.timetable.model;

public class Group {


    private String desc;
    private int size;
    private int stage;

    public Group() {
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public String getDesc() {
        return desc;
    }

    public int getSize() {
        return size;
    }

    public int getStage() {
        return stage;
    }

    @Override
    public String toString() {
        return "[ Group: " + desc + " , size: " + size + " , stage: " + stage + " ]";
    }
}
