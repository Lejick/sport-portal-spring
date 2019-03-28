package org.portal.front.events;


import org.portal.back.model.Event;

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
