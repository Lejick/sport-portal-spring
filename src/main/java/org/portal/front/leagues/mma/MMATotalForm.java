package org.portal.front.leagues.mma;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.portal.back.model.Event;
import org.portal.front.events.CombineEventFactory;
import org.portal.front.events.CombineTotalsOdds;
import org.portal.front.events.EventsView;
import org.portal.front.events.forms.TotalForm;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

public class MMATotalForm extends TotalForm {

    public MMATotalForm(EventsView eventsView) {
        super(eventsView);
    }

    public void showOdds(Event eventParam) {
        if (eventParam == null) {
            return;
        }
        content.removeAll();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Button mlButton = new Button("Money Line");
        mlButton.addClickListener(event -> changeToMoneLine());

        HorizontalLayout buttonBar = new HorizontalLayout(mlButton);
        buttonBar.setWidth("100%");
        buttonBar.setFlexGrow(1, mlButton);
        content.add(buttonBar);
        Label homeHeader = new Label("Total Rounds");
        homeHeader.setWidth(stdWidth);
        Label maxHomeHeader = new Label("OVER");
        maxHomeHeader.setWidth(stdWidth);
        Label awayHeader = new Label("UNDER");
        awayHeader.setWidth(stdWidth);
        Label dateHeader = new Label("Date(MSK)");
        dateHeader.setWidth(dateWidth);
        dateHeader.setWidth(stdWidth);

        HorizontalLayout horizontalLayout = new HorizontalLayout(homeHeader, maxHomeHeader, awayHeader, dateHeader);
        horizontalLayout.setWidth("100%");
        horizontalLayout.setFlexGrow(1, homeHeader, awayHeader, dateHeader);
        content.add(horizontalLayout);
        List<CombineTotalsOdds> combineMoneyLineOddsList = CombineEventFactory.createTotalsOddsList(eventParam.getLineEvents());
        if (combineMoneyLineOddsList != null && combineMoneyLineOddsList.size() > 0) {
            for (int i = 0; i < combineMoneyLineOddsList.size(); i++) {
                BigDecimal nextTotal = null;
                BigDecimal nextUnder = null;
                BigDecimal nextOver = null;
                if (i < combineMoneyLineOddsList.size() - 1) {
                    nextTotal = combineMoneyLineOddsList.get(i + 1).getTotal();
                    nextUnder = combineMoneyLineOddsList.get(i + 1).getUnder().getPrice();
                    nextOver = combineMoneyLineOddsList.get(i + 1).getOver().getPrice();
                }
                BigDecimal currentUnder = combineMoneyLineOddsList.get(i).getUnder().getPrice();
                BigDecimal currentOver = combineMoneyLineOddsList.get(i).getOver().getPrice();
                BigDecimal currentTotal = combineMoneyLineOddsList.get(i).getTotal();
                CombineTotalsOdds combineTotalOdds = combineMoneyLineOddsList.get(i);
                date = new Label(format.format(combineTotalOdds.getDate()));
                date.setWidth(dateWidth);

                total = new Label(combineTotalOdds.getTotal().toPlainString());
                under = new Label(combineTotalOdds.getUnder().getPrice().toPlainString());
                over = new Label(combineTotalOdds.getOver().getPrice().toPlainString());

                over.getStyle().set("color", "black");
                over.setWidth(stdWidth);

                under.getStyle().set("color", "black");
                under.setWidth(stdWidth);

                if (nextTotal != null && currentTotal.compareTo(nextTotal) == 0 &&
                        nextOver.compareTo(currentOver) < 0) {
                    over.setText(over.getText() + "(" + currentOver.subtract(nextOver) + ")");
                    under.setText(under.getText() + "(" + currentUnder.subtract(nextUnder) + ")");
                    under.getStyle().set("color", "red");
                    over.getStyle().set("color", "green");
                }

                if (nextTotal != null && currentTotal.compareTo(nextTotal) == 0 &&
                        nextOver.compareTo(currentOver) > 0) {
                    over.setText(over.getText() + "(" + currentOver.subtract(nextOver) + ")");
                    under.setText(under.getText() + "(" + currentUnder.subtract(nextUnder) + ")");
                    over.getStyle().set("color", "red");
                    under.getStyle().set("color", "green");
                }

                horizontalLayout = new HorizontalLayout(total, over, under, date);
                horizontalLayout.setWidth("100%");
                horizontalLayout.setFlexGrow(1, total, over, under, date);
                content.add(horizontalLayout);

                if (nextTotal != null && !nextTotal.equals(combineTotalOdds.getTotal())) {
                    date = new Label("-----");
                    date.setWidth(dateWidth);
                    total = new Label("-----");
                    under = new Label("-----");
                    over = new Label("-----");

                    horizontalLayout = new HorizontalLayout(total, over, under, date);
                    horizontalLayout.setWidth("100%");
                    horizontalLayout.setFlexGrow(1, total, over, under, date);
                    content.add(horizontalLayout);
                }

            }
        }
    }

    private void changeToMoneLine() {
        setVisible(false);
        eventsView.getSpreadForm().setVisible(false);
        eventsView.getMoneyLineForm().setVisible(true);
    }
}

