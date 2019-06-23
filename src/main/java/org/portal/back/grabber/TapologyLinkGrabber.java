package org.portal.back.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.portal.back.DataService;
import org.portal.back.model.Event;
import org.portal.back.model.Note;
import org.portal.back.model.NoteRepository;
import org.portal.back.model.NoteType;
import org.portal.back.pinnacle.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.List;

@Service
public class TapologyLinkGrabber extends GoogleGrabber {
    private static String DELIM = " ";
    @Autowired
    protected DataService ds;
    @Autowired
    NoteRepository noteRepository;

    public void getUrls() {
        Collection<Event> eventList = ds.getEvents(Constants.MMA_ID);

        for (Event event : eventList) {
            try {
                String homePlayer = getPlayerName(event.getHome());
                String awayPlayer = getPlayerName(event.getAway());
                if (!containTapologyLinks(homePlayer)) {
                    String url = getUrlFromExplorer(homePlayer);
                    saveTapologyNote(homePlayer, url);
                }
                if (!containTapologyLinks(awayPlayer)) {
                    String url = getUrlFromExplorer(awayPlayer);
                    saveTapologyNote(awayPlayer, url);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }


    private void saveTapologyNote(String name, String url) {
        Note note = new Note();
        note.setSport_id(Constants.MMA_ID);
        note.setPublictype(true);
        note.setDescr(name + DELIM + "Tapology");
        note.setPersonName(name);
        note.setLink(url);
        note.setType(NoteType.AUTOLINK);
        noteRepository.save(note);
    }

    private boolean containTapologyLinks(String name) {
        List<Note> explorerNotes = noteRepository.findByPersonName(name);
        for (Note note : explorerNotes) {
            if (note.getType().equals(NoteType.AUTOLINK) && note.getDescr().contains("Tapology")) {
                return true;
            }
        }
        return false;
    }

    protected  String getUrlFromExplorer(String query) throws IOException {
        String refword = query.replace("+", "-").toLowerCase();
        String userAgent = "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6";
        Elements links = Jsoup.connect("https://www.tapology.com/search?term=" +
                URLEncoder.encode(query, "UTF-8")).
                userAgent(userAgent).get().select(".altA a");
        for (Element elem : links) {
          String url=  elem.absUrl("href");
            if (url.contains(refword)) {
                return url;
            }
        }
        return null;
    }

    private String getPlayerName(String srcName) {
        String[] strArr = srcName.split(" ");
        return strArr[0] + "+" + strArr[1];
    }

}
