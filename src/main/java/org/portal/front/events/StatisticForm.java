package org.portal.front.events;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.portal.back.model.Event;

public class StatisticForm extends Div {
    String stdWidth = "100px";
    EventsView eventsView;
    private HorizontalLayout content;

    public StatisticForm(EventsView eventsView) {
        setClassName("product-form");
        content = new HorizontalLayout();
        content.setSizeUndefined();
        add(content);
        this.eventsView = eventsView;
    }
    public void showStat(Event eventParam) {

        if (eventParam == null) {
            return;
        }

        content.removeAll();
        if (eventParam == null) {
            return;
        }
        Label homeHeader = new Label("STAT");
        homeHeader.setWidth(stdWidth);

        Label contentHeader = new Label("CONTENT");
        contentHeader.setWidth(stdWidth);

        content.add(homeHeader);
        content.add(contentHeader);
    }


}

