package org.portal.front.events.tennis_history;

import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;
import org.portal.MainLayout;
import org.portal.back.model.Event;
import org.portal.back.pinnacle.Constants;
import org.portal.front.events.*;
import org.portal.front.leagues.StandartEventsGrid;

import java.util.Collection;

@Route(value = "Tennis_Events_History", layout = MainLayout.class)
public class TennisEventsHistoryView extends EventsView {

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String leagueName) {
        if (leagueName != null) {
            Collection<Event> events = ds.getEventsHistory(leagueName, Constants.TENNIS_ID);
            grid.setDataProvider(new EventsDataProvider(events) {
            });
        }
        isHistory=true;
    }

    @Override
    protected void putSearchForm() {
        TennisSearchForm searchForm = new TennisSearchForm(this, ds);
        searchForm.setVisible(true);
        barAndGridLayout.add(searchForm);
        add(barAndGridLayout);
    }

    @Override
    public EventsGrid getEventGrid() {
        if (grid == null) {
            grid = new StandartEventsGrid();
        }
        return grid;
    }
}
