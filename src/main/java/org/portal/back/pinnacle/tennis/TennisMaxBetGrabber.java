package org.portal.back.pinnacle.tennis;

import org.portal.back.pinnacle.Constants;
import org.portal.back.pinnacle.grabber.MaxBetGrabber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TennisMaxBetGrabber extends MaxBetGrabber {
    private final Logger LOGGER = LoggerFactory.getLogger(TennisMaxBetGrabber.class);
    public TennisMaxBetGrabber() {
        super(Constants.TENNIS_ID);
    }

    public  Logger getLogger(){
        return LOGGER;
    }
}

