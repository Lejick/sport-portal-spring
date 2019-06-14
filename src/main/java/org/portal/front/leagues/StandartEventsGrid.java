package org.portal.front.leagues;

import com.vaadin.flow.function.ValueProvider;
import org.portal.back.model.Event;
import org.portal.front.events.EventsGrid;

public class StandartEventsGrid  extends EventsGrid {
    public StandartEventsGrid() {
            setSizeFull();
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
            addColumn((ValueProvider<Event, Object>) event -> {
                boolean live = event.isLive();
                if (live) {
                    return "live!";
                } else {
                    return "pre match";
                }
            })
                        .setHeader("Live?")
                        .setFlexGrow(20)
                        .setSortable(false);
            }

    }
}
