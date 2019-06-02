package org.portal.front.events.forms;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.portal.back.DataService;
import org.portal.back.model.Event;
import org.portal.back.pinnacle.Constants;
import org.portal.front.events.EventsDataProvider;
import org.portal.front.events.box_history.BoxEventsHistoryView;
import org.portal.front.events.tennis_history.TennisEventsHistoryView;

import java.util.Collection;

public class TennisSearchForm extends Div {
    private HorizontalLayout content;
    private TennisEventsHistoryView view;
    private TextField textField = new TextField();
    private DataService ds;

    public TennisSearchForm(TennisEventsHistoryView view, DataService ds) {
        this.ds = ds;
        this.view = view;
        setClassName("product-form");
        content = new HorizontalLayout();
        content.setSizeUndefined();
        textField.setWidth("300");

        Button button = new Button("Find Player");
        button.setWidth("300");
        button.addClickListener(event -> find());


        content.add(textField);
        content.add(button);
        add(content);
    }

    public void find() {
        if (textField.getValue().length() > 0) {
            Collection<Event> events = ds.getEventsByPlayer(textField.getValue(), Constants.TENNIS_ID);
            EventsDataProvider dataProvider = new EventsDataProvider(events);
            view.getEventGrid().setDataProvider(dataProvider);
        }
    }

}

