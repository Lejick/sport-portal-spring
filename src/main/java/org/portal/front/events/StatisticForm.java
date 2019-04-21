package org.portal.front.events;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.portal.ContextProvider;
import org.portal.back.DataService;
import org.portal.back.model.Event;
import org.portal.front.leagues.LeaguesDataProvider;

public class StatisticForm extends Div {
    String stdWidth = "100px";
    EventsView eventsView;
    private VerticalLayout grid = new VerticalLayout();

    public StatisticForm(EventsView eventsView) {
        setClassName("product-form");
        grid.setSizeUndefined();
        add(grid);
        this.eventsView = eventsView;
    }

    public void showStat() {
        grid.removeAll();
        HorizontalLayout rows = new HorizontalLayout();
        Label homeHeader = new Label("Description");
        homeHeader.setWidth("700");

        Label contentHeader = new Label("Link");
        contentHeader.setWidth("700");

        Label userHeader = new Label("User");
        contentHeader.setWidth(stdWidth);


        rows.add(homeHeader);
        rows.add(contentHeader);
        rows.add(userHeader);
        grid.add(rows);
        rows = new HorizontalLayout();

        TextField descrField = new TextField();
        descrField.setWidth("700");
        TextField linkField = new TextField();
        linkField.setWidth("700");
        Button button = new Button("Add Note");
        button.setWidth(stdWidth);
        button.addClickListener(event -> createNote());
        rows.add(descrField, linkField, button);
        grid.add(rows);

    }

    public void createNote() {

    }
}



