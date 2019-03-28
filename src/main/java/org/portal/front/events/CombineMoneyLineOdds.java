package org.portal.front.events;

import org.portal.back.model.LineEvent;

import java.util.Date;

public class CombineMoneyLineOdds {
    LineEvent homeOdds;
    LineEvent awayOdds;
    Date date;

    public CombineMoneyLineOdds(LineEvent homeOdds, LineEvent awayOdds1, Date date) {
        this.homeOdds = homeOdds;
        this.awayOdds = awayOdds1;
        this.date = date;
    }

    public LineEvent getAwayOdds() {
        return awayOdds;
    }

    public void setAwayOdds(LineEvent awayOdds) {
        this.awayOdds = awayOdds;
    }

    public LineEvent getHomeOdds() {
        return homeOdds;
    }

    public void setHomeOdds(LineEvent homeOdds) {
        this.homeOdds = homeOdds;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
