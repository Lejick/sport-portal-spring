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
public class TapologyLinkGrabber extends LinkGrabber {
    public static String SITE_NAME = "Tapology";

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
        String refword = query.replace("+", "-").toLowerCase();
        String userAgent = "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6";
        Elements links = Jsoup.connect("https://www.tapology.com/search?term=" +
                URLEncoder.encode(query, "UTF-8")).
                userAgent(userAgent).get().select(".altA a");
        for (Element elem : links) {
            String url = elem.absUrl("href");
            if (url.contains(refword)) {
                return url;
            }
        }
        return null;
    }

    protected String getPlayerName(String srcName) {
        return srcName.replace(" ", "+");
    }
}
