package org.portal.front.events;

import org.portal.back.model.Event;
import com.vaadin.flow.data.provider.ListDataProvider;

import java.util.Collection;

public class EventsDataProvider extends ListDataProvider<Event> {

    public EventsDataProvider(Collection<Event> items) {
        super(items);
    }
}
