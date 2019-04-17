package org.portal.back.pinnacle.box;

import org.portal.back.pinnacle.Constants;
import org.portal.back.pinnacle.grabber.LineGrabber;
import org.springframework.stereotype.Service;

@Service
public class BoxLineGrabber extends LineGrabber {
    public BoxLineGrabber() {
        super(Constants.BOXING_ID);
    }
}

