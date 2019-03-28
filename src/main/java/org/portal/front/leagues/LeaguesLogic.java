package org.portal.model.leagues;

import org.portal.backend.data.model.League;
import com.vaadin.flow.component.UI;
import org.portal.model.events.EventsView;

import java.io.Serializable;

public class LeaguesLogic implements Serializable {


    public void init() {
    }

    public void rowSelected(League league) {
        UI.getCurrent().navigate(EventsView.class, league.getName());
    }
}
