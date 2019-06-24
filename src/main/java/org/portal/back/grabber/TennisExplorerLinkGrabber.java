package org.portal.back.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.portal.back.DataService;
import org.portal.back.model.*;
import org.portal.back.model.sherdog.EventModel;
import org.portal.back.model.sherdog.Fighter;
import org.portal.back.model.sherdog.FighterRepository;
import org.portal.back.pinnacle.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.List;

@Service
public class TennisExplorerLinkGrabber extends GoogleGrabber {
    private static String DELIM = " ";
    @Autowired
    protected DataService ds;
    @Autowired
    NoteRepository noteRepository;

    public void getExplorerUrl() {
        Collection<Event> eventList = ds.getEvents(Constants.TENNIS_ID);

        for (Event event : eventList) {
            try {
                String homePlayer = getPlayerName(event.getHome());
                String awayPlayer = getPlayerName(event.getAway());
                if (!containExplorerLinks(homePlayer)) {
                    String url = getUrlFromExplorer(homePlayer);
                    saveNote(homePlayer, url);
                }
                if (!containExplorerLinks(awayPlayer)) {
                    String url = getUrlFromExplorer(awayPlayer);
                    saveNote(awayPlayer, url);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }


    private void saveNote(String name, String url) {
        if(url==null){
            return;
        }
        Note note = new Note();
        note.setSport_id(Constants.TENNIS_ID);
        note.setPublictype(true);
        note.setDescr(name + DELIM + "TennisExplorer");
        note.setPersonName(name);
        note.setLink(url);
        note.setType(NoteType.AUTOLINK);
        noteRepository.save(note);
    }

    private boolean containExplorerLinks(String name) {
        List<Note> explorerNotes = noteRepository.findByPersonName(name);
        for (Note note : explorerNotes) {
            if (note.getType().equals(NoteType.AUTOLINK) && note.getDescr().contains("TennisExplorer")) {
                return true;
            }
        }
        return false;
    }

    protected String getUrlFromExplorer(String query) throws IOException {
        String userAgent = "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6";
        Elements links = Jsoup.connect("https://www.tennisexplorer.com/list-players/?search-text-pl=" +
                URLEncoder.encode(query, "UTF-8")).
                userAgent(userAgent).get().select(".flags>.two a");
        if(links.size()>0) {
            return links.get(0).absUrl("href");
        }
        return null;
    }

    private String getPlayerName(String srcName) {
        String[] strArr = srcName.split(" ");
        return strArr[0] + " " + strArr[1];
    }

}
