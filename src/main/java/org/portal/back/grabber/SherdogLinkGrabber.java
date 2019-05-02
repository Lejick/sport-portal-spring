package org.portal.back.grabber;

import org.portal.ContextProvider;
import org.portal.back.DataService;
import org.portal.back.model.Event;
import org.portal.back.model.Note;
import org.portal.back.model.NoteRepository;
import org.portal.back.model.NoteType;
import org.portal.back.pinnacle.Constants;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class SherdogLinkGrabber extends GoogleGrabber {
    private static String DELIM = " ";
    protected DataService ds = ContextProvider.getBean(DataService.class);
    NoteRepository noteRepository = ContextProvider.getBean(NoteRepository.class);

    public SherdogLinkGrabber() {
    }

    public void getSherdogUrl() {
        Collection<Event> eventList = ds.getEvents("UFC", Constants.MMA_ID);
        for (Event event : eventList) {


            if (!containSherdogLinks(event.getId())) {
                try {
                    String homeFighter = event.getHome();
                    String searchWord = "sherdog " + homeFighter;
                    String url = getUrlFromGoogle(searchWord);
                    if (url.contains("sherdog")) {
                        Note note = new Note();
                        note.setEventId(event.getId());
                        note.setPublictype(true);
                        note.setDescr(event.getHome() + DELIM + "Sherdog");
                        note.setLink(url);
                        note.setType(NoteType.AUTOLINK);
                        noteRepository.save(note);
                    }

                    String awayFighter = event.getAway();
                     searchWord = "sherdog " + awayFighter;
                     url = getUrlFromGoogle(searchWord);
                    if (url.contains("sherdog")) {
                        Note note = new Note();
                        note.setEventId(event.getId());
                        note.setPublictype(true);
                        note.setDescr(event.getAway() + DELIM + "Sherdog");
                        note.setLink(url);
                        note.setType(NoteType.AUTOLINK);
                        noteRepository.save(note);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
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
