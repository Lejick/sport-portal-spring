package org.portal.back.grabber;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
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
public class SofaScoreLinkGrabber extends LinkGrabber {
    public static String SITE_NAME = "Sofascore";

    @Override
    protected String getSiteName() {
        return SITE_NAME;
    }

    @Override
    protected int getSportId() {
        return Constants.TENNIS_ID;
    }

    protected String getUrl(String name) throws IOException {
        String slug = "";
        Integer id = 0;
        String jsonString = getJsonString(name);
        JSONObject jsonObj = new JSONObject(jsonString);
        JSONArray teams = (JSONArray) jsonObj.get("teams");
        for (Object team : teams) {
            JSONObject t = (JSONObject) team;
            slug = (String) t.get("slug");
            id = (Integer) t.get("id");
        }
        return "https://www.sofascore.com/ru/team/tennis/" + slug + "/" + id;
    }

    protected static String getJsonString(String query) throws IOException {
        String userAgent = "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6";
        String doc = Jsoup.connect("https://www.sofascore.com/api/v1/search/teams/" +
                URLEncoder.encode(query, "UTF-8")).ignoreContentType(true).
                userAgent(userAgent).get().text();
        return doc;
    }

    protected String getPlayerName(String srcName) {
        String[] strArr = srcName.split(" ");
        return strArr[0] + " " + strArr[1];
    }

}
