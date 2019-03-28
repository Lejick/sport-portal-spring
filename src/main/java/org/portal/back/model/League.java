package org.portal.back.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class League {
    private String name;
    private Date starts;
    public League(String name){
        this.name=name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStarts() {
        return starts;
    }

    public String getStartsFormat() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if(starts!=null) {
            return format.format(starts);
        }
        return "";
    }

    public void setStarts(Date starts) {
        this.starts = starts;
    }
}
