package org.portal.front.leagues.tennis;

import com.vaadin.flow.component.UI;
import org.portal.back.model.League;
import org.portal.front.events.tennis.TennisEventsView;
import org.portal.front.leagues.LeaguesLogic;

public class TennisLeaguesLogic extends LeaguesLogic {


    public void init() {
    }

    public void rowSelected(League league) {
        UI.getCurrent().navigate(TennisEventsView.class, league.getName());
    }
}
