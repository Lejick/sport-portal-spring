package org.portal.front.leagues.soccer;

import com.vaadin.flow.component.UI;
import org.portal.back.model.League;
import org.portal.front.events.soccer.SoccerEventsView;
import org.portal.front.leagues.LeaguesLogic;

public class SoccerLeaguesLogic extends LeaguesLogic {


    public void init() {
    }

    public void rowSelected(League league) {
        UI.getCurrent().navigate(SoccerEventsView.class, league.getName());
    }
}
