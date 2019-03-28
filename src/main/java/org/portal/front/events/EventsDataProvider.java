package org.portal.model.events;

import org.portal.backend.data.model.Event;
import com.vaadin.flow.data.provider.ListDataProvider;

import java.util.Collection;

public class EventsDataProvider extends ListDataProvider<Event> {

    public EventsDataProvider(Collection<Event> items) {
        super(items);
    }
}
