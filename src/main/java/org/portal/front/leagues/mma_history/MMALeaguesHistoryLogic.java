package org.portal.front.leagues.mma_history;

import com.vaadin.flow.component.UI;
import org.portal.back.model.League;
import org.portal.front.events.mma_history.MMAEventsHistoryView;
import org.portal.front.events.tennis_history.TennisEventsHistoryView;
import org.portal.front.leagues.LeaguesLogic;

public class MMALeaguesHistoryLogic extends LeaguesLogic {


    public void init() {
    }

    public void rowSelected(League league) {
        UI.getCurrent().navigate(MMAEventsHistoryView.class, league.getName());
    }
}
