package org.portal.front.events;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.portal.back.DataService;
import org.portal.back.model.Event;
import org.portal.back.pinnacle.Constants;
import org.portal.front.events.box_history.BoxEventsHistoryView;
import org.portal.front.events.mma_history.MMAEventsHistoryView;

import java.util.Collection;

public class BoxSearchForm extends Div {
    private HorizontalLayout content;
    private BoxEventsHistoryView view;
    private TextField textField = new TextField();
    private DataService ds;

    public BoxSearchForm(BoxEventsHistoryView view, DataService ds) {
        this.ds = ds;
        this.view = view;
        setClassName("product-form");
        content = new HorizontalLayout();
        content.setSizeUndefined();
        textField.setWidth("300");

        Button button = new Button("Find Boxer");
        button.setWidth("300");
        button.addClickListener(event -> find());


        content.add(textField);
        content.add(button);
        add(content);
    }

    public void find() {
        if (textField.getValue().length() > 0) {
            Collection<Event> events = ds.getEventsByPlayer(textField.getValue(), Constants.BOXING_ID);
            EventsDataProvider dataProvider = new EventsDataProvider(events);
            view.getEventGrid().setDataProvider(dataProvider);
        }
    }

}

