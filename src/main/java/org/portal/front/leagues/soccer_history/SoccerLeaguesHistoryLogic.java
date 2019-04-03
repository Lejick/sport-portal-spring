package org.portal.front.leagues.soccer_history;

import com.vaadin.flow.component.UI;
import org.portal.back.model.League;
import org.portal.front.events.soccer_history.SoccerEventsHistoryView;
import org.portal.front.leagues.LeaguesLogic;

public class SoccerLeaguesHistoryLogic extends LeaguesLogic {


    public void init() {
    }

    public void rowSelected(League league) {
        UI.getCurrent().navigate(SoccerEventsHistoryView.class, league.getName());
    }
}
