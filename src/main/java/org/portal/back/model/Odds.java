package org.portal.back.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Odds")
public class Odds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date oddsDate;

    @Column
    private Integer sport_id;

    public Odds() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOddsDate() {
        return oddsDate;
    }

    public void setOddsDate(Date date) {
        this.oddsDate = date;
    }

    public Integer getSport_id() {
        return sport_id;
    }

    public void setSport_id(Integer sport_id) {
        this.sport_id = sport_id;
    }
}
