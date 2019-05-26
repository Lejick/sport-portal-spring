package org.portal.front.leagues.darts_history;

import com.vaadin.flow.component.UI;
import org.portal.back.model.League;
import org.portal.front.events.darts_history.DartsEventsHistoryView;
import org.portal.front.leagues.LeaguesLogic;

public class DartsLeaguesHistoryLogic extends LeaguesLogic {
    public void init() {
    }

    public void rowSelected(League league) {
        UI.getCurrent().navigate(DartsEventsHistoryView.class, league.getName());
    }
}
