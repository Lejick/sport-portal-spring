package org.portal.back.scheduler;

import com.ftpix.sherdogparser.exceptions.SherdogParserException;
import org.portal.back.sherdog.DataGrabber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SherdogGrabberTasks {
    @Autowired
    DataGrabber dataGrabber;

    @Scheduled(fixedRate = 5*60*60*1000)
    public void grabTennisLine() {
        try {
            dataGrabber.grab();
        } catch (SherdogParserException e) {
            e.printStackTrace();
        }
    }
}