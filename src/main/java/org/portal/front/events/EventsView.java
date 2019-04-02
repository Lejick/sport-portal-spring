package org.portal.front.events;

import org.portal.ContextProvider;
import org.portal.MainLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.portal.back.DataService;
import org.portal.back.model.Event;
import org.portal.back.pinnacle.Constants;

import java.util.Collection;


public abstract class EventsView extends HorizontalLayout
        implements HasUrlParameter<String> {
    protected EventsLogic viewLogic = new EventsLogic(this);
    protected EventsGrid grid;
    protected MoneyLineForm moneyLineForm;
    protected TotalForm totalForm;
    protected SpreadForm spreadForm;
    protected DataService ds = ContextProvider.getBean(DataService.class);



    public void showOdds(Event eventModelTennis) {
        moneyLineForm.setVisible(true);
        moneyLineForm.showOdds(eventModelTennis);
        spreadForm.setVisible(false);
        spreadForm.showOdds(eventModelTennis);
        totalForm.setVisible(false);
        totalForm.showOdds(eventModelTennis);

    }

    public EventsView() {
        setSizeFull();
        grid = new EventsGrid();
        grid.asSingleSelect().addValueChangeListener(event -> viewLogic.rowSelected(event.getValue()));
        moneyLineForm = new MoneyLineForm(this);
        totalForm = new TotalForm(this);
        spreadForm = new SpreadForm(this);
        VerticalLayout barAndGridLayout = new VerticalLayout();
        barAndGridLayout.add(grid);
        barAndGridLayout.setFlexGrow(1, grid);
        barAndGridLayout.setSizeFull();
        barAndGridLayout.expand(grid);
        add(barAndGridLayout);
        add(totalForm, moneyLineForm,spreadForm);
        viewLogic.init();
    }

    public MoneyLineForm getMoneyLineForm() {
        return moneyLineForm;
    }

    public SpreadForm getSpreadForm() {
        return spreadForm;
    }

    public void setMoneyLineForm(MoneyLineForm moneyLineForm) {
        this.moneyLineForm = moneyLineForm;
    }

    public TotalForm getTotalForm() {
        return totalForm;
    }

    public void setTotalForm(TotalForm totalForm) {
        this.totalForm = totalForm;
    }
}
