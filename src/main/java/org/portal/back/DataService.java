package org.portal.back;

import java.io.Serializable;
import java.util.*;

import org.portal.back.model.Event;
import org.portal.back.model.League;
import org.portal.back.pinnacle.Constants;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Back-end service interface for retrieving and updating product data.
 */
@Component
public class DataService implements Serializable {
    private  int sportId=Constants.TENNIS_ID;
    @PersistenceContext
    protected EntityManager em;

    @Transactional
    public Collection<Event> getEvents(String leagueName) {
        java.math.BigInteger max = (java.math.BigInteger) em.createNativeQuery("select MAX(id) from odds").getSingleResult();
        List<Event> events = em.createQuery(
                "SELECT e FROM Event e where league_name=:name AND (actual_fix_id=:max OR actual_fix_id=:pre) AND sport_id=:sportId ORDER BY starts, home", Event.class)
                .setParameter("name", leagueName)
                .setParameter("max", max.intValue())
                .setParameter("pre", max.intValue()-1)
                .setParameter("sportId", sportId)
                .getResultList();
        return events;
    }
    @Transactional
    public Event getEventById(Long eventId) {
        Event result = new Event();

        List<Event> events = em.createQuery(
                "SELECT e FROM Event e where id=:eventId", Event.class)
                .setParameter("eventId", eventId)
                .getResultList();
        if (events.size() > 0) {
            result = events.get(0);
        }
        return result;

    }

    @Transactional
    public Collection<League> getAllLeagues() {
        java.math.BigInteger max = (java.math.BigInteger) em.createNativeQuery("select MAX(id) from odds").getSingleResult();

        List events = em.createQuery(
                "SELECT (e.league_name), min(starts) FROM Event e where (actual_fix_id=:max OR actual_fix_id=:pre) AND sport_id=:sportId group by league_name order by league_name")
                .setParameter("max",   max.intValue())
                .setParameter("pre", max.intValue()-1)
                .setParameter("sportId", sportId)
                .getResultList();
        List<League> leagues = new ArrayList<>();
        for (Object event : events) {
            Object[] resArr = (Object[]) event;
            League league = new League((String) resArr[0]);
            league.setStarts((Date) resArr[1]);
            leagues.add(league);
        }
        return leagues;
    }
}
