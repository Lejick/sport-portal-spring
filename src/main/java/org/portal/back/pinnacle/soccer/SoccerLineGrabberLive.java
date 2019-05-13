package org.portal.back.pinnacle.soccer;

import org.portal.back.pinnacle.Constants;
import org.portal.back.pinnacle.grabber.LineGrabber;
import org.springframework.stereotype.Service;

@Service
public class SoccerLineGrabberLive extends LineGrabber {
    public SoccerLineGrabberLive() {
        super(Constants.SOCCER_ID);
        setLive(true);
        allowedLeagues.add(2627L);
    }
}

