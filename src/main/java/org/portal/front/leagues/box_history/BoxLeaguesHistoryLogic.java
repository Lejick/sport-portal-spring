package org.portal.front.leagues.box_history;

import com.vaadin.flow.component.UI;
import org.portal.back.model.League;
import org.portal.front.events.box_history.BoxEventsHistoryView;
import org.portal.front.leagues.LeaguesLogic;

public class BoxLeaguesHistoryLogic extends LeaguesLogic {


    public void init() {
    }

    public void rowSelected(League league) {
        UI.getCurrent().navigate(BoxEventsHistoryView.class, league.getName());
    }
}
