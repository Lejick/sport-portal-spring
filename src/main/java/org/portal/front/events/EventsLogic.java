package org.portal.model.events;


import org.portal.backend.data.model.Event;

import java.io.Serializable;

public class EventsLogic implements Serializable {
   private EventsView view;

    public EventsLogic(EventsView view) {
        this.view=view;
    }

    public void init() {
    }

    public void rowSelected(Event event) {
        view.showOdds(event);
    }
}
