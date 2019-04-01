package org.portal.front.leagues.tennis_history;

import com.vaadin.flow.component.UI;
import org.portal.back.model.League;
import org.portal.front.events.tennis_history.TennisEventsHistoryView;
import org.portal.front.leagues.LeaguesLogic;

public class TennisLeaguesHistoryLogic extends LeaguesLogic {


    public void init() {
    }

    public void rowSelected(League league) {
        UI.getCurrent().navigate(TennisEventsHistoryView.class, league.getName());
    }
}
