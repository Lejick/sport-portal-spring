package org.portal.front.leagues.box;

import com.vaadin.flow.component.UI;
import org.portal.back.model.League;
import org.portal.front.events.box.BoxEventsView;
import org.portal.front.events.mma.MMAEventsView;
import org.portal.front.leagues.LeaguesLogic;

public class BoxLeaguesLogic extends LeaguesLogic {


    public void init() {
    }

    public void rowSelected(League league) {
        UI.getCurrent().navigate(BoxEventsView.class, league.getName());
    }
}
