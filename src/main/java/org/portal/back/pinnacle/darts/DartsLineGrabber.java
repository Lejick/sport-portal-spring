package org.portal.back.pinnacle.darts;

import org.portal.back.pinnacle.Constants;
import org.portal.back.pinnacle.grabber.LineGrabber;
import org.springframework.stereotype.Service;


@Service
public class DartsLineGrabber extends LineGrabber {
    public DartsLineGrabber() {
        super(Constants.DARTS_ID);
    }
}
