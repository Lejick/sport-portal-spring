package org.portal.back.pinnacle.soccer;

import org.portal.back.pinnacle.Constants;
import org.portal.back.pinnacle.grabber.MaxBetGrabber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SoccerMaxBetGrabber extends MaxBetGrabber {
    private final Logger LOGGER = LoggerFactory.getLogger(SoccerMaxBetGrabber.class);
    public SoccerMaxBetGrabber() {
        super(Constants.SOCCER_ID);
    }

    public  Logger getLogger(){
        return LOGGER;
    }
}

