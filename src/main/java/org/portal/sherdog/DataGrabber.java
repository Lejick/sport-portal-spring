package org.portal.sherdog;

import com.ftpix.sherdogparser.Constants;
import com.ftpix.sherdogparser.Sherdog;
import com.ftpix.sherdogparser.exceptions.SherdogParserException;
import com.ftpix.sherdogparser.models.*;
import com.ftpix.sherdogparser.models.Event;
import com.ftpix.sherdogparser.models.Fight;
import com.ftpix.sherdogparser.models.Fighter;
import com.ftpix.sherdogparser.parsers.FighterParser;
import org.portal.back.model.sherdog.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.time.ZoneId;
import java.util.List;

/**
 * Created by Oleg on 31.01.2018.
 */
@Component
public class DataGrabber {
    @Autowired
    EventModelRepository eventModelRepository;

    @Autowired
    FighterRepository fighterRepository;

    @Autowired
    FightRepository fightRepository;

    static Sherdog parser = new Sherdog.Builder().withTimezone(Constants.SHERDOG_TIME_ZONE).build();
    static FighterParser fighterParser = new FighterParser(Constants.DEFAULT_PICTURE_PROCESSOR, ZoneId.of(Constants.SHERDOG_TIME_ZONE));


    private static Organizations currentOrganisation = Organizations.UFC;

    public void grab() throws SherdogParserException {

        for (Organizations organizations : Organizations.values()) {


            Organization org = getOrganisation(organizations);
            for (int i = 0; i < org.getEvents().size(); i++) {
                com.ftpix.sherdogparser.models.Event event = org.getEvents().get(i);
                List<EventModel> eventModelList = eventModelRepository.findByEventNameContaining(event.getName());
                if ((eventModelList).isEmpty()) {

                    try {
                        printEvent(event, organizations);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private Organization getOrganisation(Organizations org) throws SherdogParserException {
        Organization organization = null;
        try {
            organization = parser.getOrganization(org.url);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return organization;
    }

    public void printEvent(Event event, Organizations organizations) throws IOException, ParseException, SherdogParserException {
        EventModel eventModel = new EventModel();
        eventModel.setEventName(event.getName());
        eventModel.setSherdogUrl(event.getSherdogUrl());
        eventModel.setOrgId(organizations.id);
        eventModel.setStartDate(Date.valueOf(event.getDate().toLocalDate()));
        eventModelRepository.save(eventModel);
        Event parsedEvent = parser.getEvent(event.getSherdogUrl());
        for (int i = 0; i < parsedEvent.getFights().size(); i++) {
            Fight fight = parsedEvent.getFights().get(i);
            Fighter fighter1 = fighterParser.parse(fight.getFighter1().getSherdogUrl());
            Fighter fighter2 = fighterParser.parse(fight.getFighter2().getSherdogUrl());
            org.portal.back.model.sherdog.Fighter savedFighter1 = saveFighter(fighter1);
            org.portal.back.model.sherdog.Fighter savedFighter2 = saveFighter(fighter2);
            saveFight(fight, eventModel, savedFighter1, savedFighter2);
        }
    }

    private org.portal.back.model.sherdog.Fighter saveFighter(Fighter fighter) {
        org.portal.back.model.sherdog.Fighter dbFighter = new org.portal.back.model.sherdog.Fighter(fighter);
       List<org.portal.back.model.sherdog.Fighter> fList= fighterRepository.findBySherdogUrlContaining(fighter.getSherdogUrl());
        if (fList.isEmpty()) {
            fighterRepository.save(dbFighter);
        } else {
            dbFighter=fList.get(0);
        }
        return dbFighter;
    }

    private void saveFight(Fight fight, EventModel eventModel, org.portal.back.model.sherdog.Fighter fighter1, org.portal.back.model.sherdog.Fighter fighter2) {
        org.portal.back.model.sherdog.Fight dbFighter = new org.portal.back.model.sherdog.Fight(fight, eventModel, fighter1, fighter2);
        fightRepository.save(dbFighter);

    }

}
