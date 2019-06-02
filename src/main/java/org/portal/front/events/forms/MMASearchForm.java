package org.portal.front.events.forms;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.portal.ContextProvider;
import org.portal.back.DataService;
import org.portal.back.model.Event;
import org.portal.back.pinnacle.Constants;
import org.portal.front.events.EventsDataProvider;
import org.portal.front.events.mma.MMAEventsView;
import org.portal.front.events.mma_history.MMAEventsHistoryView;
import org.portal.front.leagues.LeaguesDataProvider;
import org.portal.front.leagues.LeaguesView;

import java.util.ArrayList;
import java.util.Collection;

public class MMASearchForm extends Div {
    private HorizontalLayout content;
    private MMAEventsHistoryView view;
    private TextField textField = new TextField();
    private DataService ds;

    public MMASearchForm(MMAEventsHistoryView view, DataService ds) {
        this.ds = ds;
        this.view = view;
        setClassName("product-form");
        content = new HorizontalLayout();
        content.setSizeUndefined();
        textField.setWidth("300");

        Button button = new Button("Find Fighter");
        button.setWidth("300");
        button.addClickListener(event -> find());

        Button buttonEvent = new Button("Find Event");
        buttonEvent.setWidth("300");
        buttonEvent.addClickListener(event -> findEvent());


        content.add(textField);
        content.add(button);
        content.add(buttonEvent);

        add(content);
    }

    public void find() {
        if (textField.getValue().length() > 0) {
            Collection<Event> events = ds.getEventsByPlayer(textField.getValue(), Constants.MMA_ID);
            EventsDataProvider dataProvider = new EventsDataProvider(events);
            view.getEventGrid().setDataProvider(dataProvider);
        }
    }

    public void findEvent() {
        if (textField.getValue().length() > 0) {
            Collection<Event> events = ds.getEventsByAlterTitle(textField.getValue(), Constants.MMA_ID);
            EventsDataProvider dataProvider = new EventsDataProvider(events);
            view.getEventGrid().setDataProvider(dataProvider);
        }
    }

}

