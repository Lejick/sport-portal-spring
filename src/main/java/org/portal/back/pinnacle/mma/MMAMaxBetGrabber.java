package org.portal.back.pinnacle.mma;

import org.portal.back.pinnacle.Constants;
import org.portal.back.pinnacle.grabber.MaxBetGrabber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MMAMaxBetGrabber extends MaxBetGrabber {
    private final Logger LOGGER = LoggerFactory.getLogger(MMAMaxBetGrabber.class);
    public MMAMaxBetGrabber() {
        super(Constants.MMA_ID);
    }

    public  Logger getLogger(){
        return LOGGER;
    }
}

