package org.portal.back.pinnacle.darts;

import org.portal.back.pinnacle.Constants;
import org.portal.back.pinnacle.grabber.MaxBetGrabber;
import org.portal.back.pinnacle.tennis.TennisMaxBetGrabber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


public class DartsMaxBetGrabber extends MaxBetGrabber {
    private final Logger LOGGER = LoggerFactory.getLogger(DartsMaxBetGrabber.class);
    public DartsMaxBetGrabber() {
        super(Constants.DARTS_ID);
    }

    public  Logger getLogger(){
        return LOGGER;
    }
}
