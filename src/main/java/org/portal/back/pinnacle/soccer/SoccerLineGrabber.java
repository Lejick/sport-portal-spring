package org.portal.back.pinnacle.soccer;

import org.portal.back.pinnacle.Constants;
import org.portal.back.pinnacle.grabber.LineGrabber;
import org.springframework.stereotype.Service;

@Service
public class SoccerLineGrabber extends LineGrabber {
    public SoccerLineGrabber() {
        super(Constants.SOCCER_ID);
        allowedLeagues.add(2627L);
    }
}

