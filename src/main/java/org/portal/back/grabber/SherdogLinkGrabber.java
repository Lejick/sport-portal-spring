package org.portal.back.grabber;

import org.portal.back.model.*;
import org.portal.back.model.sherdog.EventModel;
import org.portal.back.model.sherdog.Fighter;
import org.portal.back.model.sherdog.FighterRepository;
import org.portal.back.pinnacle.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Service
public class SherdogLinkGrabber extends LinkGrabber {
    @Autowired
    FighterRepository fighterRepository;
    @Autowired
    EventRepository eventRepository;
    public static String SITE_NAME = "Sherdog";

    public void getLinks() {
        Collection<Event> eventList = ds.getEvents("UFC", Constants.MMA_ID);
        for (Event event : eventList) {
            if (event.getAlterTitle() == null) {
                List<EventModel> eventModelList = ds.getUFCEventsByDate(event.getStarts());
                if (eventModelList.size() > 0) {
                    Event dbEvent = eventRepository.findById(event.getId()).get();
                    dbEvent.setAlterTitle(eventModelList.get(0).getEventName());
                    eventRepository.save(dbEvent);
                }
            }
            String homeFighter = event.getHome();
            String awayFighter = event.getAway();

            if (!containLinks(homeFighter)) {

                List<Fighter> fList = fighterRepository.findByName(homeFighter);
                for (Fighter fighter : fList) {
                    String url = fighter.getSherdogUrl();
                    saveNote(homeFighter, url);
                }

                if (fList.isEmpty()) {
                    String searchWord = "sherdog " + homeFighter;
                    String url = null;
                    try {
                        url = getUrlFromGoogle(searchWord);
                    } catch (IOException e) {
                        url = "";
                    }
                    if (url.contains("sherdog")) {
                        saveNote(homeFighter, url);
                    }
                }
            }


            if (!containLinks(awayFighter)) {

                List<Fighter> fList = fighterRepository.findByName(awayFighter);

                if (fList.isEmpty()) {
                    String searchWord = "sherdog " + awayFighter;
                    String url = null;
                    try {
                        url = getUrlFromGoogle(searchWord);
                    } catch (IOException e) {
                        url = "";
                    }
                    if (url.contains("sherdog")) {
                        saveNote(awayFighter, url);
                    }
                }

                for (Fighter fighter : fList) {
                    String url = fighter.getSherdogUrl();
                    saveNote(awayFighter, url);
                }


            }
        }

    }

    @Override
    protected String getSiteName() {
        return SITE_NAME;
    }

    @Override
    protected int getSportId() {
        return Constants.MMA_ID;
    }

    @Override
    protected String getUrl(String query) throws IOException {
        return null;
    }

    @Override
    protected String getPlayerName(String query) {
        return null;
    }
}
