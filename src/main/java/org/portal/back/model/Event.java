package org.portal.back.model;

import org.portal.back.pinnacle.api.enums.BET_TYPE;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name="Event")
public class Event {
    @Id
    private Long id;

    @Column
    private Long league_id;

    @Column
    private Integer sport_id;

    @Column
    String league_name;

    @OrderBy("checkDate desc")
    @OneToMany(mappedBy = "event",fetch = FetchType.EAGER)
    private List<LineEvent> lineEvents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actual_fix_id",nullable = false)
    Odds odds;

    @Column
    Date starts;

    @Column
    String home;

    @Column
    boolean live;

    @Column
    String away;

    @Column
    String alterTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getLeague_id() {
        return league_id;
    }

    public void setLeague_id(Long league_id) {
        this.league_id = league_id;
    }

    public String getLeague_name() {
        return league_name;
    }

    public void setLeague_name(String league_name) {
        this.league_name = league_name;
    }

    public Date getStarts() {
        return starts;
    }

    public String getStartsFormat() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (starts != null) {
            return format.format(starts);
        }
        return "";
    }

    public void setStarts(Date starts) {
        this.starts = starts;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getAway() {
        return away;
    }

    public String getBlank() {
        return "";
    }

    public void setAway(String away) {
        this.away = away;
    }

    public List<LineEvent> getLineEvents() {
        if (lineEvents == null) {
            lineEvents = new ArrayList<>();
        }
        Collections.sort(lineEvents, (o1, o2) -> {
            if (o1.getCheckDate().before(o2.getCheckDate())) {
                return 1;
            }
            if (o1.getCheckDate().before(o2.getCheckDate())) {
                return -1;
            }
            return 0;
        });
        return lineEvents;
    }

    public void addEvent(LineEvent lineEvent) {
        if (lineEvents == null) {
            lineEvents = new ArrayList<>();
        }
        lineEvents.add(lineEvent);
    }

    public void addFirstEvent(LineEvent lineEvent) {
        if (lineEvents == null) {
            lineEvents = new ArrayList<>();
        }
        lineEvents.add(0, lineEvent);
    }

    public void setLineEvents(List<LineEvent> lineEvents) {
        this.lineEvents = lineEvents;
    }

    public Integer getSport_id() {
        return sport_id;
    }

    public void setSport_id(Integer sport_id) {
        this.sport_id = sport_id;
    }

    public LineEvent getLastMoneyLine(String teamType) {
        if (getLineEvents().size() > 2) {
            for (LineEvent lineEvent : lineEvents) {
                if (lineEvent.getTeam_type() != null
                        && lineEvent.getTeam_type().equals(teamType)
                        && lineEvent.getBet_type().equals(BET_TYPE.MONEYLINE.toAPI())) {
                    return lineEvent;
                }
            }
        }
        return null;
    }

    public LineEvent getLastTotal(BigDecimal totalPoint, String sideType) {
        if (getLineEvents().size() > 2) {
            for (LineEvent lineEvent : lineEvents) {
                if (lineEvent.getSide_type() != null && lineEvent.getTotal()!=null
                        && lineEvent.getTotal().compareTo(totalPoint)==0
                        && lineEvent.getSide_type().equals(sideType)
                        && lineEvent.getBet_type().equals(BET_TYPE.TOTAL_POINTS.toAPI())) {
                    return lineEvent;
                }
            }
        }
        return null;
    }

    public LineEvent getLastSpread(BigDecimal spread, String teamType) {
        if (getLineEvents().size() > 2) {
            for (LineEvent lineEvent : lineEvents) {
                if (lineEvent.getTeam_type() != null && lineEvent.getSpread()!=null
                        && lineEvent.getSpread().compareTo(spread)==0
                        &&  lineEvent.getTeam_type().equals(teamType)
                        && lineEvent.getBet_type().equals(BET_TYPE.SPREAD.toAPI())) {
                    return lineEvent;
                }
            }
        }
        return null;
    }


    public Odds getOdds() {
        return odds;
    }

    public void setOdds(Odds odds) {
        this.odds = odds;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public String getAlterTitle() {
        return alterTitle;
    }

    public void setAlterTitle(String alterTitle) {
        this.alterTitle = alterTitle;
    }
}
