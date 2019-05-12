package org.portal.front.leagues.mma;

import org.portal.back.model.Event;
import org.portal.front.events.EventsGrid;

public class StandartEventsGrid  extends EventsGrid {
    public StandartEventsGrid() {
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
}
