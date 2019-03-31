package org.portal.scheduler;

import org.portal.back.pinnacle.grabber.TennisMaxBetGrabber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TennisMaxBetGrabberTasks {
    @Autowired
    TennisMaxBetGrabber maxBetGrabber;

    @Scheduled(fixedDelay = 60*1000)
    public void grabTennisLine() {
        maxBetGrabber.grab();
    }
}