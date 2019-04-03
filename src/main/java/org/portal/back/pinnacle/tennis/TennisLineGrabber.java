package org.portal.back.pinnacle.tennis;

import org.portal.back.pinnacle.Constants;
import org.portal.back.pinnacle.grabber.LineGrabber;
import org.springframework.stereotype.Service;

@Service
public class TennisLineGrabber extends LineGrabber {
    public TennisLineGrabber() {
        super(Constants.TENNIS_ID);
    }
}

