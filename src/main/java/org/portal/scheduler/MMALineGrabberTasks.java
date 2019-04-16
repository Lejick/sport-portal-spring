package org.portal.scheduler;

import org.portal.back.pinnacle.mma.MMALineGrabber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MMALineGrabberTasks {
    @Autowired
    MMALineGrabber mmaLineGrabber;

    @Scheduled(fixedDelay = 180*1000, initialDelay = 120*1000)
    public void grabSoccerLine() {
        mmaLineGrabber.grab();
    }
}