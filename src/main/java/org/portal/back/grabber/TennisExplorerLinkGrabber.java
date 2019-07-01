package org.portal.back.grabber;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.portal.back.DataService;
import org.portal.back.model.*;
import org.portal.back.pinnacle.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.List;

@Service
public class TennisExplorerLinkGrabber extends LinkGrabber {
    public static String SITE_NAME = "TennisExplorer";

    @Override
    protected String getSiteName() {
        return SITE_NAME;
    }

    @Override
    protected int getSportId() {
        return Constants.TENNIS_ID;
    }

    protected String getUrl(String query) throws IOException {
        String userAgent = "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6";
        Elements links = Jsoup.connect("https://www.tennisexplorer.com/list-players/?search-text-pl=" +
                URLEncoder.encode(query, "UTF-8")).
                userAgent(userAgent).get().select(".flags>.two a");
        if (links.size() > 0) {
            return links.get(0).absUrl("href");
        }
        return null;
    }

    protected String getPlayerName(String srcName) {
        String[] strArr = srcName.split(" ");
        return strArr[0] + " " + strArr[1];
    }

}
