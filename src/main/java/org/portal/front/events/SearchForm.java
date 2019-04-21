package org.portal.front.events;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import org.portal.ContextProvider;
import org.portal.back.DataService;
import org.portal.back.model.Event;
import org.portal.back.pinnacle.Constants;
import org.portal.front.leagues.LeaguesDataProvider;
import org.portal.front.leagues.LeaguesView;

public class SearchForm extends Div {
    String stdWidth = "100px";
    private HorizontalLayout content;
    private LeaguesView view;
    private TextField textField = new TextField();

    public SearchForm(LeaguesView view) {
        this.view = view;
        setClassName("product-form");
        content = new HorizontalLayout();
        content.setSizeUndefined();
        textField.setWidth("300");

        Button button = new Button("Find");
        button.setWidth(stdWidth);
        button.addClickListener(event -> find());
        content.add(textField);
        content.add(button);

        add(content);
    }

    public void find() {
        if (textField.getValue().length() > 0) {
            LeaguesDataProvider dataProvider = new LeaguesDataProvider(ContextProvider.getBean(DataService.class).
                    findLeagueByName(view.getSportId(),textField.getValue()));
            view.getGrid().setDataProvider(dataProvider);
        }
    }
}

