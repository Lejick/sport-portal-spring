package org.portal.front.events;

import org.portal.back.model.LineEvent;

import java.util.Date;

public class CombineMoneyLineOdds {
    LineEvent homeOdds;
    LineEvent awayOdds;
    Date date;
    boolean live;

    public CombineMoneyLineOdds(LineEvent homeOdds, LineEvent awayOdds1, Date date, boolean live) {
        this.homeOdds = homeOdds;
        this.awayOdds = awayOdds1;
        this.date = date;
        this.live = live;
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

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }
}
