package org.portal.scheduler;

import com.ftpix.sherdogparser.exceptions.SherdogParserException;
import org.portal.sherdog.DataGrabber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SherdogGrabberTasks {
    @Autowired
    DataGrabber dataGrabber;

    @Scheduled(fixedDelay = 24*60*1000)
    public void grabTennisLine() {
        try {
            dataGrabber.grab();
        } catch (SherdogParserException e) {
            e.printStackTrace();
        }
    }
}