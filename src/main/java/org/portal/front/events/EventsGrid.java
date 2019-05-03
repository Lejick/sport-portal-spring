package org.portal.front.events;

import com.vaadin.flow.component.grid.Grid;
import org.portal.back.model.Event;

/**
 * Grid of products, handling the visual presentation and filtering of a set of
 * items. This version uses an in-memory data source that is suitable for small
 * data sets.
 */
public abstract class EventsGrid extends Grid<Event> {
    protected boolean isHistory=false;

    public void setHistory(boolean isHistory) {
        this.isHistory = isHistory;
    }
}
