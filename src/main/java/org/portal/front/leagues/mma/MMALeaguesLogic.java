package org.portal.front.leagues.mma;

import com.vaadin.flow.component.UI;
import org.portal.back.model.League;
import org.portal.front.events.mma.MMAEventsView;
import org.portal.front.leagues.LeaguesLogic;

public class MMALeaguesLogic extends LeaguesLogic {


    public void init() {
    }

    public void rowSelected(League league) {
        UI.getCurrent().navigate(MMAEventsView.class, league.getName());
    }
}
