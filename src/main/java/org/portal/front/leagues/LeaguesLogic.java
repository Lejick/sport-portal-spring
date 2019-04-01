package org.portal.front.leagues;

import org.portal.back.model.League;
import com.vaadin.flow.component.UI;
import org.portal.front.events.EventsView;

import java.io.Serializable;

public abstract class LeaguesLogic implements Serializable {


    public void init() {
    }

    public void rowSelected(League league) {
        UI.getCurrent().navigate(EventsView.class, league.getName());
    }
}
