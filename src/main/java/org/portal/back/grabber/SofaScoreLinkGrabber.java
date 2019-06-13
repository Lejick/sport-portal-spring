package org.portal.back.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
public class SofaScoreLinkGrabber extends GoogleGrabber {
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
                //    Document url = getUrl(homePlayer);
                   // saveExplorerNote(homePlayer, url);
                }
                if (!containExplorerLinks(awayPlayer)) {
                 //   Document url = getUrl(awayPlayer);
                //    saveExplorerNote(awayPlayer, url);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public static void main(String[] args) throws IOException {
      getUrl("Alexey Vatutin");
      System.out.println();
    }

    private void saveExplorerNote(String name, String url) {
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

    protected static String getUrl(String query) throws IOException {
        String userAgent = "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6";
      String doc = Jsoup.connect("https://www.sofascore.com/api/v1/search/teams/" +
                URLEncoder.encode(query, "UTF-8")).ignoreContentType(true).
                userAgent(userAgent).get().text();
        return doc;
    }

    private String getPlayerName(String srcName) {
        String[] strArr = srcName.split(" ");
        return strArr[0] + " " + strArr[1];
    }

}
