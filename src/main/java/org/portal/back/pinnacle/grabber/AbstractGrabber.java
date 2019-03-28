package org.portal.back.pinnacle.grabber;

import org.portal.back.model.LineEvent;
import org.portal.back.pinnacle.api.enums.TEAM_TYPE;
import org.portal.back.util.DualLogger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public abstract class AbstractGrabber {
    protected int sportId;
    @PersistenceContext
    protected EntityManager em;
    protected java.util.Date now;
    private static final DualLogger LOGGER = new DualLogger(AbstractGrabber.class);

    public AbstractGrabber(int sportId) {
        this.sportId = sportId;
    }

    public abstract void grab();

    public LineEvent getLastMoneyline(Long eventId, TEAM_TYPE teamType) {
        LineEvent result = new LineEvent();
           List<LineEvent> events = em.createQuery(
                "SELECT e FROM LineEvent e where event_id=:eventId and team_type=:team_type order by checkDate desc", LineEvent.class)
                .setParameter("eventId", eventId)
                .setParameter("team_type", teamType.toAPI())
                .getResultList();
        if (events.size() > 0) {
            result = events.get(0);
        }
        return result;

    }

    protected Date getCurrentTime() {
        return Calendar.getInstance().getTime();
    }

}
