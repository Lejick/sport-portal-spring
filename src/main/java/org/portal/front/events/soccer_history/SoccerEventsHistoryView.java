package org.portal.front.events.soccer_history;

import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;
import org.portal.MainLayout;
import org.portal.back.model.Event;
import org.portal.back.pinnacle.Constants;
import org.portal.front.events.EventsDataProvider;
import org.portal.front.events.EventsGrid;
import org.portal.front.events.EventsView;
import org.portal.front.leagues.StandartEventsGrid;

import java.util.Collection;

@Route(value = "Soccer_Events_History", layout = MainLayout.class)
public class SoccerEventsHistoryView extends EventsView {

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String leagueName) {
        if (leagueName != null) {
            Collection<Event> events = ds.getEventsHistory(leagueName, Constants.SOCCER_ID);
            grid.setDataProvider(new EventsDataProvider(events) {
            });
        }
        isHistory=true;
    }
    @Override
    protected EventsGrid getEventGrid() {
        return new StandartEventsGrid();
    }
}
