package org.portal.back.model.sherdog;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table
public class Fight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id",nullable = false)
    private EventModel event;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "fighter1_id",nullable = false)
    private Fighter fighter1;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "fighter2_id",nullable = false)
    private Fighter fighter2;

    @Column
    private ZonedDateTime date;
    @Column
    private String result;
    @Column
    private String winMethod;
    @Column
    private String winTime;
    @Column
    private int winRound;

    public Fight() {
    }

    public Fight(com.ftpix.sherdogparser.models.Fight sdFight, EventModel eventModel,
                 Fighter fighter1, Fighter fighter2) {
        setEvent(eventModel);
        setFighter1(fighter1);
        setFighter2(fighter2);
        setDate(sdFight.getDate());
        setResult(sdFight.getResult().name());
        setWinMethod(sdFight.getWinMethod());
        setWinTime(sdFight.getWinTime());
        setWinRound(sdFight.getWinRound());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventModel getEvent() {
        return this.event;
    }

    public void setEvent(EventModel event) {
        this.event = event;
    }

    public Fighter getFighter1() {
        return this.fighter1;
    }

    public void setFighter1(Fighter fighter1) {
        this.fighter1 = fighter1;
    }

    public Fighter getFighter2() {
        return this.fighter2;
    }

    public void setFighter2(Fighter fighter2) {
        this.fighter2 = fighter2;
    }

    public ZonedDateTime getDate() {
        return this.date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getWinMethod() {
        return this.winMethod;
    }

    public void setWinMethod(String winMethod) {
        this.winMethod = winMethod;
    }

    public String getWinTime() {
        return this.winTime;
    }

    public void setWinTime(String winTime) {
        this.winTime = winTime;
    }

    public int getWinRound() {
        return this.winRound;
    }

    public void setWinRound(int winRound) {
        this.winRound = winRound;
    }

    public boolean equals(Object obj) {
        try {
            Fight fight = (Fight)obj;
            return fight.getFighter1().getSherdogUrl().equalsIgnoreCase(this.fighter1.getSherdogUrl()) && fight.getFighter2().getSherdogUrl().equalsIgnoreCase(this.fighter2.getSherdogUrl()) && this.event.getSherdogUrl().equals(fight.getEvent().getSherdogUrl());
        } catch (Exception var3) {
            return false;
        }
    }

    public String toString() {
        return "Fight{event=" + this.event + ", fighter1=" + this.fighter1 + ", fighter2=" + this.fighter2 + ", date=" + this.date + ", result=" + this.result + ", winMethod='" + this.winMethod + '\'' + ", winTime='" + this.winTime + '\'' + ", winRound=" + this.winRound + '}';
    }
}

