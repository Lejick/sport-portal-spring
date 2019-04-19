package org.portal.front.events;

import org.portal.back.model.LineEvent;

import java.math.BigDecimal;
import java.util.Date;

public class CombineSpreadOdds {
    LineEvent homeOdds;
    LineEvent awayOdds;
    private BigDecimal spread;
    private  Date date;
    private  boolean live;

    public CombineSpreadOdds(LineEvent homeOdds, LineEvent awayOdds, Date date, BigDecimal spread, boolean live) {
        this.homeOdds = homeOdds;
        this.awayOdds = awayOdds;
        this.date = date;
        this.spread = spread;
        this.live=live;
    }

    public LineEvent getHomeOdds() {
        return homeOdds;
    }

    public void setHomeOdds(LineEvent homeOdds) {
        this.homeOdds = homeOdds;
    }

    public LineEvent getAwayOdds() {
        return awayOdds;
    }

    public void setAwayOdds(LineEvent awayOdds) {
        this.awayOdds = awayOdds;
    }

    public BigDecimal getSpread() {
        return spread;
    }

    public void setSpread(BigDecimal spread) {
        this.spread = spread;
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
