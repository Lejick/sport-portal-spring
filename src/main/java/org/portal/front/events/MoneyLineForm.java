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

public class MoneyLineForm extends Div {
    String stdWidth = "100px";
    String dateWidth = "150px";
    Label home;
    Label max_home;
    Label away;
    Label max_away;
    Label date;
    EventsView eventsView;
    private VerticalLayout content;

    public MoneyLineForm(EventsView eventsView) {
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


        Button totalButton = new Button("Total");
        totalButton.addClickListener(event -> changeToTotal());
        Button spreadButton = new Button("Spread");
        spreadButton.addClickListener(event -> changeToSpread());

        HorizontalLayout buttonBar=new HorizontalLayout(totalButton,spreadButton);
        buttonBar.setWidth("100%");
        buttonBar.setFlexGrow(1, totalButton, spreadButton);
        content.add(buttonBar);
        Label homeHeader = new Label("Home");
        homeHeader.setWidth(stdWidth);
      //  Label maxHomeHeader = new Label("Max $ Home");
       // maxHomeHeader.setWidth(stdWidth);
        Label awayHeader = new Label("Away");
        awayHeader.setWidth(stdWidth);
      //  Label maxAwayHeader = new Label("Max $ Away");
      //  maxAwayHeader.setWidth(stdWidth);

        Label dateHeader = new Label("Actual_Date(MSK)");
        dateHeader.setWidth(dateWidth);
        HorizontalLayout horizontalLayout = new HorizontalLayout(homeHeader, awayHeader, dateHeader);
        horizontalLayout.setWidth("100%");
        horizontalLayout.setFlexGrow(1, homeHeader, awayHeader, dateHeader);
        content.add(horizontalLayout);
        List<CombineMoneyLineOdds> combineMoneyLineOddsList = CombineEventFactory.createMoneyLineOddsList(eventParam.getLineEvents());
        if (combineMoneyLineOddsList != null && combineMoneyLineOddsList.size() > 0) {
            for (int i = 0; i < combineMoneyLineOddsList.size(); i++) {
                BigDecimal homeNextPrice = null;
                BigDecimal awayNextPrice = null;
                BigDecimal homeNextMax = null;
                BigDecimal awayNextMax = null;
                if (i < combineMoneyLineOddsList.size() - 1) {
                    homeNextPrice = combineMoneyLineOddsList.get(i + 1).getHomeOdds().getPrice();
                    awayNextPrice = combineMoneyLineOddsList.get(i + 1).getAwayOdds().getPrice();
                    homeNextMax = combineMoneyLineOddsList.get(i + 1).getHomeOdds().getMax_bet();
                    awayNextMax = combineMoneyLineOddsList.get(i + 1).getAwayOdds().getMax_bet();
                }
                CombineMoneyLineOdds combineMoneyLineOdds = combineMoneyLineOddsList.get(i);
                BigDecimal homeMax = combineMoneyLineOdds.getHomeOdds().getMax_bet();
                BigDecimal awayMax = combineMoneyLineOdds.getAwayOdds().getMax_bet();

                BigDecimal homePrice = combineMoneyLineOdds.getHomeOdds().getPrice();
                BigDecimal awayPrice = combineMoneyLineOdds.getAwayOdds().getPrice();

                date = new Label(format.format(combineMoneyLineOdds.getDate()));
                date.setWidth(dateWidth);

                max_home = new Label(homeMax != null ? String.valueOf(homeMax) : "");
                max_away = new Label(awayMax != null ? String.valueOf(awayMax) : "");
                max_home.getStyle().set("color", "black");
                max_home.setWidth(stdWidth);

                home = new Label(String.valueOf(homePrice));
                home.setWidth(stdWidth);
                home.getStyle().set("color", "black");

                away = new Label(String.valueOf(awayPrice));
                away.setWidth(stdWidth);


                max_away.getStyle().set("color", "black");
                max_away.setWidth(stdWidth);

                if (homeNextPrice != null && homePrice.compareTo(homeNextPrice) < 0) {
                    home.setText(home.getText() + "(" + homePrice.subtract(homeNextPrice) + ")");
                    home.getStyle().set("color", "red");
                }

                if (homeNextPrice != null && homePrice.compareTo(homeNextPrice) > 0) {
                    home.setText(home.getText() + "(" + homePrice.subtract(homeNextPrice) + ")");
                    home.getStyle().set("color", "green");
                }

                if (awayNextPrice != null && awayPrice.compareTo(awayNextPrice) < 0) {
                    away.setText(away.getText() + "(" + awayPrice.subtract(awayNextPrice) + ")");
                    away.getStyle().set("color", "red");
                }

                if (awayNextPrice != null && awayPrice.compareTo(awayNextPrice) > 0) {
                    away.setText(away.getText() + "(" + awayPrice.subtract(awayNextPrice) + ")");
                    away.getStyle().set("color", "green");
                }

                if (homeNextMax != null && homeMax != null && homeMax.compareTo(homeNextMax) < 0) {
                    max_home.getStyle().set("color", "red");
                }

                if (homeNextMax != null && homeMax != null && homeMax.compareTo(homeNextMax) > 0) {
                    max_home.getStyle().set("color", "green");
                }


                if (awayNextMax != null && awayMax != null && awayMax.compareTo(awayNextMax) < 0) {
                    max_away.getStyle().set("color", "red");
                }

                if (awayNextMax != null && awayMax != null && awayMax.compareTo(awayNextMax) > 0) {
                    max_away.getStyle().set("color", "green");
                }


                horizontalLayout = new HorizontalLayout(home, max_home, away, max_away, date);
                horizontalLayout.setWidth("100%");
                horizontalLayout.setFlexGrow(1, home, max_home, away, max_away, date);
                content.add(horizontalLayout);
            }
        }
    }

    private void changeToTotal() {
        setVisible(false);
        eventsView.getSpreadForm().setVisible(false);
        eventsView.getTotalForm().setVisible(true);

    }

    private void changeToSpread() {
        setVisible(false);
        eventsView.getTotalForm().setVisible(false);
        eventsView.getSpreadForm().setVisible(true);

    }

}

