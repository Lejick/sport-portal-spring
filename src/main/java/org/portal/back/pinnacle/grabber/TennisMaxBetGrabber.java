package org.portal.back.pinnacle.grabber;

import org.portal.back.pinnacle.Constants;
import org.springframework.stereotype.Service;

@Service
public class TennisMaxBetGrabber extends MaxBetGrabber {
    public TennisMaxBetGrabber() {
        super(Constants.TENNIS_ID);
    }
}

