package org.portal.front.leagues.darts;

import com.vaadin.flow.component.UI;
import org.portal.back.model.League;
import org.portal.front.events.darts.DartsEventsView;
import org.portal.front.leagues.LeaguesLogic;

public class DartsLeaguesLogic extends LeaguesLogic{
    public void init() {
    }

    public void rowSelected(League league) {
        UI.getCurrent().navigate(DartsEventsView.class, league.getName());
    }
}
