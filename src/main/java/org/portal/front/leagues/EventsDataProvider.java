package org.portal.front.leagues;

import com.vaadin.flow.data.provider.ListDataProvider;
import org.portal.back.model.Event;
import org.portal.back.model.League;

import java.util.Collection;


public class EventsDataProvider extends ListDataProvider<Event> {
    public EventsDataProvider(Collection<Event> items) {
        super(items);
    }

}
