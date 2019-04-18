package org.portal.back.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="LineEvent")
public class LineEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id",nullable = false)
    Event event;

    @Column
    private int period_number;

    @Column
    private String odds_format;

    @Column
    private String bet_type;

    @Column
    private String side_type;


    @Column
    Date checkDate;

    @Column
    private String team_type;

    @Column
    private BigDecimal price;

    @Column
    private BigDecimal max_bet;

    @Column
    private BigDecimal total;

    @Column
    private BigDecimal spread;

    @Column
    private boolean live;

    public LineEvent() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPeriod_number() {
        return period_number;
    }

    public void setPeriod_number(int period_number) {
        this.period_number = period_number;
    }

    public String getOdds_format() {
        return odds_format;
    }

    public void setOdds_format(String odds_format) {
        this.odds_format = odds_format;
    }

    public String getBet_type() {
        return bet_type;
    }

    public void setBet_type(String bet_type) {
        this.bet_type = bet_type;
    }

    public String getTeam_type() {
        return team_type;
    }

    public void setTeam_type(String team_type) {
        this.team_type = team_type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public BigDecimal getMax_bet() {
        return max_bet;
    }

    public void setMax_bet(BigDecimal max_bet) {
        this.max_bet = max_bet;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getSpread() {
        return spread;
    }

    public void setSpread(BigDecimal spread) {
        this.spread = spread;
    }

    public String getSide_type() {
        return side_type;
    }

    public void setSide_type(String side_type) {
        this.side_type = side_type;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }
}

