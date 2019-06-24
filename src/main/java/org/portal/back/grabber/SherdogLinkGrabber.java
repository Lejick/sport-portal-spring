package org.portal.back.grabber;

import org.portal.back.DataService;
import org.portal.back.model.*;
import org.portal.back.model.sherdog.EventModel;
import org.portal.back.model.sherdog.Fighter;
import org.portal.back.model.sherdog.FighterRepository;
import org.portal.back.pinnacle.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.util.EventReaderDelegate;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Service
public class SherdogLinkGrabber extends GoogleGrabber{
    private static String DELIM = " ";
    @Autowired
    protected DataService ds;
    @Autowired
    NoteRepository noteRepository;
    @Autowired
    FighterRepository fighterRepository;
    @Autowired
    EventRepository eventRepository;

    public void getSherdogUrl() {
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
            if (!containSherdogLinks(event.getId())) {
                String homeFighter = event.getHome();
                List<Fighter> fList = fighterRepository.findByName(homeFighter);
                for (Fighter fighter : fList) {
                    String url = fighter.getSherdogUrl();
                    saveSherdogNote(event, homeFighter, url);
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
                        saveSherdogNote(event,homeFighter, url);
                    }
                }

                String awayFighter = event.getAway();
                fList = fighterRepository.findByName(awayFighter);

                if (fList.isEmpty()) {
                    String searchWord = "sherdog " + awayFighter;
                    String url = null;
                    try {
                        url = getUrlFromGoogle(searchWord);
                    } catch (IOException e) {
                        url = "";
                    }
                    if (url.contains("sherdog")) {
                        saveSherdogNote(event,awayFighter, url);
                    }
                }

                for (Fighter fighter : fList) {
                    String url = fighter.getSherdogUrl();
                    saveSherdogNote(event,awayFighter, url);
                }


            }
        }

    }

    private void saveSherdogNote(Event event,String name, String url){
        Note note = new Note();
        note.setEventId(event.getId());
        note.setPublictype(true);
        note.setDescr(name + DELIM + "Sherdog");
        note.setLink(url);
        note.setType(NoteType.AUTOLINK);
        noteRepository.save(note);
    }

    private boolean containSherdogLinks(Long eventId) {
        List<Note> sherdogNotes = noteRepository.findByEventId(eventId);
        for (Note note : sherdogNotes) {
            if (note.getType().equals(NoteType.AUTOLINK) && note.getDescr().contains("Sherdog")) {
                return true;
            }
        }
        return false;
    }
}
