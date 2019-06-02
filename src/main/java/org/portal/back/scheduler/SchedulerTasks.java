package org.portal.back.scheduler;

import org.portal.back.grabber.SherdogLinkGrabber;
import org.portal.back.pinnacle.box.BoxLineGrabber;
import org.portal.back.pinnacle.darts.DartsLineGrabber;
import org.portal.back.pinnacle.darts.DartsLineGrabberLive;
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
   @Autowired
   DartsLineGrabber dartsLineGrabber;
   @Autowired
   DartsLineGrabberLive dartsLineGrabberLive;

    @Scheduled(fixedRate = 4*60*1000)
    public void grabTennisLine() {
        tennisLineGrabber.grab();
    }

    @Scheduled(fixedRate = 4*60*1000, initialDelay = 60*1000)
    public void grabSoccerLine() {
        soccerLineGrabber.grab();
    }

    @Scheduled(fixedRate = 4*60*1000, initialDelay = 2*60*1000)
    public void grabTennisLineLive() {
        tennisLineGrabberLive.grab();
    }

    @Scheduled(fixedRate = 4*60*1000, initialDelay = 3*60*1000)
    public void grabSoccerLineLive() {
        soccerLineGrabberLive.grab();
    }


    @Scheduled(fixedRate = 15*60*1000, initialDelay = 2*60*1000)
    public void grabMMALine() {
        mmaLineGrabber.grab();
    }



    @Scheduled(fixedRate = 30*60*1000, initialDelay = 3*60*1000)
    public void grabBoxLine() {
        boxLineGrabber.grab();
    }

    @Scheduled(fixedRate = 60*1000)
    public void grabSherdogLinks() {
        sherdogLinkGrabber.getSherdogUrl();
    }

   @Scheduled(fixedRate = 4*60*1000)
   public void grabDartsLine() {
       dartsLineGrabber.grab();
   }

   @Scheduled(fixedRate = 4*60*1000, initialDelay = 2*60*1000)
   public void grabDartsLineLive() {
        dartsLineGrabberLive.grab();
    }


}
