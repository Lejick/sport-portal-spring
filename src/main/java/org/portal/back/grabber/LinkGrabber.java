package org.portal.back.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.portal.back.DataService;
import org.portal.back.model.Event;
import org.portal.back.model.Note;
import org.portal.back.model.NoteRepository;
import org.portal.back.model.NoteType;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.List;

public abstract class LinkGrabber {
    protected static String DELIM = " ";

    @Autowired
    protected DataService ds;
    @Autowired
    NoteRepository noteRepository;

    public void getLinks() {
        Collection<Event> eventList = ds.getEvents(getSportId());

        for (Event event : eventList) {
            try {
                String homePlayer = event.getHome();
                String awayPlayer = event.getAway();
                if (!containLinks(homePlayer)) {
                    String url = getUrl(getPlayerName(event.getHome()));
                    saveNote(homePlayer, url);
                }
                if (!containLinks(awayPlayer)) {
                    String url = getUrl(getPlayerName(event.getAway()));
                    saveNote(awayPlayer, url);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }


    protected void saveNote(String name, String url) {
        if (url == null) {
            return;
        }
        Note note = new Note();
        note.setSport_id(getSportId());
        note.setPublictype(true);
        note.setDescr(name + DELIM + getSiteName());
        note.setPersonName(name);
        note.setLink(url);
        note.setType(NoteType.AUTOLINK);
        noteRepository.save(note);
    }

    protected boolean containLinks(String name) {
        List<Note> explorerNotes = noteRepository.findByPersonName(name);
        for (Note note : explorerNotes) {
            if (note.getType().equals(NoteType.AUTOLINK) && note.getDescr().contains(getSiteName())) {
                return true;
            }
        }
        return false;
    }

    protected abstract String getSiteName();

    protected abstract int getSportId();

    protected abstract String getUrl(String query) throws IOException;

    protected abstract String getPlayerName(String query);


    protected String getUrlFromGoogle(String query) throws IOException {

        String userAgent = "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6";
        Elements links = Jsoup.connect("http://www.google.ru/search?q=" +
                URLEncoder.encode(query, "UTF-8")).
                userAgent(userAgent).get().select(".g>.r>a");

        for (Element link : links) {
            String url = link.absUrl("href");
            url = URLDecoder.decode(url.substring(url.indexOf('=') +
                    1, url.indexOf('&')), "UTF-8");
            if (!url.startsWith("http")) {
                continue;
            }
        }
        return links.get(0).absUrl("href");
    }

}
