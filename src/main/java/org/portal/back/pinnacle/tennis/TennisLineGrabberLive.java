package org.portal.back.pinnacle.tennis;

import org.portal.back.pinnacle.Constants;
import org.portal.back.pinnacle.grabber.LineGrabber;
import org.springframework.stereotype.Service;

@Service
public class TennisLineGrabberLive extends LineGrabber {
    public TennisLineGrabberLive() {
        super(Constants.TENNIS_ID);
        setLive(true);
    }
}

