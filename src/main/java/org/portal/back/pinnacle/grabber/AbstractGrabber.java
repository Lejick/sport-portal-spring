package org.portal.back.pinnacle.grabber;


import java.util.Calendar;
import java.util.Date;

public abstract class AbstractGrabber {
    protected int sportId;
    protected java.util.Date now;
    public AbstractGrabber(int sportId) {
        this.sportId = sportId;
    }

    public abstract void grab();

    protected Date getCurrentTime() {
        return Calendar.getInstance().getTime();
    }

}
