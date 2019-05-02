package org.portal.back.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class GoogleGrabber {

    protected  String getUrlFromGoogle(String query) throws IOException {

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
