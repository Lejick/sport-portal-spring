package org.portal.back.grabber;

import org.portal.back.DataService;
import org.portal.back.model.Event;
import org.portal.back.model.Note;
import org.portal.back.model.NoteRepository;
import org.portal.back.model.NoteType;
import org.portal.back.model.sherdog.Fighter;
import org.portal.back.model.sherdog.FighterRepository;
import org.portal.back.pinnacle.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class SherdogLinkGrabber {
    private static String DELIM = " ";
    @Autowired
    protected DataService ds;
    @Autowired
    NoteRepository noteRepository;
    @Autowired
    FighterRepository fighterRepository;

    public void getSherdogUrl() {
        Collection<Event> eventList = ds.getEvents("UFC", Constants.MMA_ID);
        for (Event event : eventList) {

            if (!containSherdogLinks(event.getId())) {
                String homeFighter = event.getHome();
                List<Fighter> fList = fighterRepository.findByName(homeFighter);
                for (Fighter fighter : fList) {
                    String url = fighter.getSherdogUrl();
                    Note note = new Note();
                    note.setEventId(event.getId());
                    note.setPublictype(true);
                    note.setDescr(event.getHome() + DELIM + "Sherdog");
                    note.setLink(url);
                    note.setType(NoteType.AUTOLINK);
                    noteRepository.save(note);
                }
                String awayFighter = event.getAway();
                fList = fighterRepository.findByName(awayFighter);
                for (Fighter fighter : fList) {


                    String url = fighter.getSherdogUrl();
                    Note note = new Note();
                    note.setEventId(event.getId());
                    note.setPublictype(true);
                    note.setDescr(event.getAway() + DELIM + "Sherdog");
                    note.setLink(url);
                    note.setType(NoteType.AUTOLINK);
                    noteRepository.save(note);
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
