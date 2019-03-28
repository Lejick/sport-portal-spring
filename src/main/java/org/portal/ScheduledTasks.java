package org.portal;

import org.portal.back.pinnacle.grabber.TennisLineGrabber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    @Autowired
    TennisLineGrabber tennisLineGrabber;

    @Scheduled(fixedDelay = 60*1000)
    public void reportCurrentTime() {
        tennisLineGrabber.grab();
    }
}