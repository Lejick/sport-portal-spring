package org.portal.back.pinnacle.grabber;

import org.portal.back.pinnacle.Constants;
import org.springframework.stereotype.Service;

@Service
public class TennisLineGrabber extends LineGrabber {
    public TennisLineGrabber() {
        super(Constants.TENNIS_ID);
    }
}

