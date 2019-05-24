package org.portal.back.pinnacle.darts;

import org.portal.back.pinnacle.Constants;
import org.portal.back.pinnacle.grabber.LineGrabber;
import org.springframework.stereotype.Service;

@Service
public class DartsLineGrabberLive extends LineGrabber {
    public DartsLineGrabberLive() {
        super(Constants.DARTS_ID);
        setLive(true);
    }
}
