package org.portal.back;

import java.io.Serializable;
import java.util.*;

import org.portal.back.model.Event;
import org.portal.back.model.League;
import org.portal.back.pinnacle.Constants;
import org.portal.front.leagues.LeaguesDataProvider;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Back-end service interface for retrieving and updating product data.
 */
@Component
public class DataService implements Serializable {
    @PersistenceContext
    protected EntityManager em;

    @Transactional
    public Collection<Event> getEvents(String leagueName, int sportId) {
        java.math.BigInteger max = (java.math.BigInteger) em.createNativeQuery
                ("select MAX(id) from Odds where sport_id=:sportId")
                .setParameter("sportId", sportId)
                .getSingleResult();
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
    public Collection<Event> getEventsHistory(String leagueName, int sportId) {
              List<Event> events = em.createQuery(
                "SELECT e FROM Event e where league_name=:name AND sport_id=:sportId ORDER BY starts, home", Event.class)
                .setParameter("name", leagueName)
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
    public Collection<League> getAllLeagues(int sportId) {
        java.math.BigInteger max = (java.math.BigInteger) em.createNativeQuery
                ("select MAX(id) from Odds where sport_id=:sportId")
                .setParameter("sportId", sportId).getSingleResult();

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

    @Transactional
    public Collection<League> getAllLeaguesHistory(int sportId) {
        List events = em.createQuery(
                "SELECT (e.league_name), min(starts) st FROM Event e where sport_id=:sportId group by league_name order by st")
                .setParameter("sportId", sportId)
                .setMaxResults(200)
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

    @Transactional
    public Collection<League> findLeagueByName(int sportId, String leagueName) {
        List events = em.createQuery(
                "SELECT (e.league_name), min(starts) FROM Event e where sport_id=:sportId and league_name like '%:lName%' group by league_name order by starts")
                .setParameter("sportId", sportId)
                .setParameter("lName", leagueName)
                .setMaxResults(100)
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
