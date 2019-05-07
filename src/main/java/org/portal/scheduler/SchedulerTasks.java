package org.portal.scheduler;

import org.portal.back.grabber.SherdogLinkGrabber;
import org.portal.back.pinnacle.box.BoxLineGrabber;
import org.portal.back.pinnacle.mma.MMALineGrabber;
import org.portal.back.pinnacle.soccer.SoccerLineGrabber;
import org.portal.back.pinnacle.soccer.SoccerLineGrabberLive;
import org.portal.back.pinnacle.tennis.TennisLineGrabber;
import org.portal.back.pinnacle.tennis.TennisLineGrabberLive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerTasks {
    @Autowired
    MMALineGrabber mmaLineGrabber;
    @Autowired
    BoxLineGrabber boxLineGrabber;
    @Autowired
    SoccerLineGrabber soccerLineGrabber;
    @Autowired
    TennisLineGrabber tennisLineGrabber;
    @Autowired
    SoccerLineGrabberLive soccerLineGrabberLive;
    @Autowired
    TennisLineGrabberLive tennisLineGrabberLive;
    @Autowired
    SherdogLinkGrabber sherdogLinkGrabber;

    @Scheduled(fixedDelay = 4*60*1000)
    public void grabTennisLine() {
        tennisLineGrabber.grab();
    }

    @Scheduled(fixedDelay = 4*60*1000, initialDelay = 60*1000)
    public void grabSoccerLine() {
        soccerLineGrabber.grab();
    }

    @Scheduled(fixedDelay = 4*60*1000, initialDelay = 2*60*1000)
    public void grabTennisLineLive() {
        tennisLineGrabberLive.grab();
    }

    @Scheduled(fixedDelay = 4*60*1000, initialDelay = 3*60*1000)
    public void grabSoccerLineLive() {
        soccerLineGrabberLive.grab();
    }


    @Scheduled(fixedDelay = 15*60*1000, initialDelay = 2*60*1000)
    public void grabMMALine() {
        mmaLineGrabber.grab();
    }



    @Scheduled(fixedDelay = 30*60*1000, initialDelay = 3*60*1000)
    public void grabBoxLine() {
        boxLineGrabber.grab();
    }

    @Scheduled(fixedDelay = 60*1000)
    public void grabSherdogLinks() {
        sherdogLinkGrabber.getSherdogUrl();
    }
}
