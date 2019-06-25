package com.travel.model;

public class Event {

    private String title;
 
    //カレンダーの開始日付
    private String start;

    //カレンダーの終了日付
    private String end;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getStart() {
        return start;
    }
    public void setStart(String start) {
        this.start = start;
    }
    public String getEnd() {
        return end;
    }
    public void setEnd(String end) {
        this.end = end;
    }
}
