package org.portal.front.events;

import com.vaadin.flow.component.grid.Grid;
import org.portal.back.model.Event;

/**
 * Grid of products, handling the visual presentation and filtering of a set of
 * items. This version uses an in-memory data source that is suitable for small
 * data sets.
 */
public class EventsGrid extends Grid<Event> {
    private boolean isHistory=false;
    public EventsGrid() {
        setSizeUndefined();
        addColumn(Event::getHome)
                .setHeader("Home")
                .setFlexGrow(20)
                .setSortable(true);
        addColumn(Event::getAway)
                .setHeader("Away")
                .setFlexGrow(20)
                .setSortable(true);
        addColumn(Event::getStartsFormat)
                .setHeader("Start Date(MSK)")
                .setFlexGrow(20)
                .setSortable(true);
        addColumn(Event::getLeague_name)
                .setHeader("League")
                .setFlexGrow(20)
                .setSortable(false);
        if (!isHistory) {
            addColumn(Event::isLive)
                    .setHeader("Live")
                    .setFlexGrow(20)
                    .setSortable(false);
        }
    }

    public void setHistory(boolean isHistory) {
        this.isHistory = isHistory;
    }
}
