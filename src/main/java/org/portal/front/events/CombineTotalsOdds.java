package org.portal.front.events;

import org.portal.back.model.LineEvent;

import java.math.BigDecimal;
import java.util.Date;

public class CombineTotalsOdds {
  private  LineEvent over;
    private LineEvent under;
    private BigDecimal total;
    private  Date date;
    private  boolean live;

    public CombineTotalsOdds(LineEvent under, LineEvent over, Date date, BigDecimal total, boolean live) {
        this.over = over;
        this.under = under;
        this.date = date;
        this.total = total;
        this.live=live;
    }

    public LineEvent getOver() {
        return over;
    }

    public void setOver(LineEvent over) {
        this.over = over;
    }

    public LineEvent getUnder() {
        return under;
    }

    public void setUnder(LineEvent under) {
        this.under = under;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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
