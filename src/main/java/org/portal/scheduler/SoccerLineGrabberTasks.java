package org.portal.scheduler;

import org.portal.back.pinnacle.soccer.SoccerLineGrabber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SoccerLineGrabberTasks {
    @Autowired
    SoccerLineGrabber soccerLineGrabber;

    @Scheduled(fixedDelay = 120*1000, initialDelay = 60*1000)
    public void grabTennisLine() {
        soccerLineGrabber.grab();
    }
}