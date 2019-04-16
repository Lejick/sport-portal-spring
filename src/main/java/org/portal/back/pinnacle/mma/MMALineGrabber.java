package org.portal.back.pinnacle.mma;

import org.portal.back.pinnacle.Constants;
import org.portal.back.pinnacle.grabber.LineGrabber;
import org.springframework.stereotype.Service;

@Service
public class MMALineGrabber extends LineGrabber {
    public MMALineGrabber() {
        super(Constants.MMA_ID);
    }
}

