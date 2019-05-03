package org.portal.front.leagues.mma;

import org.portal.back.model.Event;
import org.portal.front.events.EventsGrid;

/**
 * Grid of products, handling the visual presentation and filtering of a set of
 * items. This version uses an in-memory data source that is suitable for small
 * data sets.
 */
public class MMAEventsGrid extends EventsGrid {
    private boolean isHistory=false;
    public MMAEventsGrid() {
        setSizeUndefined();
        addColumn(Event::getHome)
                .setHeader("Fighter 1")
                .setFlexGrow(20)
                .setSortable(true);
        addColumn(Event::getAway)
                .setHeader("Fighter 2")
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
    }

    public void setHistory(boolean isHistory) {
        this.isHistory = isHistory;
    }
}
