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
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.List;

@Service
public class FightMatrixLinkGrabber extends LinkGrabber {
public static String SITE_NAME="FightMatrix";
    protected String getUrl(String query) throws IOException {
        String userAgent = "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6";
        Document doc = Jsoup.connect("http://www.fightmatrix.com/fighter-search/?fName=" +
                URLEncoder.encode(query, "UTF-8")).
                userAgent(userAgent).get();
        Elements links = doc.select(".tdRank a");
        for (Element el : links) {
            String ref = el.attr("href");
            if (ref.contains("fighter-profile")) {
                return "http://www.fightmatrix.com/" + ref;
            }
        }
        return null;
    }

    protected String getSiteName() {
        return SITE_NAME;
    }

    protected int getSportId() {
        return Constants.MMA_ID;
    }

    protected String getPlayerName(String srcName) {
        return srcName;
    }

}
