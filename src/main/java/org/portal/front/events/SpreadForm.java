package org.portal.front.events;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.portal.back.model.Event;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

public class SpreadForm extends Div {
    String stdWidth = "100px";
    String dateWidth = "150px";
    Label spread;
    Label home;
    Label away;
    Label date;
    EventsView eventsView;
    private VerticalLayout content;

    public SpreadForm(EventsView eventsView) {
        setClassName("product-form");
        content = new VerticalLayout();
        content.setSizeUndefined();
        add(content);
        this.eventsView = eventsView;
    }

    public void showOdds(Event eventParam) {
        if (eventParam == null) {
            return;
        }
        content.removeAll();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Button mlButton = new Button("MoneyLine");
        mlButton.addClickListener(event -> changeToMoneyLine());

        Button totalButton = new Button("Total");
        totalButton.addClickListener(event -> changeToTotal());

        HorizontalLayout buttonBar = new HorizontalLayout(mlButton, totalButton);
        buttonBar.setWidth("100%");
        buttonBar.setFlexGrow(1, mlButton, totalButton);
        content.add(buttonBar);
        Label homeHeader = new Label("Spread");
        homeHeader.setWidth(stdWidth);
        Label maxHomeHeader = new Label("Home");
        maxHomeHeader.setWidth(stdWidth);
        Label awayHeader = new Label("Away");
        awayHeader.setWidth(stdWidth);
        Label dateHeader = new Label("Date(MSK)");
        dateHeader.setWidth(dateWidth);
        dateHeader.setWidth(stdWidth);

        HorizontalLayout horizontalLayout = new HorizontalLayout( homeHeader, maxHomeHeader, awayHeader, dateHeader);
        horizontalLayout.setWidth("100%");
        horizontalLayout.setFlexGrow(1,  homeHeader, awayHeader, dateHeader);
        content.add(horizontalLayout);
        List<CombineSpreadOdds> combineSpreadOddsList = CombineEventFactory.createSpreadOddsList(eventParam.getLineEvents());
        if (combineSpreadOddsList != null && combineSpreadOddsList.size() > 0) {
            for (int i = 0; i < combineSpreadOddsList.size(); i++) {
                BigDecimal nextSpread = null;
                BigDecimal nextHome = null;
                BigDecimal nextAway = null;
                if (i < combineSpreadOddsList.size() - 1) {
                    nextSpread = combineSpreadOddsList.get(i + 1).getSpread();
                    nextHome = combineSpreadOddsList.get(i + 1).getHomeOdds().getPrice();
                    nextAway = combineSpreadOddsList.get(i + 1).getAwayOdds().getPrice();
                }
                BigDecimal currentHome = combineSpreadOddsList.get(i).getHomeOdds().getPrice();
                BigDecimal currentAway = combineSpreadOddsList.get(i).getAwayOdds().getPrice();
                BigDecimal currentSpread = combineSpreadOddsList.get(i).getSpread();
                CombineSpreadOdds combineSpreadOdds = combineSpreadOddsList.get(i);
                date = new Label(format.format(combineSpreadOdds.getDate()));
                date.setWidth(dateWidth);

                spread = new Label(combineSpreadOdds.getSpread().toPlainString());
                home = new Label(combineSpreadOdds.getHomeOdds().getPrice().toPlainString());
                away = new Label(combineSpreadOdds.getAwayOdds().getPrice().toPlainString());

                home.getStyle().set("color", "black");
                home.setWidth(stdWidth);

                away.getStyle().set("color", "black");
                away.setWidth(stdWidth);

                if (nextSpread != null && currentSpread.compareTo(nextSpread) == 0 &&
                        nextHome.compareTo(currentHome) < 0) {
                    home.setText(home.getText() + "(" + currentHome.subtract(nextHome) + ")");
                    away.setText(away.getText() + "(" + currentAway.subtract(nextAway) + ")");
                    away.getStyle().set("color", "red");
                    home.getStyle().set("color", "green");
                }

                if (nextSpread != null && currentSpread.compareTo(nextSpread) == 0 &&
                        nextHome.compareTo(currentHome) > 0) {
                    home.setText(home.getText() + "(" + currentHome.subtract(nextHome) + ")");
                    away.setText(away.getText() + "(" + currentAway.subtract(nextAway) + ")");
                    home.getStyle().set("color", "red");
                    away.getStyle().set("color", "green");
                }

                horizontalLayout = new HorizontalLayout( spread, home, away, date);
                horizontalLayout.setWidth("100%");
                horizontalLayout.setFlexGrow(1, spread, home, away, date);
                content.add(horizontalLayout);

                if (nextSpread != null && !nextSpread.equals(combineSpreadOdds.getSpread())) {
                    date = new Label("-----");
                    date.setWidth(dateWidth);
                    spread = new Label("-----");
                    home = new Label("-----");
                    away = new Label("-----");
                    horizontalLayout = new HorizontalLayout( spread, home, away, date);
                    horizontalLayout.setWidth("100%");
                    horizontalLayout.setFlexGrow(1, spread, home, away, date);
                    content.add(horizontalLayout);
                }

            }
        }
    }

    private void changeToMoneyLine() {
        setVisible(false);
        eventsView.getTotalForm().setVisible(false);
        eventsView.getMoneyLineForm().setVisible(true);

    }

    private void changeToTotal() {
        setVisible(false);
        eventsView.getMoneyLineForm().setVisible(false);
        eventsView.getTotalForm().setVisible(true);

    }

}

