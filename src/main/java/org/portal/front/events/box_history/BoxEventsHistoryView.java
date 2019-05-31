package org.portal.front.events.box_history;

import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;
import org.portal.MainLayout;
import org.portal.back.model.Event;
import org.portal.back.pinnacle.Constants;
import org.portal.front.events.*;
import org.portal.front.leagues.mma.MMAEventsGrid;
import org.portal.front.leagues.mma.MMAMoneyLineForm;
import org.portal.front.leagues.mma.MMATotalForm;

import java.util.Collection;

@Route(value = "Box_Events_History", layout = MainLayout.class)
public class BoxEventsHistoryView extends EventsView {

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String leagueName) {
        if (leagueName != null) {
            Collection<Event> events = ds.getEventsHistory(leagueName, Constants.BOXING_ID);
            grid.setDataProvider(new EventsDataProvider(events) {
            });
        }
        isHistory=true;
    }

    @Override
    protected void putSearchForm() {
        BoxSearchForm searchForm = new BoxSearchForm(this,ds);
        searchForm.setVisible(true);
        barAndGridLayout.add(searchForm);
        add(barAndGridLayout);
    }

    @Override
    protected void initForms() {
        moneyLineForm = new MMAMoneyLineForm(this);
        totalForm = new MMATotalForm(this);
        spreadForm = new SpreadForm(this);
        personalNotesForm=new PersonalNotesForm(noteRepository,this);
        linksForm = new LinksForm(noteRepository, this);
        autoLinksForm = new AutoLinksForm(noteRepository, this);
        notesForm = new NotesForm(noteRepository, this);
    }

    @Override
    public EventsGrid getEventGrid() {
        if (grid == null) {
            return new MMAEventsGrid();
        }
        return grid;
    }
}
