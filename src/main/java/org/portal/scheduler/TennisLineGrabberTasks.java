package org.portal.scheduler;

import org.portal.back.pinnacle.grabber.TennisLineGrabber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TennisLineGrabberTasks {
    @Autowired
    TennisLineGrabber tennisLineGrabber;

    @Scheduled(fixedDelay = 60*1000)
    public void grabTennisLine() {
        tennisLineGrabber.grab();
    }
}